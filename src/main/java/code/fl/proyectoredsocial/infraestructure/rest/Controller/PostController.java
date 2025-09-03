package code.fl.proyectoredsocial.infraestructure.rest.Controller;

import code.fl.proyectoredsocial.application.port.in.PostInputPort;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
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
}
