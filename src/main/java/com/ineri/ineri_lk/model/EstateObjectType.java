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
@Table(name = "estate_object_types")
public class EstateObjectType extends AbstractType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
