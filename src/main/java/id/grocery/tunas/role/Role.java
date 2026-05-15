package id.grocery.tunas.role;

import id.grocery.tunas.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
public class Role extends BaseModel {
    private String name;
}
