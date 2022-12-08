package com.nocountry.java_react.auth.model;

import com.nocountry.java_react.commons.enums.EUserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "User")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserAuthentication implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    protected final Date created = new Date();
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    protected String id;
    @Column(name = "name")
    protected String name;
    @Column(name = "surname")
    protected String surname;
    @Column(name = "email", unique = true)
    protected String email;
    @Column(name = "password")
    protected String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    protected EUserRole role;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = " updated")
    protected Date updated = new Date();

    @Column(name = "deleted")
    protected boolean deleted = Boolean.FALSE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public String getPassword() {
        return password;
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
        return isDeleted();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAuthentication that = (UserAuthentication) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}