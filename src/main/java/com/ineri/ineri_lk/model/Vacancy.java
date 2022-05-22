package com.ineri.ineri_lk.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
public class Vacancy extends AbstractModel {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String keywords;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Getter
    @Setter
    private float summary;
    @Getter
    @Setter
    private String textDescription;
    @Getter
    @Setter
    private String textStudy;
    @Getter
    @Setter
    private String textResponsibilities;
    @Getter
    @Setter
    private String textRequirements;
    @Getter
    @Setter
    private String textFeatures;
    @Getter
    @Setter
    private String textSummary;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User contactUser;
    @Getter
    @Setter
    private boolean isActive;

}
