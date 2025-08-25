package com.example.demo.DTOs.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GetCommentDTO
{
    private PartialProfileInfoDTO partialProfile;

    private LocalDate dateOfCreation;
    private String comment;
    private Long commentId;

    private String reply;
    private Long replyId;
}
