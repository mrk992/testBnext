package com.test.technicalMarcos.controller;

import com.test.technicalMarcos.domain.NewUserDTO;
import com.test.technicalMarcos.domain.UserDTO;
import com.test.technicalMarcos.domain.request.NewUserBody;
import com.test.technicalMarcos.service.IUserService;
import com.test.technicalMarcos.service.TelephoneServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody NewUserBody newUserBody) {

        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(newUserBody.getName());
        newUserDTO.setLastName(newUserBody.getLastName());
        newUserDTO.setTelephone(newUserBody.getTelephone());
        try{
            UserDTO userDTO = userService.createUser(newUserDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (TelephoneServiceException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}