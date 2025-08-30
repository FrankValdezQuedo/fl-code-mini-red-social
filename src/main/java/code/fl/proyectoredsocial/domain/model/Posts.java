package code.fl.proyectoredsocial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Posts {
    private Long id;
    private String contenido;
    private String imagenUrl;
    private Long usuarioId;
    private LocalDateTime fecha;
}
