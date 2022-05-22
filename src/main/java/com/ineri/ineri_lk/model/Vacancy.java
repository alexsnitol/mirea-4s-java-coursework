package com.ineri.ineri_lk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vacancies")
public class Vacancy extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    private String name;
    private String keywords;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private float summary;
    private String textDescription;
    private String textStudy;
    private String textResponsibilities;
    private String textRequirements;
    private String textFeatures;
    private String textSummary;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User contactUser;
    private boolean isActive;

}
