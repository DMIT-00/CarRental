package com.dmit.rest.user;

import com.dmit.dto.user.UserDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.exception.NotFoundException;
import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        List<UserResponseDto> users = userService.getAllUsersPageable(page, size);
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") UUID id) {
        UserResponseDto user;

        try {
            user = userService.findUserById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(UserDto userDto) {
        UserResponseDto userResponseDto = userService.registerUser(userDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") UUID id, @RequestBody UserDto userDto) {
        UserResponseDto user;

        try {
            user = userService.findUserById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // TODO: Implement
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
