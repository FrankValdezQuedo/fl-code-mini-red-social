package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.error.UserNotFoundException;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepositoryOutputPort userRepositoryOutputPort;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close(); // Evita advertencia de AutoCloseable
    }

    @Test
    void saveUser_success() {
        UserRequest request = new UserRequest();
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        when(userRepositoryOutputPort.saveUserOrUpdate(any(UserEntity.class))).thenReturn(Mono.just(userEntity));
        StepVerifier.create(userService.saveUser(request))
                .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                .verifyComplete();
    }

    @Test
    void saveUser_error() {
        UserRequest request = new UserRequest();
        when(userRepositoryOutputPort.saveUserOrUpdate(any(UserEntity.class))).thenReturn(Mono.error(new RuntimeException("DB error")));
        StepVerifier.create(userService.saveUser(request))
                .expectError()
                .verify();
    }

    @Test
    void updateUser_success() {
        UserRequest request = new UserRequest();
        request.setId(1L);
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(userEntity));
        when(userRepositoryOutputPort.saveUserOrUpdate(any(UserEntity.class))).thenReturn(Mono.just(userEntity));
        StepVerifier.create(userService.updateUser(request))
                .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                .verifyComplete();
    }

    @Test
    void updateUser_notFound() {
        UserRequest request = new UserRequest();
        request.setId(1L);
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(userService.updateUser(request))
                .expectError(UserNotFoundException.class)
                .verify();
    }

    @Test
    void deleteUser_success() {
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(userEntity));
        when(userRepositoryOutputPort.deleteById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(userService.deleteUser(1L))
                .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                .verifyComplete();
    }

    @Test
    void deleteUser_notFound() {
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(userService.deleteUser(1L))
                .expectError(UserNotFoundException.class)
                .verify();
    }
}
