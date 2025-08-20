package code.fl.proyectoredsocial.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column("id")
    private Long id;
    private String username;
    private String password;
    private String rol;
    private String nombre;
    private String bio;
}
