package com.ineri.ineri_lk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "advertiseds")
public class Advertised extends AbstractModel {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "estate_objects_id")
    private EstateObject estateObject;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private float price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "advertised_status_id")
    AdvertisedStatus advertisedStatus;

    @Getter
    @Setter
    LocalDateTime dateTimeCreated;

}
