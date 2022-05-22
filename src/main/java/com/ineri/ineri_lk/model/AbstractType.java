package com.ineri.ineri_lk.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public abstract class AbstractType extends AbstractModel {

    @Column
    private String name;

}
