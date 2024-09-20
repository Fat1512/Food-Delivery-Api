package com.food.phat.dto.comment;

import lombok.Data;

@Data
public class CommentItemResponse {
    private Integer commentId;
    private String content;
    private Boolean isHavingChildren;
}
