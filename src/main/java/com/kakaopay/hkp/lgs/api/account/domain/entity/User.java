package com.kakaopay.hkp.lgs.api.account.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.domain.enums.AuthorityName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
@ToString
public class User {

    private static final long serialVersionUID = -763463602149143107L;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UserDto userDto) {
        this.username = userDto.getId();
        this.password = userDto.getPw();
        this.authorities.add(new Authority(AuthorityName.ROLE_USER));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "login", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();
}
