import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Person;
import org.djcortez.hibernatejpa.util.JpaUtil;

public class HibernateFetchLazyJoinFetch {

    public static void main(String[] args) {

        // dentro de una consulta con usando fetch no se pueden poblar dos listas al mismo tiempo
        EntityManager entityManager = JpaUtil.getEntityManager();

        Person person = entityManager
                .createQuery("select p from Person p " +
                        "left join fetch p.addresses where p.id=:personid", Person.class)
                .setParameter("personid", 1L)
                .getSingleResult();

        System.out.println("************************************");
        System.out.println("IMPRIMIENDO PERSONA");
        System.out.println("************************************");
        System.out.println(person);

        /*
        System.out.println("************************************");
        System.out.println("IMPRIMIENDO DIRECCIONES");
        System.out.println("************************************");
        person.getAddresses().forEach(System.out::println);
*/
        entityManager.close();

        System.out.println("************************************");
        System.out.println("IMPRIMIENDO DIRECCIONES");
        System.out.println("************************************");
        person.getAddresses().forEach(System.out::println);
    }
}
