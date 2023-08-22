import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.djcortez.hibernatejpa.entities.Client;
import org.djcortez.hibernatejpa.entities.Invoice;
import org.djcortez.hibernatejpa.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneApiCriteria {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Invoice> query = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> from = query.from(Invoice.class);
        Fetch<Invoice, Client> client = from.fetch("client", JoinType.LEFT);
        client.fetch("detail", JoinType.LEFT);

        List<Invoice> invoiceList = entityManager.createQuery(query).getResultList();

        entityManager.close();
    }
}
