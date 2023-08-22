import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Client;
import org.djcortez.hibernatejpa.entities.Invoice;
import org.djcortez.hibernatejpa.util.JpaUtil;

import java.math.BigDecimal;

public class HibernateAssociationsOneToManyBi {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Client client = new Client();
            client.setName("Pepito");
            client.setPaymentForm("CREDITO");

            Invoice invoice1 = new Invoice();
            invoice1.setDescription("Compra de ps4");
            invoice1.setTotal(new BigDecimal("250"));

            Invoice invoice2 = new Invoice();
            invoice2.setDescription("Compra de switch");
            invoice2.setTotal(new BigDecimal("350"));

            client.addInvoice(invoice1);
            client.addInvoice(invoice2);

            entityManager.persist(client);
            entityManager.getTransaction().commit();

            System.out.println("Cliente y sus facturas guardadas exitosamente");
            System.out.println("=============================================");
            System.out.println("Eliminando factura");

            // eliminando una factura
            entityManager.getTransaction().begin();
            Invoice invoiceToDelete = entityManager.find(Invoice.class, invoice1.getId());

            // tenemos que removerla del contexto tanto de la lista como asignar a null
            // la relacion con el cliente en el objeto a eliminar
            client.getInvoices().remove(invoiceToDelete);
            invoiceToDelete.setClient(null);

            // como ya esta en el contexto no es necesario hacer un merge
            // simplemente enviamos hacemos commit
            entityManager.getTransaction().commit();

            System.out.println("Factura eliminada satisfactoriamente");

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}
