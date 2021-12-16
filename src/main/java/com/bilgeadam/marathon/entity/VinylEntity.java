package com.bilgeadam.marathon.entity;

import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.enums.EVinlyDiameter;
import com.bilgeadam.marathon.enums.EVinylRPM;
import com.bilgeadam.marathon.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "vinylies")
public class VinylEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private long id;

        @Column(name = "name")
        private String name;

        @Column(name = "price")
        private double price;

        @Column(name = "discount_rate")
        private double discountRate;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToOne
        @JoinColumn(name = "artist_id", referencedColumnName = "id")
        private ArtistEntity artist;


        @Enumerated(value = EnumType.STRING)
        private EGenre genre;

        @Temporal(value = TemporalType.TIMESTAMP)
        @CreationTimestamp
        @Column(name = "created_date", updatable = false)
        private Date createdDate;

        @Enumerated(value = EnumType.STRING)
        private EVinlyDiameter diameter;

        @Enumerated(value = EnumType.STRING)
        private EVinylRPM speed;


        public VinylEntity(String name, double price, double discountRate, EGenre genre, EVinlyDiameter diameter, EVinylRPM speed) {
                this.name = name;
                this.price = price * discountRate;
                this.discountRate = discountRate;
                this.genre = genre;
                this.diameter = diameter;
                this.speed = speed;
        }
}

