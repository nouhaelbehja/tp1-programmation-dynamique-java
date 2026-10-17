package dao;

public class DaoImpl implements IDao {

    private final String source = "Source locale";
    private int nombreLectures = 0;

    public DaoImpl() {
        System.out.println("[DAO] Initialisation : " + source);
    }

    @Override
    public double getValue() {
        nombreLectures++;
        System.out.println("[DAO] Source : " + source);
        System.out.println("[DAO] Lecture numero : " + nombreLectures);
        return 100.0;
    }
}