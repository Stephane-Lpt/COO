import java.util.List;

/**
 * Classe représentant un groupe qui va accueillir des étudiants
 */
public class Groupe {
    /**
     * Formation à laquelle appartient le groupe d'étudiants
     */
    private Formation formation;

    /**
     * la liste des étudiants appartenant à ce groupe
     */
    private List<Etudiant> etudiants;

    /**
     * Construit un groupe en l'associant à une formation
     * @param f la formation liée à ce groupe
     */
    public Groupe(Formation f){
        if(f!=null){
            this.formation = f;
        }
        else{
            throw new NullPointerException("La formation est invalide pour la construction d'un groupe");
        }
    }


}
