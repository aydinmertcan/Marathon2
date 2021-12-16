package com.bilgeadam.marathon.entity;

import com.bilgeadam.marathon.enums.EGenre;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "albums")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

/*    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] picture;*/

    @Column(name = "discount_rate")
    private double discountRate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private ArtistEntity artist;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Enumerated(value = EnumType.STRING)
    private EGenre genre;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    // ALLARGS CONSTRUCTOR
    public AlbumEntity(String name, double price, double discountRate, EGenre genre) {
        this.name = name;
        this.price = price;
        this.discountRate = discountRate;
        this.genre = genre;
    }
}

