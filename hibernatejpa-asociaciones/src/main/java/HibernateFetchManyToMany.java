import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Student;
import org.djcortez.hibernatejpa.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToMany {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        List<Student> students =
                entityManager.createQuery("select s from Student s left join fetch s.courses", Student.class).getResultList();

        // lista de estudiantes

        System.out.println("**********************************");
        System.out.println("* LISTA DE ESTUDIANTES ***********");
        System.out.println("**********************************");
        students.forEach(s -> {
            System.out.println(s.getName());

            System.out.println("CURSO -> ----------------------");
            s.getCourses().forEach(
                    c -> System.out.println(c.getName())
            );
        });

        entityManager.close();
    }
}
