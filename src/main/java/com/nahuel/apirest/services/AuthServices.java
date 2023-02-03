package com.nahuel.apirest.services;

import com.nahuel.apirest.entities.User;
import com.nahuel.apirest.models.LoginDTO;
import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.repository.UserRepository;
import com.nahuel.apirest.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServices {

    private final UserRepository userRepository;
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    @Autowired
    private JWTUtil jwtUtil;

    //CONSTRUCTOR
    public AuthServices(UserRepository userRepository) { //Toma los atributos como parametros
        this.userRepository = userRepository;
    }


    /**
     * Login User
     * @param user loginDTO (email, password)
     * @return JWT Token
     */
    public ResponseEntity<String> login(LoginDTO user) {
        Optional<User> userLogin = userRepository.findByEmail(user.getEmail());

        //Check if the email is in use
        if(!userLogin.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        //Validate password
        String passwordHashed = userLogin.get().getPassword();

        if(argon2.verify(passwordHashed, user.getPassword())){
            String token = jwtUtil.create(String.valueOf(userLogin.get().getId()), userLogin.get().getEmail());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.badRequest().build();
    }

    /**
     *  Register new user
     * @param userData (name, surname, cellphone, email, password)
     * @return JWT token
     */
    public ResponseEntity<String> register(UserDTO userData) {
        Optional<User> UserExist = userRepository.findByEmail(userData.getEmail());

        // Verify that the email is not in use
        if(UserExist.isPresent()){
            return ResponseEntity.badRequest().body("This email is already in use");
        }

        // todo Validate fields

        //HASHED password
        String hashPassword = argon2.hash(1, 1024, 1, userData.getPassword());

        User newUser = new User(
                userData.getName(),
                userData.getSurname(),
                userData.getCellphone(),
                userData.getEmail(),
                hashPassword
        );

        //Save new user
        userRepository.save(newUser);

        //Generate JWT
        String token = jwtUtil.create(String.valueOf(newUser.getId()), newUser.getEmail());

        return ResponseEntity.ok(token);

    }
}
