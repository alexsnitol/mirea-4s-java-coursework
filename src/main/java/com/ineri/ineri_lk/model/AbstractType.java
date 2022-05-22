package com.ineri.ineri_lk.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public abstract class AbstractType extends AbstractModel {

    @Getter
    @Setter
    @Column
    private String name;

}
