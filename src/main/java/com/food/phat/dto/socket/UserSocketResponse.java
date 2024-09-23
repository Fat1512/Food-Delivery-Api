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
    private Integer chatRoomId;
    private String name;
    private String avatar;
    private Integer role;
    private Boolean status;
}
