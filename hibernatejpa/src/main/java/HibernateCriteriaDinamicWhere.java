import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.djcortez.hibernatejpa.entities.User;
import org.djcortez.hibernatejpa.util.JpaUtil;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateCriteriaDinamicWhere {
    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese el estado: ");
        String active = scanner.nextLine();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<User> from = query.from(User.class);

        List<Predicate> filters = new ArrayList<>();
        if (name != null && !name.isEmpty())
            filters.add(criteriaBuilder.equal(from.get("name"), name));

        if (email != null && !email.isEmpty())
            filters.add(criteriaBuilder.like(from.get("email"), "%" + email + "%"));

        if (active != null & !active.isEmpty())
            filters.add(criteriaBuilder.equal(from.get("active"),
                    (active.equals("true") || active.equals("yes")) ?
                            true: false
            ));

        query.multiselect(from.get("id"), from.get("name"), from.get("email"), from.get("active"))
                .where(criteriaBuilder.and(filters.toArray(Predicate[]::new)));

        System.out.println(" Registros encontrados: ");
        entityManager.createQuery(query)
                .getResultList().forEach(result -> {
                    System.out.print("[ id = " + result[0]);
                    System.out.print(" ,name = " + result[1]);
                    System.out.print(" ,email = " + result[2]);
                    System.out.print(" ,active = " + result[3] + "]\n");
                });

        entityManager.close();
    }
}
