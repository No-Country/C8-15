package com.nocountry.java_react.model;

import com.nocountry.java_react.commons.enums.EPhotoCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Photo")
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
    protected String id;

    @Basic
    @Column(name = "original_name")
    private String originalName;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "path")
    private String path;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private EPhotoCategory category;

    @Column(name = "author")
    private String author;

    @Column(name = "location")
    private String location;

    // RELATION PHOTO --> PHOTOGRAPHER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_photographer")
    @ToString.Exclude
    private Photographer photographer;

    // RELATION PHOTO --> BUYER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buyer")
    @ToString.Exclude
    private Buyer buyer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = " last_modification")
    private Date lastModification;

    @Column(name = "soft_delete")
    protected boolean softDelete = Boolean.FALSE;

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