import java.util.Comparator;

public class ComparateurAlpha implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant etu1, Etudiant etu2) {
        if(etu1 != null && etu2 != null){
            return etu1.getNom().compareTo(etu2.getNom());
        }
        throw new NullPointerException();
    }


}
