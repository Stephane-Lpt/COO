import java.util.Comparator;

public class ComparateurMerite implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant etu1, Etudiant etu2) {
        if(etu1 != null && etu2 != null){
            if(etu1.calculerMoyGen()<etu2.calculerMoyGen()) return -1;
            else if(etu1.calculerMoyGen()>etu2.calculerMoyGen()) return 1;
            else return 0;
        }
        throw new NullPointerException();
    }
}