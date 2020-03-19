package com.enosnery.desafiojava.model;

import com.enosnery.desafiojava.request.PhoneRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String number;

    private String ddd;

    public Telephone(){

    }

    public Telephone(PhoneRequest phoneRequest){
        this.number = phoneRequest.number;
        this.ddd = phoneRequest.ddd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", ddd='" + ddd + '\'' +
                '}';
    }
}
