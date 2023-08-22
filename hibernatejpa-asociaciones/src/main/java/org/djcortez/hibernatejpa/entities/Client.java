package org.djcortez.hibernatejpa.entities;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name ="clientid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ClientDetail getDetail() {
        return detail;
    }

    public void setDetail(ClientDetail detail) {
        this.detail = detail;
    }

    @Column(nullable = false)
    private String name;
    @Column(name = "payment_form")
    private String paymentForm;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name="clientid")
    /*
    * si no se especifica la llave foranea crea una tabla intermedia entre ambas entidades
    * */
    @JoinTable(
            name="tbl_clients_addresses",
            joinColumns = @JoinColumn(name = "id_client"), // campo que se puede repetir
            inverseJoinColumns = @JoinColumn(name = "id_address"), // campo unico de la tabla foranea
            uniqueConstraints = @UniqueConstraint(columnNames = { "id_address" } // restriccion de la tabla foranea
            )
    )
    private List<Address> addresses;

    // para el mappedBy la tabla donde esta la foreign key es la dueña de la relacion
    // mappedBy debe apuntar al campo con la relacion foranea
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private List<Invoice> invoices;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetail detail;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Client() {
        addresses = new ArrayList<>();
        invoices = new ArrayList<>();
    }

    public Client(Long id, String name, String paymentForm, List<Address> addresses) {
        this();
        this.id = id;
        this.name = name;
        this.paymentForm = paymentForm;
        this.addresses = addresses;
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

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public void addInvoice(Invoice invoice){

        // establecemos la relacion cliente factura
        this.getInvoices().add(invoice);

        // establecemos la relacion factura cliente
        invoice.setClient(this);
    }
}
