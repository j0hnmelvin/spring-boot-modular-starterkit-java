package com.jms.boilerplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data // or individual annotations
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @NotEmpty
    String id;

    @NotEmpty
    String name;

    @NotEmpty
    @Email
    String email;
}
