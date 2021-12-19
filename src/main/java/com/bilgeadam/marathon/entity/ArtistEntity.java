package com.bilgeadam.marathon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "artists")
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "description")
    private String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DVDEntity> dvdEntities = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<VinylEntity> vinylEntities = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CDEntity> cdEntities = new HashSet<>();

    // ALLARGS CONSTRUCTOR
    public ArtistEntity(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public void addDVD(DVDEntity dvdEntity) {
        this.dvdEntities.add(dvdEntity);
    }

    public void addVinyl(VinylEntity vinylEntity) {
        this.vinylEntities.add(vinylEntity);
    }

    public void addCD(CDEntity cdEntity) {
        this.cdEntities.add(cdEntity);
    }

}
