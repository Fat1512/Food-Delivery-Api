package com.food.phat.dto.comment;


import lombok.Data;

import java.util.List;

@Data
public class CommentResponse {
    private Integer parentId;
    private List<CommentItemResponse> comments;
}
