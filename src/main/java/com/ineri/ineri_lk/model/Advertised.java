package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Slotin Alexander (@alexsnitol)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "advertiseds")
public class Advertised extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "estate_object_id")
    private EstateObject estateObject;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String description;
    private double price;
    @ManyToOne
    @JoinColumn(name = "advertised_status_id")
    AdvertisedStatus advertisedStatus;
    @Column(name = "datetime_created")
    LocalDateTime dateTimeCreated;
    @OneToMany(mappedBy = "advertised")
    List<AdvertisedPhoto> advertisedPhoto;

    public void setNowDateTime() {
        this.dateTimeCreated = LocalDateTime.now();
    }

}
