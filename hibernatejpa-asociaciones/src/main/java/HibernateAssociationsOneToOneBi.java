import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Client;
import org.djcortez.hibernatejpa.entities.ClientDetail;
import org.djcortez.hibernatejpa.util.JpaUtil;

public class HibernateAssociationsOneToOneBi {
    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        try{
            entityManager.getTransaction().begin();
            Client client = new Client();
            client.setName("LALO");
            client.setPaymentForm("CREDIT");

            ClientDetail detail = new ClientDetail();
            detail.setClient(client);
            detail.setAccumulatePoints(500L);
            detail.setPrime(false);

            client.setDetail(detail);
            entityManager.persist(client);

            entityManager.getTransaction().commit();

            System.out.println("Datos guardados satisfactoriamente");

        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
