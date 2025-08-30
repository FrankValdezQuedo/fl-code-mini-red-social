package code.fl.proyectoredsocial.infraestructure.model;

import jakarta.validation.constraints.NotBlank;

public class PostsRequest {
    private long id;

    @NotBlank(message = "El contenido no puede estar vacío")
    private String contenido;

    @NotBlank(message = "La URL de la imagen no puede estar vacía")
    private String imagenUrl;

    @NotBlank(message = "El ID de usuario no puede estar vacío")
    private long usuarioId;

    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;
}
