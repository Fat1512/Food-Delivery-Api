package com.food.phat.dto.comment;

import lombok.Data;

@Data
public class CommentProductItemResponse {
    private Integer commentId;
    private Integer starCount;
    private String content;
    private Boolean isHavingChildren;
}
