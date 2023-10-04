package com.devking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Manufacture")
@NoArgsConstructor
@Getter
@Setter
public class Manufacture {

    @Id
    private Long id;

    @Column(name = "Name",nullable = false)
    @Size(min = 3,max = 128)
    private String name;

    @Column(name = "Location",nullable = false)
    private String location;

    @Column(name = "Employee")
    private Integer employee;

    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phoneList;

    public Manufacture(Long id, String name, String location, Integer employee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public void print(){
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Location: " + this.location);
        System.out.println("Employees: " + this.employee);
        //System.out.println("Country: " + this.country);
        //System.out.println("Quantity: " + this.quantity);
    }

}
