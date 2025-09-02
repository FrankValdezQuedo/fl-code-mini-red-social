package code.fl.proyectoredsocial.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEntity {
    @Id
    private Long id;

    private String contenido;

    @Column("imagen_url")
    private String imagenUrl;

    @Column("usuario_id")
    private Long usuarioId;

    private LocalDateTime fecha;
}
