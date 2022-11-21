package com.nocountry.java_react.model;

import com.nocountry.java_react.commons.enums.EUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name = "User")
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    protected String id;

    @Column(name = "name")
    protected String name;

    @Column(name = "surname")
    protected String surname;

    @Column(name = "document", unique = true)
    protected String document;

    @Column(name = "email", unique = true)
    protected String email;

    @Column(name = "user_name", unique = true)
    protected String userName;

    @Column(name = "password")
    protected String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    protected EUserRole role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    protected Date created = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = " updated")
    protected Date updated = new Date();

    @Column(name = "deleted")
    protected boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}