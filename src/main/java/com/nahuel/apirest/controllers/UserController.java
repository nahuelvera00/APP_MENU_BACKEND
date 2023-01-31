package com.nahuel.apirest.controllers;

import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/users")
public class UserController {

    //ATRIBUTOS

    private final UserServices userServices; //Se toma la clase de Servicio del mismo como un atributo

    //CONSTRUCTOR
    public UserController(UserServices userServices) { //Se asigna al constructor el atributo service para poderlo instancciar y utilizar sus metodos
        this.userServices = userServices;
    }

    //METODOS


    @GetMapping
    public List<UserBasicDTO> getUser(@RequestParam(value = "id", required = false) Long id) {
        if (Objects.isNull(id)) {
            return userServices.getUsers();
        }
        return List.of(userServices.getUser(id));
    }

    @PostMapping
    public String createUser(@RequestBody UserDTO user) {

        userServices.createUser(user);

        return "success";
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user) {

        userServices.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

        return userServices.deleteUser(id);
    }


}
