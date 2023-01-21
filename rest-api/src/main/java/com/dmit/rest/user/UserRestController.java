package com.dmit.rest.user;

import com.dmit.dto.user.UserRequestDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dmit.config.RestConfig.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping
    @Secured("ROLE_MANAGER")
    public ResponseEntity<List<UserResponseDto>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE)
                                                          int size) {
        List<UserResponseDto> users = userService.findAllUsersPageable(page, size);
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") UUID id) {
        UserResponseDto user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") UUID id,
                                                      @RequestBody UserRequestDto updatedUser) {
        updatedUser.setId(id);

        UserResponseDto resultUser = userService.updateUser(updatedUser);

        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
