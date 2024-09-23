package com.food.phat.dto.socket;


import lombok.*;


@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSocketResponse {
    private Integer userId;
    private String name;
    private String avatar;
    private Integer role;
}
