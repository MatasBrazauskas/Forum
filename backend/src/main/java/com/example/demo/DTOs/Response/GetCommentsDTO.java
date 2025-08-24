package com.example.demo.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

//?????????????????????????????????
@Data
@NoArgsConstructor
public class GetCommentsDTO
{
    private PartialProfileInfoDTO partialProfile;

    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contentDateOfCreation;

    private List<GetCommentDTO> comments;
}
