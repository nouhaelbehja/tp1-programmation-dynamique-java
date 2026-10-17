package metier;

import dao.IDao;

public class MetierImpl implements IMetier {

    private IDao dao;
    private double coefficient = 2.0;

    public MetierImpl() {
        System.out.println("[METIER] Service metier initialise");
    }

    public void setDao(IDao dao) {
        this.dao = dao;
        System.out.println("[METIER] Injection du DAO reussie");
    }

    public void setCoefficient(double coefficient) {
        if (coefficient <= 0) {
            throw new IllegalArgumentException(
                    "Le coefficient doit etre positif"
            );
        }
        this.coefficient = coefficient;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException(
                    "Le DAO n'a pas ete injecte"
            );
        }

        double valeur = dao.getValue();
        double resultat = valeur * coefficient;

        System.out.println("[METIER] Coefficient : " + coefficient);
        System.out.println("[METIER] Resultat : " + resultat);

        return resultat;
    }
}