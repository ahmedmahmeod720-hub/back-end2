package com.backend.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * كلاس المحافظة - يمثل صف واحد في جدول governorates
 * يحتوي على اسم المحافظة وسعر المشال (النقل)
 */
@Entity
@Table(name = "governorates")
public class Governorate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double freight;

    // Constructor فاضي - Spring يحتاجه
    public Governorate() {
    }

    // Getters و Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }
}
