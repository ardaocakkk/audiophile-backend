package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDetail {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;



    private String Country;
    private String City;
    private String Address;
    private String PostalCode;
    private String PhoneNumber;

    @OneToOne(mappedBy = "userDetail", cascade = jakarta.persistence.CascadeType.ALL)
    @JsonIgnore
    private User user;
}
