package code.fl.proyectoredsocial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostsResponce {
    private Integer codResponse;
    private String messageResponse;
    private String codEntity;
}
