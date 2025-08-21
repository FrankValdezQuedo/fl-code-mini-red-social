package code.fl.proyectoredsocial.infraestructure.rest.Controller;

import code.fl.proyectoredsocial.application.port.in.UserInputPort;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/user")
@AllArgsConstructor
@RestController
@Validated
@CrossOrigin
public class UserController {

    private final UserInputPort userInputPort;

    @GetMapping("/{id}")
    Mono<UserListResponse> findById(@PathVariable Long id) {
        return userInputPort.findById(id);
    }

}