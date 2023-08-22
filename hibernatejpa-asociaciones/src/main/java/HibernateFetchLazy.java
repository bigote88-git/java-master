import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Person;
import org.djcortez.hibernatejpa.util.JpaUtil;

public class HibernateFetchLazy {

    public static void main(String[] args) {

        // Todas las asociaciones que terminan con Many son LAZY por defecto.
        EntityManager entityManager = JpaUtil.getEntityManager();

        Person person = entityManager.find(Person.class, 1L);
        if (person != null){
            System.out.println("************************************");
            System.out.println("IMPRIMIENDO PERSONA");
            System.out.println("************************************");
            System.out.println(person);

            System.out.println("************************************");
            System.out.println("IMPRIMIENDO DIRECCIONES");
            System.out.println("************************************");
            person.getAddresses().forEach(System.out::println);
        }

        entityManager.close();
    }
}
