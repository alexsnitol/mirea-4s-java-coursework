package com.ineri.ineri_lk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "advertised_statuses")
public class AdvertisedStatus extends AbstractModel {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
