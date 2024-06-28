package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserDetail;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Get Methods
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public UserDetail getUserDetail(Long id) {
        return Objects.requireNonNull(userRepository.findById(id).orElse(null)).getUserDetail();
    }


    //Post Methods
    public void saveUser(User user) {
        User existingUser = userRepository.findUserByEmail(user.getEmail()).stream().findFirst().orElse(null);
        if(existingUser == null) {
            userRepository.save(user);
        }

    }

    public void saveUserDetail(UserDetail userDetail, Long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null) {
            existingUser.setUserDetail(userDetail);
            userRepository.save(existingUser);
        }
    }

}
