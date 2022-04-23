package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    //Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class) @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){

        log.debug("POST: registerUser userDto received {}", userDto.toString());

        if(userService.existsByUsername(userDto.getUsername())){
            log.warn("POST: Username  {} is already taken", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is already taken!");
        }

        if(userService.existsByEmail(userDto.getEmail())){
            log.warn("POST: Email  {} is already taken", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userService.save(userModel);

        log.debug("POST: registerUser userDto saved {}", userModel.toString());
        log.info("User saved successfully userId {}", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index(){
        log.trace("TRACE");
        log.trace("DEBUG");
        log.trace("WARN");
        log.trace("ERROR");
        log.trace("INFO");

/*        try{
            throw new Exception("Exception message");
        }catch (Exception e){
            log.error("----ERROR----", e);
        }*/

        return "Logging ...";
    }
}
