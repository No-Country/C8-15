package com.nocountry.java_react.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Photo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    @Basic
    @Column(name = "original_name")
    private String originalName;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "path")
    private String path;

    @Column(name = "category")
    //@Enumerated(EnumType.STRING)
    private String category;

    @Column(name = "author")
    private String author;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "payment_link")
    private String paymentLink;

    @Column(name = "sales")
    private int sales;

    // RELATION PHOTO --> PHOTOGRAPHER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_photographer")
    @ToString.Exclude
    private Photographer photographer;

    // RELATION PHOTO --> BUYER
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH}, fetch = FetchType.LAZY, targetEntity = Buyer.class)
    @JoinTable(
            name = "buyer_photo",
            joinColumns = @JoinColumn(name = "id_photo"),
            inverseJoinColumns = @JoinColumn(name = "id_buyer"))
    @ToString.Exclude
    private List<Buyer> buyers = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated = new Date();

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Photo photo = (Photo) o;
        return id != null && Objects.equals(id, photo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}