package com.ineri.ineri_lk.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public abstract class AbstractType extends AbstractModel {

    @Getter
    @Setter
    @Column
    private String name;

}
