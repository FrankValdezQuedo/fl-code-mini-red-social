package code.fl.proyectoredsocial.infraestructure.rest.Controller;

import code.fl.proyectoredsocial.application.port.in.ComentarioInputPort;
import code.fl.proyectoredsocial.domain.model.ComentarioListResponse;
import code.fl.proyectoredsocial.domain.model.ComentarioResponse;
import code.fl.proyectoredsocial.infraestructure.model.ComentarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comentario")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioInputPort comentarioInputPort;

    @GetMapping("/all")
    public Mono<ComentarioListResponse> findAll() {
        return comentarioInputPort.findAll();
    }

    @GetMapping("/all/user/{usuarioId}")
    public Mono<ComentarioListResponse> findAllByUsuarioId(@PathVariable Long usuarioId) {
        return comentarioInputPort.findAllByUsuarioId(usuarioId);
    }

    @GetMapping("/all/post/{postId}")
    public Mono<ComentarioListResponse> findAllByPostId(@PathVariable Long postId) {
        return comentarioInputPort.findAllByPostId(postId);
    }

    @PostMapping("/save")
    public Mono<ComentarioResponse> saveComentario(@RequestBody @Validated ComentarioRequest comentarioRequest) {
        return comentarioInputPort.saveComentario(comentarioRequest)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("ComentarioRequest no puede ser null")));
    }

    @PostMapping("/update")
    public Mono<ComentarioResponse> updateComentario(@RequestBody @Validated ComentarioRequest comentarioRequest) {
        return comentarioInputPort.updateComentario(comentarioRequest)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("ComentarioRequest no puede ser null")));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ComentarioResponse> deleteComentario(@PathVariable Long id) {
        return comentarioInputPort.deleteComentario(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No se pudo eliminar el comentario con id " + id)));
    }
}

