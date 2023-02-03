package com.jap.eCommerce.Jap_ECom.controller.user;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;
import com.jap.eCommerce.Jap_ECom.exception.AppsException;
import com.jap.eCommerce.Jap_ECom.model.common.ResponseDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.user.UserDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.user.UserPasswordChangeDTO;
import com.jap.eCommerce.Jap_ECom.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/registerUser", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> registerUser(@RequestBody UserDTO registerUserDTO) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        UserDTO userDTO;
        try {
            userDTO = this.userService.registerUser(registerUserDTO);

            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/changeUserPassword/{userID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> changeUserPassword(@RequestBody UserPasswordChangeDTO passwordChangeDTO, @PathVariable Long userID) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        UserDTO userDTO;
        try {
            userDTO = this.userService.changeUserPassword(passwordChangeDTO, userID);

            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getUserByID/{userID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserByID(@PathVariable Long userID) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            UserDTO userDTO = this.userService.getUserByID(userID);

            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/resetUserPassword/{userID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> resetUserPassword(@PathVariable Long userID) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            UserDTO userDTO = this.userService.resetUserPassword(userID);
            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateUser/{userID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@RequestBody UserDTO registerUserDTO, @PathVariable Long userID) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        UserDTO userDTO = null;
        try {
            userDTO = this.userService.updateUser(registerUserDTO, userID);

            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllUsers", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getAllUsers() {
        ResponseDTO<List<UserDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<UserDTO> userList = this.userService.getAllUsers();

            response.setResult(userList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
