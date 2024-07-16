package com.teamchallege.common.entities.catalog;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contentType")
    private String contentType;

    @Lob()
    @Column(length = 100000)
    private byte[] bytes;
}