package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.model.User;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.utils.UserUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;

class UserServiceTest {

}