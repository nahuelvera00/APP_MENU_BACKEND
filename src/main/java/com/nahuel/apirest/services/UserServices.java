package com.nahuel.apirest.services;

import com.nahuel.apirest.entities.User;
import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    //ATRIBUTOS
    private final UserRepository userRepository;

    //CONSTRUCTOR
    public UserServices(UserRepository userRepository) { //Toma los atributos como parametros
        this.userRepository = userRepository;
    }


    //METODOS

    /**
     *
     * @return UserBasicgit add DTO list, all users in DB
     */
    public List<UserBasicDTO> getUsers() {                                          //Retorna una lista de UsersDTO
        List<User> users = userRepository.findAll();                           //Ejecuta el metodo interno findAll desde el Repository e ingresa el resultado en una lista de Users. pueden crearse metodos personalizados.
        return users.stream().map(User::toCoreModelBasic).toList();                 //Se ejecuta un map y por cada elemento de esa lista, se ejecuta su metodo toCorelModel, el cual retona un UserDTO, añadiendolo a la lista, retornando la misma.
    }

    /**
     *
     * @param id of the user
     * @return UserBasicDTO or null
     */
    public UserBasicDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get().toCoreModelBasic();
        }
        return new UserBasicDTO();
    }

    public void createUser(UserDTO user) {

        User newUser = new User(
                user.getName(),
                user.getSurname(),
                user.getCellphone(),
                user.getEmail(),
                user.getPassword());
        userRepository.save(newUser);
    }

    public void updateUser(Long id, UserDTO user) {
        Optional<User> updateUser = userRepository.findById(id);
        if(updateUser.isPresent()) {

            User userUpdate = updateUser.get();

            userUpdate.setName(user.getName());
            userUpdate.setSurname(user.getSurname());
            userUpdate.setCellphone(user.getCellphone());
            userUpdate.setEmail(user.getEmail());

            userRepository.save(userUpdate);
        }

    }

    public ResponseEntity<String> deleteUser(Long id) {

        Optional<User> deleteUser = userRepository.findById(id);

        if(deleteUser.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok("Successfully deleted");
        }

        return ResponseEntity.badRequest().build();
    }


}
