package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.out.PostRepositoryOutputPort;
import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.error.PostNotFoundException;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.domain.model.PostResponse;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.model.PostRequest;
import code.fl.proyectoredsocial.infraestructure.utils.PostUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostServiceTest {
    @Mock
    private PostRepositoryOutputPort postRepositoryOutputPort;
    @Mock
    private UserRepositoryOutputPort userRepositoryOutputPort;
    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void findAll_success() {
        when(postRepositoryOutputPort.findAll()).thenReturn(Flux.just(PostEntity.builder().id(1L).build()));
        StepVerifier.create(postService.findAll())
                .expectNextMatches(resp -> resp != null)
                .verifyComplete();
    }

    @Test
    void findAll_error() {
        when(postRepositoryOutputPort.findAll()).thenReturn(Flux.error(new RuntimeException("DB error")));
        StepVerifier.create(postService.findAll())
                .expectError()
                .verify();
    }

    @Test
    void findAllByUsuarioId_success() {
        when(postRepositoryOutputPort.findAllByUsuarioId(1L)).thenReturn(Flux.just(PostEntity.builder().id(1L).build()));
        StepVerifier.create(postService.findAllByUsuarioId(1L))
                .expectNextMatches(resp -> resp != null)
                .verifyComplete();
    }

    @Test
    void findAllByUsuarioId_error() {
        when(postRepositoryOutputPort.findAllByUsuarioId(1L)).thenReturn(Flux.error(new RuntimeException("DB error")));
        StepVerifier.create(postService.findAllByUsuarioId(1L))
                .expectError()
                .verify();
    }

    @Test
    void savePost_success() {
        PostRequest request = PostRequest.builder().usuarioId(1L).build();
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        PostEntity postEntity = PostEntity.builder().id(1L).build();
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(userEntity));
        when(postRepositoryOutputPort.savePostOrUpdate(any(PostEntity.class))).thenReturn(Mono.just(postEntity));
        StepVerifier.create(postService.savePost(request))
                .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                .verifyComplete();
    }

    @Test
    void savePost_userNotFound() {
        PostRequest request = PostRequest.builder().usuarioId(1L).build();
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(postService.savePost(request))
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof code.fl.proyectoredsocial.domain.error.UserExceptions);
                    assertNotNull(error.getCause());
                    assertTrue(error.getCause() instanceof IllegalArgumentException);
                })
                .verify();
    }

    @Test
    void savePost_error() {
        PostRequest request = PostRequest.builder().usuarioId(1L).build();
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        when(userRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(userEntity));
        when(postRepositoryOutputPort.savePostOrUpdate(any(PostEntity.class))).thenReturn(Mono.error(new RuntimeException("DB error")));
        StepVerifier.create(postService.savePost(request))
                .expectError()
                .verify();
    }

    @Test
    void updatePost_success() {
        PostRequest request = PostRequest.builder().id(1L).build();
        PostEntity postEntity = PostEntity.builder().id(1L).build();
        when(postRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(postEntity));
        when(postRepositoryOutputPort.savePostOrUpdate(any(PostEntity.class))).thenReturn(Mono.just(postEntity));
        StepVerifier.create(postService.updatePost(request))
                .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                .verifyComplete();
    }

    @Test
    void updatePost_notFound() {
        PostRequest request = PostRequest.builder().id(1L).build();
        when(postRepositoryOutputPort.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(postService.updatePost(request))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void updatePost_error() {
        PostRequest request = PostRequest.builder().id(1L).build();
        PostEntity postEntity = PostEntity.builder().id(1L).build();
        when(postRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(postEntity));
        when(postRepositoryOutputPort.savePostOrUpdate(any(PostEntity.class))).thenReturn(Mono.error(new RuntimeException("DB error")));
        StepVerifier.create(postService.updatePost(request))
                .expectError()
                .verify();
    }

    @Test
    void deletePost_success() {
        PostEntity postEntity = PostEntity.builder().id(1L).build();
        when(postRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(postEntity));
        when(postRepositoryOutputPort.deleteById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(postService.deletePost(1L))
                .expectNextMatches(resp -> resp.getCodEntity().equals("1"))
                .verifyComplete();
    }

    @Test
    void deletePost_notFound() {
        when(postRepositoryOutputPort.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(postService.deletePost(1L))
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof code.fl.proyectoredsocial.domain.error.UserExceptions);
                    assertNotNull(error.getCause());
                    assertTrue(error.getCause() instanceof PostNotFoundException);
                })
                .verify();
    }

    @Test
    void deletePost_error() {
        PostEntity postEntity = PostEntity.builder().id(1L).build();
        when(postRepositoryOutputPort.findById(1L)).thenReturn(Mono.just(postEntity));
        when(postRepositoryOutputPort.deleteById(1L)).thenReturn(Mono.error(new RuntimeException("DB error")));
        StepVerifier.create(postService.deletePost(1L))
                .expectError()
                .verify();
    }
}
