package code.fl.proyectoredsocial.infraestructure.rest.Controller;

import code.fl.proyectoredsocial.application.port.in.UserInputPort;
import code.fl.proyectoredsocial.domain.error.UserNotFoundException;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.domain.model.UserResponse;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;
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
    public Mono<UserListResponse> findById(@PathVariable Long id) {
        return userInputPort.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado con id " + id)));
    }

    @GetMapping("/all")
    public Mono<UserListResponse> findAll() {
        return userInputPort.findAll();
    }

    @PostMapping("/save")
    public Mono<UserResponse> saveUser(@RequestBody @Validated UserRequest userRequest) {
        return userInputPort.saveUser(userRequest)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("UserRequest no puede ser null")));
    }

    @PostMapping("/update")
    public Mono<UserResponse> updateUser(@RequestBody @Validated UserRequest userRequest) {
        return userInputPort.updateUser(userRequest)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado para actualizar con id " + userRequest.getId())));
    }
    @DeleteMapping("/delete/{id}")
    public Mono<UserResponse> deleteUser(@PathVariable Long id) {
        return userInputPort.deleteUser(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario no encontrado para eliminar con id " + id)));
    }

}