import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.User;
import org.djcortez.hibernatejpa.services.UserServiceImpl;
import org.djcortez.hibernatejpa.util.JpaUtil;

import javax.swing.*;

public class HibernateCrudExample {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();
        UserServiceImpl userService = new UserServiceImpl(entityManager);

        System.out.println(" LISTAR TODOS LOS USUARIOS");
        userService.getAll().forEach(System.out::println);

        System.out.println(" OBTENER USUARIO");
        Long id = new Long(JOptionPane.showInputDialog("Id del usuario"));
        if(id != null){
            User user = userService.get(id);
            user.setEmail("emailmodificado@gmail.com");

            userService.save(user);
            System.out.println("Usuario modificado con exito");
            userService.getAll().forEach(System.out::println);
        }
/*
        System.out.println("=============================================");
        System.out.println(" GUARDAR USUARIO");
        System.out.println("=============================================");
        User user = new User();
        user.setName("pepe");
        user.setEmail("pepelarana@gmail.com");
        user.setPassword("helloworld");
        user.setGender("M");
        user.setActive(true);

        userService.save(user);
        System.out.println(" USUARIO GUARDADO CON EXITO");
        System.out.println("=============================================");
        userService.getAll().forEach(System.out::println);
        */
/*
        System.out.println("=============================================");
        System.out.println(" ELIMINAR USUARIO");
        System.out.println("=============================================");
        Long idToDelete = new Long(JOptionPane.showInputDialog("Ingrese el id de usuario"));
        if(idToDelete != null){
            User userToDelete = userService.get(idToDelete);

            if(userToDelete != null){
                userService.delete(userToDelete.getId());
                System.out.println(" USUARIO ELIMINADO CON EXITO");
                userService.getAll().forEach(System.out::println);
            }else
                System.out.println(" NO SE ENCONTRO UN USUARIO CON ESE ID");

        }else
            System.out.println("NO SE PROPORCIONO UN ID VALIDO PARA ELIMINAR EL USUARIO");
*/
        entityManager.close();
    }
}
