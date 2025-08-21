package code.fl.proyectoredsocial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    private Long id;
    private String username;
    private String password;
    private String rol;
    private String nombre;
    private String biografia;
}
