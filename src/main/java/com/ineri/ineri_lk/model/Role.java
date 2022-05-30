package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Kozlov Alexander
 * @version 1.0
 */

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Role extends AbstractModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }

    public String getAuthority() {
        return this.name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.toString().equals(role.name.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
