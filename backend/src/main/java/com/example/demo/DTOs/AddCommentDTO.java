package com.example.demo.DTOs;

import com.example.demo.Entities.Comment;
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
