package code.fl.proyectoredsocial.application.port.in;

import code.fl.proyectoredsocial.domain.model.ComentarioListResponse;
import code.fl.proyectoredsocial.domain.model.ComentarioResponse;
import code.fl.proyectoredsocial.infraestructure.model.ComentarioRequest;
import reactor.core.publisher.Mono;

public interface ComentarioInputPort {
    Mono<ComentarioListResponse> findAll();
    Mono<ComentarioListResponse> findAllByUsuarioId(Long usuarioId);
    Mono<ComentarioListResponse> findAllByPostId(Long postId);
    Mono<ComentarioResponse> saveComentario(ComentarioRequest comentarioRequest);
    Mono<ComentarioResponse> updateComentario(ComentarioRequest comentarioRequest);
    Mono<ComentarioResponse> deleteComentario(Long id);
}

