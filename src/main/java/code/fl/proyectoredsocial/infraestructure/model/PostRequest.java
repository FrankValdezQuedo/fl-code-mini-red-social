package code.fl.proyectoredsocial.infraestructure.model;

import jakarta.validation.constraints.NotBlank;
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
    private long id;

    @NotBlank(message = "El contenido no puede estar vacío")
    private String contenido;


    private String imagenUrl;

    @NotBlank(message = "El ID de usuario no puede estar vacío")
    private long usuarioId;

    @NotBlank(message = "La fecha no puede estar vacía")
    private LocalDateTime fecha;
}
