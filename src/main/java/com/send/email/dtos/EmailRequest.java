package com.send.email.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequest {
	@Email
    @NotBlank
    private String to;

    private List<@Email String> cc;
    private List<@Email String> bcc;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;
}
