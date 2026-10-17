package dao;
import dao.IDao;

public class DaoImpl2 implements IDao {

    private final String source = "Service externe";
    private final double valeurExterne = 175.0;

    public DaoImpl2() {
        System.out.println("[DAO EXT] Connexion simulee au service externe");
    }

    @Override
    public double getValue() {
        System.out.println("[DAO EXT] Source : " + source);
        System.out.println("[DAO EXT] Valeur : " + valeurExterne);
        return valeurExterne;
    }
}