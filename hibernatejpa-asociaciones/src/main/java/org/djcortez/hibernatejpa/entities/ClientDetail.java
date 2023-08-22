package org.djcortez.hibernatejpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="client_detail")
public class ClientDetail {

    @Id
    @Column(name = "clientdetail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean prime;

    @Column(name ="accumulate_points", columnDefinition = "numeric default 0")
    private Long accumulatePoints;

    @OneToOne
    @JoinColumn(name = "clientid")
    private Client client;

    public ClientDetail() {
    }

    public ClientDetail(Boolean prime, Long accumulatePoints) {
        this.prime = prime;
        this.accumulatePoints = accumulatePoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrime() {
        return prime;
    }

    public void setPrime(Boolean prime) {
        this.prime = prime;
    }

    public Long getAccumulatePoints() {
        return accumulatePoints;
    }

    public void setAccumulatePoints(Long accumulatePoints) {
        this.accumulatePoints = accumulatePoints;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientDetail [" +
                "id=" + id +
                ", prime=" + prime +
                ", accumulatePoints=" + accumulatePoints +
                ']';
    }
}
