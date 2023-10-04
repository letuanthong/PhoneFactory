package com.devking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MobilePhone")
@NoArgsConstructor
@Getter @Setter
public class Phone {

    @Id
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private Integer price;

    @Column(name = "Color")
    private String color;

    @Column(name = "Country")
    private String country;

    @Column(name = "Quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture")
    private Manufacture manufacture;


    public Phone(Long id, String name, int price, String color, String country, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
    }

    public void print(){
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Price: " + this.price);
        System.out.println("Color: " + this.color);
        System.out.println("Country: " + this.country);
        System.out.println("Quantity: " + this.quantity);
    }
}
