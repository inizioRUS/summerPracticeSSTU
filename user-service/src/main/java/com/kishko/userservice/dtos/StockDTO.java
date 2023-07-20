package com.kishko.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.User;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long id;

    private String name;

    private String shortName;

    private Double price;

    @JsonIgnore
    private List<AdvancedStock> advancedStocks;

    @JsonIgnore
    private List<User> users;

    private String attachmentId;

}
