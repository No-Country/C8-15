package com.nocountry.java_react.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Buyer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Buyer extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // RELATION BUYER --> PHOTO
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Photo> photos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Buyer buyer = (Buyer) o;
        return id != null && Objects.equals(id, buyer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}