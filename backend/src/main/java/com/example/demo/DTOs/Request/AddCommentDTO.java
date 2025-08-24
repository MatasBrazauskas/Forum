package com.example.demo.DTOs.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

/// /////////////////////////
@Data
@NoArgsConstructor
public class AddCommentDTO
{
    private String threadName;
    private String comment;
    private long replyId;
}
