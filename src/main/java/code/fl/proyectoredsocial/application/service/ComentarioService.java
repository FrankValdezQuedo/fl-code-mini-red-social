package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.in.ComentarioInputPort;
import code.fl.proyectoredsocial.application.port.out.ComentarioRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.error.ComentarioNotFoundException;
import code.fl.proyectoredsocial.domain.model.ComentarioListResponse;
import code.fl.proyectoredsocial.domain.model.ComentarioResponse;
import code.fl.proyectoredsocial.infraestructure.model.ComentarioRequest;
import code.fl.proyectoredsocial.infraestructure.utils.ComentarioUtils;
import code.fl.proyectoredsocial.infraestructure.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j

public class ComentarioService implements ComentarioInputPort {


    private final ComentarioRepositoryOutputPort comentarioOutputPort;

    public ComentarioService(@Qualifier("comentarioRepositoryOutputPort") ComentarioRepositoryOutputPort comentarioOutputPort) {
        this.comentarioOutputPort = comentarioOutputPort;
    }

    @Override
    public Mono<ComentarioListResponse> findAll() {
        return comentarioOutputPort.findAll()
                .collectList()
                .map(ComentarioUtils::covertComentarioListResponse)
                .doOnError(error -> log.error(Constantes.FIND_ERROR, error.getMessage(), error));
    }

    @Override
    public Mono<ComentarioListResponse> findAllByUsuarioId(Long usuarioId) {
        return comentarioOutputPort.findAllByUsuarioId(usuarioId)
                .collectList()
                .map(ComentarioUtils::covertComentarioListResponse)
                .doOnError(error -> log.error(Constantes.FIND_ERROR, error.getMessage(), error));
    }

    @Override
    public Mono<ComentarioListResponse> findAllByPostId(Long postId) {
        return comentarioOutputPort.findAllByPostId(postId)
                .collectList()
                .map(ComentarioUtils::covertComentarioListResponse)
                .doOnError(error -> log.error(Constantes.FIND_ERROR, error.getMessage(), error));
    }

    @Override
    public Mono<ComentarioResponse> saveComentario(ComentarioRequest comentarioRequest) {
        return Mono.just(comentarioRequest)
                .map(ComentarioUtils::convertComentarioEntity)
                .flatMap(comentarioOutputPort::saveComentario)
                .map(entity -> ComentarioUtils.convertComentarioResponseSave(String.valueOf(entity.getId())))
                .doOnError(error -> log.error(Constantes.SAVE_ERROR, error.getMessage(), error));
    }

    @Override
    public Mono<ComentarioResponse> updateComentario(ComentarioRequest comentarioRequest) {
        return comentarioOutputPort
                .findAll() // No hay findById, así que buscamos todos y filtramos
                .filter(entity -> entity.getId().equals(comentarioRequest.getId()))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new ComentarioNotFoundException("Comentario con id " + comentarioRequest.getId() + " no encontrado")))
                .map(existing -> ComentarioUtils.convertComentarioEntityUpdate(comentarioRequest))
                .flatMap(comentarioOutputPort::updateComentario)
                .map(entity -> ComentarioUtils.convertComentarioResponseSave(String.valueOf(entity.getId())))
                .doOnError(error -> log.error(Constantes.UPDATE_ERROR, error.getMessage(), error));
    }

    @Override
    public Mono<ComentarioResponse> deleteComentario(Long id) {
        return comentarioOutputPort
                .findAll()
                .filter(entity -> entity.getId().equals(id))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new ComentarioNotFoundException("Comentario con id " + id + " no encontrado")))
                .flatMap(existing -> comentarioOutputPort.deleteComentario(id))
                .then(Mono.defer(() -> Mono.just(ComentarioUtils.convertComentarioResponseDelete(String.valueOf(id)))))
                .doOnError(error -> log.error(Constantes.DELETE_ERROR, error.getMessage(), error));
    }
}

