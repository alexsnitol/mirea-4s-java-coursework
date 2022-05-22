package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "advertiseds")
public class Advertised extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "estate_objects_id")
    private EstateObject estateObject;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String description;
    private float price;
    @ManyToOne
    @JoinColumn(name = "advertised_status_id")
    AdvertisedStatus advertisedStatus;
    LocalDateTime dateTimeCreated;

}
