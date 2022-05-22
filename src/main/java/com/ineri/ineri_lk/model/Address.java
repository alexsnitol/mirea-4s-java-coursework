package com.ineri.ineri_lk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address extends AbstractModel {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Getter
    @Setter
    @Column
    private String fullAddress;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
