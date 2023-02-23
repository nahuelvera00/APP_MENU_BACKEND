package com.nahuel.apirest.services;

import com.nahuel.apirest.entities.Business;
import com.nahuel.apirest.entities.User;
import com.nahuel.apirest.models.BusinessDTO;
import com.nahuel.apirest.repository.BusinessRepository;
import com.nahuel.apirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BusinessServices {

    private final BusinessRepository businessRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String hello(String token) {
        return jwtService.extractUsername(token.replace("Bearer ", ""));
    }

    public ResponseEntity<String> create(String token, BusinessDTO businessData) {

        String emailUser = jwtService.extractUsername(token.replace("Bearer ", ""));
        Optional<User> userLogin = userRepository.findByEmail(emailUser);
        if(userLogin.isPresent()){
            User user = userLogin.get();

            Business newBusiness = businessRepository.save(new Business(null, businessData.getName(),null ,user));

            user.getBusinesses().add(newBusiness);

            userRepository.save(user);
            return ResponseEntity.ok("add success");
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<BusinessDTO>> getBusiness(String token) {
        String emailUser = jwtService.extractUsername(token.replace("Bearer ", ""));
        Optional<User> userLogin = userRepository.findByEmail(emailUser);

        if (userLogin.isPresent()) {
            User user = userLogin.get();

            List<BusinessDTO> businesses = new ArrayList<>();

            for(Business business: user.getBusinesses()) {
                BusinessDTO businessDTO = new BusinessDTO(
                        business.getId(),
                        business.getName(),
                        business.getMenu()
                );
                businesses.add(businessDTO);
            }

            return ResponseEntity.ok(businesses);
        }

        return ResponseEntity.badRequest().build();
    }
}
