package com.example.demo.DTOs.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/// //////////////////////
@Data
public class AddThreadDTO
{
    @NotEmpty
    private String topicsName;
    @Size(min = 1, max = 64, message = "Title length is invalid: length between 1 and 64")
    private String title;
    @Size(min = 1, max = 256, message = "Contents length is invalid: length between 1 and 256")
    private String content;
}
