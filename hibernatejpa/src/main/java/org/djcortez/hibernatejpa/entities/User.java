package org.djcortez.hibernatejpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "pass")
    private String password;
    private String gender;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "created", column = @Column(name = "created")),
            @AttributeOverride(name = "updated", column = @Column(name = "updated"))
    }
    )
    private Audit auditFields = new Audit();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "isactive")
    private Boolean active;

    public User() {
    }

    public User(Long id, String name, String email, String password, String gender, Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.active = active;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @PrePersist
    public void prePersist(){
        System.out.println("Generando fecha de creacion del registro desde clase Audit");
        //this.auditFields.setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Generando fecha de actualizacion del registro desde clase Audit");
        //this.auditFields.setUpdated(LocalDateTime.now());
    }


    @Override
    public String toString() {
        LocalDateTime createdAt = this.auditFields != null ? this.auditFields.getCreated() : null;
        LocalDateTime updatedAt = this.auditFields != null ? this.auditFields.getUpdated() : null;
        return "User [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", created=" + createdAt + "" +
                ", updated=" + updatedAt + "" +
                ", active=" + active +
                ']';
    }
}
