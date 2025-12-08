package com.gic.rosm.Entity;

import com.gic.rosm.Entity.Enums.MenuCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private MenuCategory category;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;
}
