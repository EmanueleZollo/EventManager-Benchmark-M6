package EventManager.emanuele.controllers;

import EventManager.emanuele.entities.User;
import EventManager.emanuele.exceptions.BadRequestException;
import EventManager.emanuele.payloads.NewUserDTO;
import EventManager.emanuele.payloads.SuccessfulLoginDTO;
import EventManager.emanuele.payloads.UserLoginDTO;
import EventManager.emanuele.services.AuthService;
import EventManager.emanuele.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService usersService;

    @PostMapping("/login")
    public SuccessfulLoginDTO.UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){

        return new SuccessfulLoginDTO.UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return usersService.save(body);
        }
    }
}
