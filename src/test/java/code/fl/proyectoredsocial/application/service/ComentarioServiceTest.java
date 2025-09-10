package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.out.ComentarioRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.error.ComentarioNotFoundException;
import code.fl.proyectoredsocial.domain.model.ComentarioListResponse;
import code.fl.proyectoredsocial.domain.model.ComentarioResponse;
import code.fl.proyectoredsocial.infraestructure.entity.ComentarioEntity;
import code.fl.proyectoredsocial.infraestructure.model.ComentarioRequest;
import code.fl.proyectoredsocial.infraestructure.utils.ComentarioUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ComentarioServiceTest {

    @Mock
    private ComentarioRepositoryOutputPort comentarioOutputPort;

    @InjectMocks
    private ComentarioService comentarioService;

    private ComentarioEntity comentarioEntity;
    private ComentarioRequest comentarioRequest;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            comentarioEntity = ComentarioEntity.builder()
                    .id(1L)
                    .texto("Test")
                    .usuarioId(2L)
                    .postId(3L)
                    .fecha(LocalDateTime.now())
                    .build();
            comentarioRequest = ComentarioRequest.builder()
                    .id(1L)
                    .texto("Test")
                    .usuarioId(2L)
                    .postId(3L)
                    .fecha(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll_success() {
        when(comentarioOutputPort.findAll()).thenReturn(Flux.just(comentarioEntity));
        try (MockedStatic<ComentarioUtils> utils = mockStatic(ComentarioUtils.class)) {
            utils.when(() -> ComentarioUtils.covertComentarioListResponse(any())).thenReturn(
                    ComentarioListResponse.builder().data(List.of()).build()
            );
            StepVerifier.create(comentarioService.findAll())
                    .expectNextMatches(java.util.Objects::nonNull)
                    .verifyComplete();
        }
    }

    @Test
    void findAllByUsuarioId_success() {
        when(comentarioOutputPort.findAllByUsuarioId(2L)).thenReturn(Flux.just(comentarioEntity));
        try (MockedStatic<ComentarioUtils> utils = mockStatic(ComentarioUtils.class)) {
            utils.when(() -> ComentarioUtils.covertComentarioListResponse(any())).thenReturn(
                    ComentarioListResponse.builder().data(List.of()).build()
            );
            StepVerifier.create(comentarioService.findAllByUsuarioId(2L))
                    .expectNextMatches(java.util.Objects::nonNull)
                    .verifyComplete();
        }
    }

    @Test
    void findAllByPostId_success() {
        when(comentarioOutputPort.findAllByPostId(3L)).thenReturn(Flux.just(comentarioEntity));
        try (MockedStatic<ComentarioUtils> utils = mockStatic(ComentarioUtils.class)) {
            utils.when(() -> ComentarioUtils.covertComentarioListResponse(any())).thenReturn(
                    ComentarioListResponse.builder().data(List.of()).build()
            );
            StepVerifier.create(comentarioService.findAllByPostId(3L))
                    .expectNextMatches(java.util.Objects::nonNull)
                    .verifyComplete();
        }
    }

    @Test
    void saveComentario_success() {
        when(comentarioOutputPort.saveComentario(any())).thenReturn(Mono.just(comentarioEntity));
        try (MockedStatic<ComentarioUtils> utils = mockStatic(ComentarioUtils.class)) {
            utils.when(() -> ComentarioUtils.convertComentarioEntity(any())).thenReturn(comentarioEntity);
            utils.when(() -> ComentarioUtils.convertComentarioResponseSave(any())).thenReturn(
                    ComentarioResponse.builder().codEntity("1").build()
            );
            StepVerifier.create(comentarioService.saveComentario(comentarioRequest))
                    .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                    .verifyComplete();
        }
    }

    @Test
    void updateComentario_success() {
        when(comentarioOutputPort.findAll()).thenReturn(Flux.just(comentarioEntity));
        when(comentarioOutputPort.updateComentario(any())).thenReturn(Mono.just(comentarioEntity));
        try (MockedStatic<ComentarioUtils> utils = mockStatic(ComentarioUtils.class)) {
            utils.when(() -> ComentarioUtils.convertComentarioEntityUpdate(any())).thenReturn(comentarioEntity);
            utils.when(() -> ComentarioUtils.convertComentarioResponseSave(any())).thenReturn(
                    ComentarioResponse.builder().codEntity("1").build()
            );
            StepVerifier.create(comentarioService.updateComentario(comentarioRequest))
                    .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                    .verifyComplete();
        }
    }

    @Test
    void updateComentario_notFound() {
        when(comentarioOutputPort.findAll()).thenReturn(Flux.empty());
        StepVerifier.create(comentarioService.updateComentario(comentarioRequest))
                .expectError(ComentarioNotFoundException.class)
                .verify();
    }

    @Test
    void deleteComentario_success() {
        when(comentarioOutputPort.findAll()).thenReturn(Flux.just(comentarioEntity));
        when(comentarioOutputPort.deleteComentario(1L)).thenReturn(Mono.empty());
        try (MockedStatic<ComentarioUtils> utils = mockStatic(ComentarioUtils.class)) {
            utils.when(() -> ComentarioUtils.convertComentarioResponseDelete(any())).thenReturn(
                    ComentarioResponse.builder().codEntity("1").build()
            );
            StepVerifier.create(comentarioService.deleteComentario(1L))
                    .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                    .verifyComplete();
        }
    }

    @Test
    void deleteComentario_notFound() {
        when(comentarioOutputPort.findAll()).thenReturn(Flux.empty());
        StepVerifier.create(comentarioService.deleteComentario(1L))
                .expectError(ComentarioNotFoundException.class)
                .verify();
    }
}
