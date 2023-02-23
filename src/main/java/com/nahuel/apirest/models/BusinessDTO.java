package com.nahuel.apirest.models;

import com.nahuel.apirest.entities.Menu;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessDTO {
    private Long id;
    private String name;
    private Menu menu;
}
