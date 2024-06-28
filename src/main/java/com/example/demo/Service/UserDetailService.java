package com.example.demo.Service;

import com.example.demo.Model.UserDetail;
import com.example.demo.Repository.UserDetailRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;



    //Get Methods
    public UserDetail getUserDetail(Long id) {
        UserDetail userDetail = userDetailRepository.findById(id).orElse(null);
        if(userDetail == null) {
            throw new RuntimeException("User Detail not found");
        }
        return userDetail;
    }

    //Post Methods
    public void save(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }
}
