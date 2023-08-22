package org.djcortez.hibernatejpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @Column(name="invoiceid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "numeric default 0")
    private BigDecimal total;

    // JoinColumn es para establecer la llave foranea
    @ManyToOne
    @JoinColumn(name="clientid")
    private Client client;

    public Invoice() {
    }

    public Invoice(Long id, String description, BigDecimal total, Client client) {
        this.id = id;
        this.description = description;
        this.total = total;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
