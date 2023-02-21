package com.nahuel.apirest.models;

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
}
