package code.fl.proyectoredsocial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostListResponse {
    private List<Posts> posts;
    private ErrorResponse errorResponse;
}
