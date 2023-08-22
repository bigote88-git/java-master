import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Course;
import org.djcortez.hibernatejpa.entities.Student;
import org.djcortez.hibernatejpa.util.EntityManagerHelper;
import org.djcortez.hibernatejpa.util.JpaUtil;

public class HibernateAssociationsManyToManyBi {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Student newStudent = new Student();
            newStudent.setName("Zato");
            newStudent.setAppe("1");

            Student oldStudent = new Student();
            oldStudent.setName("Other");
            oldStudent.setAppe("2");

            Course chemical = new Course();
            chemical.setName("Quimica");
            chemical.setTeacher("Pepito");

            Course mathematical = new Course();
            mathematical.setName("Matematicas");
            mathematical.setTeacher("Laura");

            newStudent.getCourses().add(chemical);
            newStudent.getCourses().add(mathematical);

            oldStudent.getCourses().add(chemical);

            entityManager.persist(newStudent);
            entityManager.persist(oldStudent);
            entityManager.getTransaction().commit();

            System.out.println("Alumno y cursos guardados");

            entityManager.getTransaction().begin();
            Course courseToDelete = entityManager.find(Course.class, chemical.getId());
            newStudent.removeCourse(courseToDelete);

            entityManager.getTransaction().commit();

            System.out.println("Curso eliminado exitosamente");

        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            entityManager.close();
        }
    }
}
