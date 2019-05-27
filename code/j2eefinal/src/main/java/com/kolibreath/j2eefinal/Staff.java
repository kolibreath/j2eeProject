package com.kolibreath.j2eefinal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="staff")
public class Staff {

    @Id
    private int staffId;

    @Column(length = 255)
    private int staffType;

    @Column(length = 255)
    private String name;


    @Column(length = 255)
    private String title;
}
