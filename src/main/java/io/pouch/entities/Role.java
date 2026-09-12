package io.pouch.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(nullable = false)
    private String name;

    public enum Values {
        MANAGER(1L),
        PUBLISHER(2L),
        INDIE_CREATOR(3L),
        USER(4L);

        long roleId;

        Values(long roleId) {this.roleId = roleId;}

        public long getRoleId() {
            return roleId;
        }
    }

    @Override
    public @Nullable String getAuthority() {
        return name;
    }
}
