package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Slotin Alexander (@alexsnitol)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "estate_objects")
public class EstateObject extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float area;
    private int floor;
    private int maxFloor;
    private int roomSize;
    private LocalDateTime dateTimeCreated;

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

    public void setNowDateTime() {
        this.dateTimeCreated = LocalDateTime.now();
    }

}
