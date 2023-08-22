import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.djcortez.hibernatejpa.entities.Address;
import org.djcortez.hibernatejpa.entities.Client;
import org.djcortez.hibernatejpa.util.JpaUtil;

public class HibernateAssociationsOneToMany {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            Client client = new Client();
            client.setName("La rana pepe");
            client.setPaymentForm("CR");

            Address addressOld = new Address("Managua/Nicaragua", "12345");
            Address addressNew = new Address("Masaya/Nicaragua", "65489");

            client.getAddresses().add(addressOld);
            client.getAddresses().add(addressNew);

            entityManager.persist(client);
            entityManager.getTransaction().commit();

            System.out.println("Cliente guardado exitosamente");

            System.out.println("Eliminando direccion antigua");

            entityManager.getTransaction().begin();
            client.getAddresses().remove(addressOld);
            entityManager.getTransaction().commit();

            System.out.println("Direccion antigua eliminada");

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
