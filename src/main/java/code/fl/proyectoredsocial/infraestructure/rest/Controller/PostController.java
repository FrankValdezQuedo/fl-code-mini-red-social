package code.fl.proyectoredsocial.infraestructure.rest.Controller;

import code.fl.proyectoredsocial.application.port.in.PostInputPort;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
