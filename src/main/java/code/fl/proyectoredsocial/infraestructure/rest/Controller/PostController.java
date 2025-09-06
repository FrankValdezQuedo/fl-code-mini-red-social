package code.fl.proyectoredsocial.infraestructure.rest.Controller;

import code.fl.proyectoredsocial.application.port.in.PostInputPort;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.domain.model.PostResponse;
import code.fl.proyectoredsocial.infraestructure.model.PostRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/post")
@AllArgsConstructor
@RestController
@Validated
@CrossOrigin
public class PostController {

    private final PostInputPort postInputPort;

    @GetMapping("/all")
    Mono<PostListResponse> findAll() {
        return postInputPort.findAll();
    }

    @GetMapping("/all/user/{usuarioId}")
    public Mono<PostListResponse> findAllByUsuarioId(@PathVariable Long usuarioId) {
        return postInputPort.findAllByUsuarioId(usuarioId);
    }

    @PostMapping("/save")
    public Mono<PostResponse> savePost(@RequestBody @Validated PostRequest postRequest) {
        return postInputPort.savePost(postRequest)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("PostRequest no puede ser null")));

    }

    @PostMapping("/update")
    public Mono<PostResponse> updatePost(@RequestBody @Validated PostRequest postRequest) {
        return postInputPort.updatePost(postRequest)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("PostRequest no puede ser null")));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<PostResponse> deletePost(@PathVariable Long id) {
        return postInputPort.deletePost(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No se pudo eliminar el post con id " + id)));
    }
}
