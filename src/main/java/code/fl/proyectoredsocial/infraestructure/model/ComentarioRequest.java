package code.fl.proyectoredsocial.infraestructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComentarioRequest {

    private Long id;

    @NotBlank(message = "El texto no puede estar vacío")
    private String texto;

    @NotNull(message = "El usuarioId no puede ser nulo")
    private Long usuarioId;

    @NotNull(message = "El postId no puede ser nulo")
    private Long postId;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime fecha;
}

