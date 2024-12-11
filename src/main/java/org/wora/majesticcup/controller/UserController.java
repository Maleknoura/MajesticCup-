package org.wora.majesticcup.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.user.ResponseUserDto;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.service.interfaces.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<ResponseUserDto>> getAllUtilisateurs(Pageable pageable) {
        Page<ResponseUserDto> response = userService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUtilisateurById(@PathVariable String id) {
        ResponseUserDto response = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtilisateurById(@PathVariable String id){
        if(userService.delete(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Succefully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }



}
