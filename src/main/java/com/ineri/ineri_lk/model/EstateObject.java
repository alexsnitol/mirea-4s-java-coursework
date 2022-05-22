package com.ineri.ineri_lk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "estate_objects")
public class EstateObject extends AbstractModel {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Getter
    @Setter
    @Column
    private float area;

    @Getter
    @Setter
    @Column
    private int floor;

    @Getter
    @Setter
    @Column
    private int maxFloor;

    @Getter
    @Setter
    @Column
    private int roomSize;

    @Getter
    @Setter
    @Column
    private LocalDateTime dateTimeCreated;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "house_type_id")
    private HouseType houseType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "renovation_type_id")
    private RenovationType renovationType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "estate_object_type_id")
    private EstateObjectType estateObjectType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
