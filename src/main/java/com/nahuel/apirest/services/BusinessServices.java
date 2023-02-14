package com.nahuel.apirest.services;

import com.nahuel.apirest.entities.Business;
import com.nahuel.apirest.entities.User;
import com.nahuel.apirest.models.BusinessDTO;
import com.nahuel.apirest.repository.BusinessRepository;
import com.nahuel.apirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

            Business addBusiness = businessRepository.save(new Business(null, user, businessData.getName()));
            return ResponseEntity.ok("add success");
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Business>> getBusiness(String token) {
        String emailUser = jwtService.extractUsername(token.replace("Bearer ", ""));
        Optional<User> userLogin = userRepository.findByEmail(emailUser);

        if (userLogin.isPresent()) {
            User user = userLogin.get();

            return ResponseEntity.ok(businessRepository.findByUser_Id(user.getId()));
        }

        return ResponseEntity.badRequest().build();
    }
}
