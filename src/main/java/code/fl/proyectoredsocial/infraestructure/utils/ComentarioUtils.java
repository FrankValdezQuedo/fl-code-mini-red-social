package code.fl.proyectoredsocial.infraestructure.utils;

import code.fl.proyectoredsocial.domain.model.Comentario;
import code.fl.proyectoredsocial.domain.model.ComentarioResponse;
import code.fl.proyectoredsocial.domain.model.ComentarioListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.ComentarioEntity;
import code.fl.proyectoredsocial.infraestructure.model.ComentarioRequest;

import java.util.List;

public class ComentarioUtils {

    public static ComentarioListResponse covertComentarioListResponse(List<ComentarioEntity> entity) {
        return ComentarioListResponse.builder()
                .data(entity.stream()
                        .map(ComentarioUtils::convertComentarioResponse)
                        .toList())
                .build();
    }

    public static Comentario convertComentarioResponse(ComentarioEntity entity) {
        return Comentario.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .usuarioId(entity.getUsuarioId())
                .postId(entity.getPostId())
                .fecha(entity.getFecha())
                .build();
    }

    public static ComentarioEntity convertComentarioEntity(ComentarioRequest comentarioRequest) {
        return ComentarioEntity.builder()
                .texto(comentarioRequest.getTexto())
                .usuarioId(comentarioRequest.getUsuarioId())
                .postId(comentarioRequest.getPostId())
                .fecha(comentarioRequest.getFecha())
                .build();
    }

    public static ComentarioEntity convertComentarioEntityUpdate(ComentarioRequest comentarioRequest) {
        return ComentarioEntity.builder()
                .id(comentarioRequest.getId())
                .texto(comentarioRequest.getTexto())
                .usuarioId(comentarioRequest.getUsuarioId())
                .postId(comentarioRequest.getPostId())
                .fecha(comentarioRequest.getFecha())
                .build();
    }

    public static ComentarioResponse convertComentarioResponseSave(String id) {
        return ComentarioResponse.builder()
                .codResponse(Constantes.COD_RESPONSE)
                .messageResponse(Constantes.COMENTARIO_SAVE)
                .codEntity(id)
                .build();
    }

    public static ComentarioResponse convertComentarioResponseDelete(String id) {
        return ComentarioResponse.builder()
                .codResponse(Constantes.COD_RESPONSE)
                .messageResponse(Constantes.COMENTARIO_DELETED)
                .codEntity(id)
                .build();
    }
}

