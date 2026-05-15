package id.grocery.tunas.user;

import id.grocery.tunas.base.BaseModel;
import id.grocery.tunas.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User extends BaseModel {
    @Column(unique = true)
    private String username;
    private String password;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

}
