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
@Table(name = "comentarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComentarioEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("texto")
    private String texto;

    @Column("usuario_id")
    private Long usuarioId;

    @Column("post_id")
    private Long postId;

    @Column("fecha")
    private LocalDateTime fecha;
}

