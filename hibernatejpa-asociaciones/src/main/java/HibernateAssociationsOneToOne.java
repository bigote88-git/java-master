import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Client;
import org.djcortez.hibernatejpa.entities.ClientDetail;
import org.djcortez.hibernatejpa.util.JpaUtil;

public class HibernateAssociationsOneToOne {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Client client = new Client();
            client.setName("DOGURA-SAN");
            client.setPaymentForm("DEBIT");
            entityManager.persist(client);

            ClientDetail detail = new ClientDetail();
            detail.setPrime(true);
            detail.setAccumulatePoints(1000L);
            detail.setClient(client);
            entityManager.persist(detail);

            entityManager.getTransaction().commit();

            System.out.println("DATOS GUARDADOS");
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
