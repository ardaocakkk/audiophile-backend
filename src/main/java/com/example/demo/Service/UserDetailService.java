package com.example.demo.Service;

import com.example.demo.Model.User;
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

    public void save(UserDetail userDetail, User authenticatedUser) {
        if (authenticatedUser.getUserDetail() != null) {
            update(userDetail, authenticatedUser);
        } else {
            userDetailRepository.save(userDetail);
            authenticatedUser.setUserDetail(userDetail);
            userRepository.save(authenticatedUser);
        }
    }

    public void update(UserDetail userDetail, User authenticatedUser) {
        UserDetail existingUserDetail = authenticatedUser.getUserDetail();
        if (existingUserDetail == null) {
            throw new RuntimeException("User Detail not found");
        }
        existingUserDetail.setCountry(userDetail.getCountry());
        existingUserDetail.setCity(userDetail.getCity());
        existingUserDetail.setAddress(userDetail.getAddress());
        existingUserDetail.setPostalCode(userDetail.getPostalCode());
        existingUserDetail.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailRepository.save(existingUserDetail);
    }
}
