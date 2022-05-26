package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Kozlov Alexander
 */

@Entity
@Table(name = "forms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EState state;
    private String adminComment;
    private Double area;
    private Integer floor;
    private Integer maxFloor;
    private Integer roomSize;
    private String description;
    private float price;
    @Column(name = "datetime_created")
    private LocalDateTime dateTimeCreated = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_type_id")
    private HouseType houseType;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "renovation_type_id")
    private RenovationType renovationType;

    @ManyToOne
    @JoinColumn(name = "estate_object_type_id")
    private EstateObjectType estateObjectType;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
