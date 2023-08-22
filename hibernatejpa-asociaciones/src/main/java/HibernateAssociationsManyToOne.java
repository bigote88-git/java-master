import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.djcortez.hibernatejpa.entities.Client;
import org.djcortez.hibernatejpa.entities.Invoice;
import org.djcortez.hibernatejpa.util.JpaUtil;

import java.math.BigDecimal;

public class HibernateAssociationsManyToOne {
    public static void main(String[] args) {

        System.out.println("Asociaciones ManyToOne");
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            /*Client client = new Client();
            client.setName("Pepito");
            client.setPaymentForm("DEBITO");
            entityManager.persist(client);
*/
            Client client = entityManager.find(Client.class, 1l);

            Invoice invoice = new Invoice();
            invoice.setDescription("Primera factura de pepito");
            invoice.setTotal(new BigDecimal("1500.50"));
            invoice.setClient(client);
            entityManager.persist(invoice);

            System.out.println("Registros guardados exitosamente");
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
        entityManager.getTransaction().rollback();
        ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
