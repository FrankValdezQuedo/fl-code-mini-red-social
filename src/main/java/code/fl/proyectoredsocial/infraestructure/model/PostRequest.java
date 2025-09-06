package code.fl.proyectoredsocial.infraestructure.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    private Long id;

    @NotBlank(message = "El contenido no puede estar vacío")
    private String contenido;

    private String imagenUrl;

    @NotNull(message = "El usuarioId no puede ser nulo")
    private Long usuarioId;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime fecha;
}
