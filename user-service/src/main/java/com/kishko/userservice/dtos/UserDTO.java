package com.kishko.userservice.dtos;

import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private Double balance;

    private Role role;

    private List<Stock> stocks;

}
