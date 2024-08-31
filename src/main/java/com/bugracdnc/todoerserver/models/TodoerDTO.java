package com.bugracdnc.todoerserver.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class TodoerDTO {
    private UUID id;

    @NotNull
    private Integer index;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String todo;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
