package com.nahuel.apirest.services;

import com.nahuel.apirest.auth.JwtResponse;
import com.nahuel.apirest.entities.User;
import com.nahuel.apirest.models.LoginDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /**
     * Login User
     * @param user loginDTO (email, password)
     * @return JWT Token
     */
    public ResponseEntity<JwtResponse> login(LoginDTO user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        var userLogin = userRepository.findByEmail(user.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(userLogin);
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    /**
     *  Register new user
     * @param userData (name, surname, cellphone, email, password)
     * @return JWT token
     */
    public ResponseEntity<JwtResponse> register(UserDTO userData) {
        Optional<User> UserExist = userRepository.findByEmail(userData.getEmail());

        // Verify that the email is not in use
        if(UserExist.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        // todo Validate fields

        User newUser = new User(
                userData.getName(),
                userData.getSurname(),
                userData.getCellphone(),
                userData.getEmail(),
                passwordEncoder.encode(userData.getPassword())
        );

        //Save new user
        userRepository.save(newUser);

        //Generate JWT
        var jwtToken = jwtService.generateToken(newUser);
        return ResponseEntity.ok(new JwtResponse(jwtToken));

    }
}
