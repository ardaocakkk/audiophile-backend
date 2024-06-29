package com.example.demo.Controller;

import com.example.demo.Dto.UserDetailDto;
import com.example.demo.Model.User;
import com.example.demo.Model.UserDetail;
import com.example.demo.Service.UserDetailService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailService userDetailService;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }


    @PostMapping("/addUserDetail")
    public ResponseEntity<String> addUserDetail(@RequestBody UserDetailDto userDetailDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        UserDetail userDetail = UserDetail.builder()
                .Country(userDetailDto.getCountry())
                .PostalCode(userDetailDto.getPostalCode())
                .PhoneNumber(userDetailDto.getPhoneNumber())
                .City(userDetailDto.getCity())
                .Address(userDetailDto.getAddress())
                .build();
        userDetailService.save(userDetail, currentUser);
        return ResponseEntity.ok("User Detail added successfully");
    }
}
