package io.pouch.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false)
    private String name;

    public enum Values {
        MANAGER(1L),
        DEV(2L),
        ENTERPRISE(3L),
        INDIE_CREATOR(4L),
        USER(5L);

        long roleId;

        Values(long roleId) {this.roleId = roleId;}

        public long getRoleId() {
            return roleId;
        }
    }
}
