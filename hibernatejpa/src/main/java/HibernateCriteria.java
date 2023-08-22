import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.djcortez.hibernatejpa.entities.User;
import org.djcortez.hibernatejpa.util.JpaUtil;
import org.djcortez.hibernatejpa.util.PrintUtil;
import org.hibernate.Criteria;

import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();
/*
        PrintUtil.headerTitle("SELECT usando criteriaQuery");
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteria.createQuery(User.class);
        Root<User> from = query.from(User.class);

        query.select(from);
        List<User> queryUsers = entityManager.createQuery(query).getResultList();
        queryUsers.forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con where");
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteria.createQuery(User.class);
        Root<User> from = query.from(User.class);

        ParameterExpression<String> nameParameter = criteria.parameter(String.class, "pName");

        query.select(from)
                .where(criteria.equal(from.get("active"), true))
                .where(criteria.equal(from.get("name"), nameParameter));

        List<User> userActives = entityManager
                .createQuery(query)
                .setParameter(nameParameter, "djcortez")
                .getResultList();
        userActives.forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con like");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        ParameterExpression<String> likeName = criteriaBuilder.parameter(String.class, "likeName");

        query.select(from).where(criteriaBuilder.like(
                criteriaBuilder.upper(from.get("name")),
                criteriaBuilder.upper(likeName)
        ));

        List<User> userList = entityManager.createQuery(query)
                .setParameter(likeName.getName(), "%or%")
                .getResultList();
        userList.forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con between");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        ParameterExpression<Long> fromRange = criteriaBuilder.parameter(Long.class, "fromRange");
        ParameterExpression<Long> toRange = criteriaBuilder.parameter(Long.class, "toRange");

        query.select(from).where(criteriaBuilder.between(
                from.get("id"), fromRange, toRange
        ));

        List<User> userList = entityManager.createQuery(query)
                .setParameter(fromRange.getName(), 2L)
                .setParameter(toRange.getName(), 5L)
                .getResultList();
        userList.forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con in");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        query.select(from).where(
                from.get("name").in(
                Arrays.asList("rchamorro", "zato-1")));

        List<User> userList = entityManager.createQuery(query)
                        .getResultList();
        userList.forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con mayor que/mayor igual");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        ParameterExpression<Long> filterID = criteriaBuilder.parameter(Long.class, "filterID");

        query.select(from).where(
                criteriaBuilder.ge(from.get("id"),
                        filterID)
        );

        List<User> listUsers = entityManager
                .createQuery(query)
                .setParameter(filterID, 4L)
                .getResultList();
        listUsers.forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con operador and");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        Predicate andName = criteriaBuilder.equal(from.get("name"), "zato-1");
        Predicate andId = criteriaBuilder.equal(from.get("id"), 5l);

        query.select(from).where(criteriaBuilder.and(andName, andId));
        entityManager.createQuery(query).getResultList().forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con operador or");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        Predicate andId = criteriaBuilder.greaterThan(from.get("id"), 4L);
        Predicate orName = criteriaBuilder.equal(from.get("name"), "zato-1");
        Predicate orEmail = criteriaBuilder.equal(from.get("email"), "zato1@gmail.com");

        query.select(from).where(criteriaBuilder.and(andId, criteriaBuilder.or(orName, orEmail)));
        entityManager.createQuery(query).getResultList().forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con equal");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        ParameterExpression<Long> filterID = criteriaBuilder.parameter(Long.class, "filterID");

        query.select(from).where(criteriaBuilder.equal(from.get("id"), filterID));
        System.out.println(entityManager
                        .createQuery(query)
                        .setParameter(filterID, 1L)
                        .getSingleResult().toString());


        PrintUtil.headerTitle("SELECT usando criteriaQuery con distinct, concat y order by");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> queryString = criteriaBuilder.createQuery(String.class);
        Root<User> from = queryString.from(User.class);

        Expression<String> concatField = criteriaBuilder.concat(from.get("name"), " - concat");

        queryString.select(concatField)
                //.orderBy(criteriaBuilder.desc(from.get("name")));
                .distinct(true);

        entityManager.createQuery(queryString).getResultList().forEach(System.out::println);

        PrintUtil.headerTitle("SELECT usando criteriaQuery con multiselect");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<User> from = query.from(User.class);

        ParameterExpression<String> likeName = criteriaBuilder.parameter(String.class, "likeName");
        Predicate andId = criteriaBuilder.greaterThan(from.get("id"), 3L);
        Predicate andLikeEmail = criteriaBuilder.like(
                from.get("email"), likeName);

        query.multiselect(from.get("id"), from.get("name"), from.get("active"), from.get("email"))
                .where(criteriaBuilder.and(andId, andLikeEmail));

        entityManager.createQuery(query)
                .setParameter(likeName.getName(), "%@mail%")
                .getResultList()
                        .forEach(record -> {
                            System.out.print("[id = " + record[0]);
                            System.out.print(", name = " + record[1]);
                            System.out.print(", active = " + record[2]);
                            System.out.print(", email = " + record[3] + "]\n");
                        });
*/
        PrintUtil.headerTitle("SELECT usando criteriaQuery con funciones de agregacion");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<User> from = query.from(User.class);

        query.multiselect(
                criteriaBuilder.count(from.get("name")),
                criteriaBuilder.countDistinct(from.get("name")),
                criteriaBuilder.min(from.get("id")),
                criteriaBuilder.max(from.get("id")));

        entityManager.createQuery(query)
                .getResultList()
                .forEach(result -> {
                    System.out.print("count : " + result[0]);
                    System.out.print(", distinct count : " + result[1]);
                    System.out.print(", min : " + result[2]);
                    System.out.print(", max : " + result[3]);
                });

        entityManager.close();
    }
}
