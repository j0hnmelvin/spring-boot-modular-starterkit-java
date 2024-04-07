package com.jms.boilerplate.userservice.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.Instant;
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
    @Nullable
    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    @Email
    String email;

    @Nullable
    Instant createdAt;

    @Nullable
    Instant updatedAt;
}
