package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Presentation2 {

    public static void main(String[] args) {

        try {
            System.out.println("=== DEMARRAGE DU MICROSERVICE ===");

            String[] classNames = loadClassNames("config.txt");

            // Chargement dynamique du DAO
            IDao dao = (IDao) instantiateClass(classNames[0]);

            System.out.println(
                    "[CONTAINER] DAO charge : " + dao.getClass().getName()
            );

            // Chargement dynamique du metier
            IMetier metier = (IMetier) instantiateClass(classNames[1]);

            System.out.println(
                    "[CONTAINER] Metier charge : " + metier.getClass().getName()
            );

            // Injection par reflexion
            injectDependency(metier, "setDao", dao);

            // Execution du calcul
            double resultat = metier.calcul();

            System.out.println("-----------------------------");
            System.out.println("RESULTAT FINAL : " + resultat);
            System.out.println("SOURCE UTILISEE : "
                    + dao.getClass().getSimpleName());
            System.out.println("-----------------------------");

        } catch (Exception e) {
            System.err.println("[ERREUR] " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String[] loadClassNames(String fileName)
            throws Exception {

        String[] classNames = new String[2];

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            for (int i = 0; i < 2; i++) {
                String line = reader.readLine();

                if (line == null || line.trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Configuration incomplete"
                    );
                }

                classNames[i] = line.trim();
            }
        }

        return classNames;
    }

    private static Object instantiateClass(String className)
            throws Exception {

        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();

        return constructor.newInstance();
    }

    private static void injectDependency(
            Object target,
            String setterName,
            IDao dependency) throws Exception {

        Method method = target.getClass()
                .getMethod(setterName, IDao.class);

        method.invoke(target, dependency);

        System.out.println("[CONTAINER] Injection terminee");
    }
}