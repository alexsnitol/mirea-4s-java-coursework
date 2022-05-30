package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Kozlov Alexander
 * @version 2.0
 * Примечание: следует добавить валидацию форм с помощью @Valid в контроллере!
 */

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Поле не может быть пустым")
//    @Length(max = 31, message = "В поле не может быть > 31")
    private String username;

//    @NotBlank(message = "Поле не может быть пустым")
//    @Length(max = 31, message = "В поле не может быть > 31")
    private String password;

//    @NotBlank(message = "Поле не может быть пустым")
//    @Length(max = 31, message = "В поле не может быть > 31")
    private String surname;

//    @NotBlank(message = "Поле не может быть пустым")
//    @Length(max = 31, message = "В поле не может быть > 31")
    private String name;

//    @NotBlank(message = "Поле не может быть пустым")
    private String patronymic;

//    @NotBlank(message = "Поле не может быть пустым")
//    @Email(message = "Поле введено не верно")
    private String email;

//    @NotBlank(message = "Поле должно содержать 13 цифр")
    private String phoneNumber;

    private String photoPath;

    @Column(name = "datetime_created")
    private LocalDateTime datetimeCreated = LocalDateTime.now();

    @Transient
    private Boolean isActive;

    @Transient
    private String confirmPassword;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "advertised", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public void setActive(Boolean active) {
        this.isActive = active;
    }
}
