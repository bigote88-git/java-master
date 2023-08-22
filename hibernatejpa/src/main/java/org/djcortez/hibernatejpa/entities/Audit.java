package org.djcortez.hibernatejpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Embeddable
public class Audit {


    private LocalDateTime created;

    private LocalDateTime updated;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

}
