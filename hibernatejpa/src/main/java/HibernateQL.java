import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.User;
import org.djcortez.hibernatejpa.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        headerTitle("1. Consultar todos");
        List<User> users = entityManager.createQuery("select u from User u", User.class)
                .getResultList();
        users.forEach(System.out::println);

        headerTitle("2. Consultar por id ");
        User user = entityManager.createQuery("select u from User u where u.id=:userid", User.class)
                .setParameter("userid", 1L)
                .getSingleResult();
        System.out.println(user);

        headerTitle("3. Consultar por un campo ");
        String name = entityManager.createQuery("select u.name from User u where u.id=:userid", String.class)
                .setParameter("userid", 2L)
                .getSingleResult();
        System.out.println(name);

        headerTitle("4. Consultar por varios campos ");
        Object[] fields = entityManager
                .createQuery("select u.id, u.name, u.email from User u where u.id=:userid", Object[].class)
                .setParameter("userid", 1L)
                .getSingleResult();
        printObjectRecord(fields);

        headerTitle("5. Consultar lista por varios campos ");
        List<Object[]> listFields = entityManager
                .createQuery("select u.id, u.name, u.email from User u", Object[].class)
                .getResultList();
        listFields.forEach(HibernateQL::printObjectRecord);

        headerTitle("6. Consultar por Usuario y otro campo ");
        List<Object[]> listFields2 = entityManager
                .createQuery("select u, u.email from User u", Object[].class)
                .getResultList();
        listFields2.forEach(record -> {
            System.out.println(record[0]);
            System.out.print(", campo " + record[1] + "\n");
        });

        headerTitle("7. Consultar por una clase personalizada ");
        List<CustomQuery> listCustomQuery = entityManager
                .createQuery("select new CustomQuery(u.id, u.name, u.email) from User u", CustomQuery.class)
                .getResultList();
        listCustomQuery.forEach(System.out::println);

        headerTitle("8. Consulta usando DISTINCT ");
        List<String> distinctUserNames = entityManager.createQuery("select distinct(u.name) from User u", String.class)
                .getResultList();
        distinctUserNames.forEach(System.out::println);

        headerTitle("9. Consulta usando COUNT ");
        Long countRecords = entityManager.createQuery("select count(distinct(u.name)) from User u", Long.class)
                .getSingleResult();
        System.out.println("countRecords = " + countRecords);

        headerTitle("10. Consulta usando CONCAT ");
        List<String> concatContactInfo = entityManager
                .createQuery("select distinct(concat(u.name, ' - ', u.email))  from User u", String.class)
                .getResultList();
        concatContactInfo.forEach(System.out::println);

        headerTitle("11. Consulta usando LIKE ");
        List<String> likeQuery = entityManager
                .createQuery("select u.name from User u where upper(u.name) like :search", String.class)
                .setParameter("search", "%or%".toUpperCase())
                .getResultList();
        likeQuery.forEach(System.out::println);

        headerTitle("12. Consulta usando BETWEEN ");
        List<User> betweenQuery = entityManager
                .createQuery("select u from User u where u.id between 2 and 3", User.class)
                .getResultList();
        betweenQuery.forEach(System.out::println);

        headerTitle("13. Consulta usando ORDER BY ");
        List<User> orderByQuery = entityManager
                .createQuery("select u from User u order by u.name", User.class)
                .getResultList();
        orderByQuery.forEach(System.out::println);

        headerTitle("14. Consulta usando funciones de agregacion ");
        /*List<AgregationQueryResult> aggregationFunctionQuery = entityManager
                .createQuery("select new AgregationQueryResult(count(u.id), max(u.id), min(u.id)) from User u", AgregationQueryResult.class)
                .getResultList();
        aggregationFunctionQuery.forEach(System.out::println);*/

        List<Object[]> aggregationFunctionQuery = entityManager
                .createQuery("select count(u.id), max(u.id), min(u.id) from User u", Object[].class)
                .getResultList();
        aggregationFunctionQuery.forEach(x -> {
            System.out.println("COUNT : " + x[0]);
            System.out.println("MAX : " + x[1]);
            System.out.println("MIN : " + x[2]);
        });

        headerTitle("15. Subconsultas ");
        Object[] lastUser = entityManager
                .createQuery("select u.id, u.name from User u where u.id = " +
                        "(select max(u.id) from User u)", Object[].class)
                .getSingleResult();
        System.out.println("Ultimo usuario registrado : " + lastUser[0] + " - " + lastUser[1]);

        entityManager.close();
    }


    public static void headerTitle(String title) {
        System.out.println("================================================");
        System.out.println(title);
        System.out.println("================================================");
    }

    public static void printObjectRecord(Object[] record) {
        System.out.print("[id = " + record[0]);
        System.out.print(", name = " + record[1]);
        System.out.print(", email = " + record[2]);
        System.out.print("]\n");
    }
}

class AgregationQueryResult{
    private Long count;
    private Long max;
    private Long min;

    public AgregationQueryResult() {
    }

    public AgregationQueryResult(Long count, Long max, Long min) {
        this.count = count;
        this.max = max;
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "AgregationQueryResult[" +
                "count=" + count +
                ", max=" + max +
                ", min=" + min +
                ']';
    }
}
class CustomQuery {
    private Long id;
    private String name;
    private String email;

    public CustomQuery() {
    }

    public CustomQuery(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ']';
    }
}
