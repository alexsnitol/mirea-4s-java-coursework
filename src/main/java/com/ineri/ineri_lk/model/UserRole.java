package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Kozlov Alexander
 */


@Table(name = "user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRole {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;
}
