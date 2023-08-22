package org.djcortez.hibernatejpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name="addresses")
public class Address {

    @Id
    @Column(name = "addressid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address() {
    }

    public Address(String street, String number) {
        this.street = street;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address [" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ']';
    }
}
