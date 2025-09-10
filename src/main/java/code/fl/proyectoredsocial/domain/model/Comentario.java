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
public class Comentario {
    private Long id;
    private String texto;
    private Long usuarioId;
    private Long postId;
    private LocalDateTime fecha;
}

