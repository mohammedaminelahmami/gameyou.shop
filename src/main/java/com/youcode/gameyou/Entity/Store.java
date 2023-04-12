package com.youcode.gameyou.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imagepath")
    private String imagePath;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @OneToMany(mappedBy = "store")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Seller> sellers = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Order_> orders = new ArrayList<>();
}