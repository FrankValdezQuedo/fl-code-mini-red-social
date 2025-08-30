package code.fl.proyectoredsocial.infraestructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public class PostsEntity {
    @Id
    private Long id;

    private String contenido;

    @Column("imagen_url")
    private String imagenUrl;

    @Column("usuario_id")
    private Long usuarioId; // Relación con el usuario (clave foránea)

    private LocalDateTime fecha;
}
