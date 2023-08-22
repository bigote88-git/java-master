import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Country;
import org.djcortez.hibernatejpa.services.CountryService;
import org.djcortez.hibernatejpa.services.CountryServiceImpl;
import org.djcortez.hibernatejpa.util.JpaUtil;

import javax.swing.*;
import java.util.List;

public class HibernateCountryExample {

    public static void main(String[] args) {

        EntityManager entityManager = JpaUtil.getEntityManager();
        CountryServiceImpl countryService = new CountryServiceImpl(entityManager);

        List<Country> countries = countryService.getAll();

        if (countries.size() > 0) {
            String option = JOptionPane.showInputDialog(getStringMenuWithRecords());
            switch (Integer.parseInt(option)) {
                case 1:
                    JOptionPane.showMessageDialog(null, getCountriesToString(countries));
                    break;
                case 2:
                    saveCountry(countryService);
                    break;
                case 3:
                    deleteCountry(countryService, countries);
                    break;
                case 4:
                    System.exit(0);
                    break;
            }

        } else {
            String option = JOptionPane.showInputDialog(getStringMenu());
            switch (Integer.parseInt(option)) {
                case 1:
                    saveCountry(countryService);
                    break;
                case 2:
                    System.exit(0);
            }
        }

        entityManager.close();
    }

    public static void deleteCountry(CountryService countryService, List<Country> countries) {
        Long id = new Long(JOptionPane.showInputDialog(getCountriesToString(countries) + "\n Ingrese el id del pais a eliminar"));

        try {
            if (id != null) {
                countryService.delete(id);
                JOptionPane.showMessageDialog(null, "Pais eliminado correctamente");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el pais: " + ex.getMessage());
        }
    }

    public static void saveCountry(CountryService countryService) {
        Country country = new Country();
        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        country.setName(name);
        country.setActive(true);
        try {
            countryService.save(country);
            JOptionPane.showMessageDialog(null, "Pais registrado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el pais: " + ex.getMessage());
        }
    }

    public static String getCountriesToString(List<Country> countries) {
        StringBuilder stringCountries = new StringBuilder();
        countries.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .distinct().forEach(country -> {
                    stringCountries.append("[" + country.getId() + "]" + country.getName())
                            .append("\n");
                });

        return stringCountries.toString();
    }

    public static String getStringMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("No existen paises registrados, seleccione una opcion: ")
                .append("\n 1. Crear Pais ")
                .append("\n 2. Salir ");

        return menu.toString();
    }

    public static String getStringMenuWithRecords() {
        StringBuilder menu = new StringBuilder();
        menu.append("Seleccione una opcion: ")
                .append("\n 1. Mostrar paises ")
                .append("\n 2. Crear Pais ")
                .append("\n 3. Eliminar pais")
                .append("\n 4. Salir ")
                .append("\n");

        return menu.toString();
    }
}
