import java.util.*;

/**
 * Classe représentant une formation
 */
public class Formation {
    /**
     * Identifiant de la formation
     */
    private String identifiant;
    /**
     * Dictionnaire des matières et de leur coefficient
     */
    private HashMap<String, Double> coefMatieres;

    /**
     * Construit une Formation avec une id et initialise le dictionnaire des matières à une capacité de 10
     * @param id l'identifiant de la formation
     */
    public Formation(String id){;
        if(id != null){
            this.identifiant = id;
            this.coefMatieres = new HashMap<>(10 );
        }
        else{
            throw new NullPointerException("L'identifiant doit être une chaine de caractères");
        }
    }

    /**
     * Permet d'ajouter une matière avec son coefficient ou de changer le coefficient associé à une matière
     * @param matiere le nom de la matière à ajouter
     * @param coef le coefficient associé à la matière
     */
    public void ajouterMat(String matiere, double coef){

        if(matiere != null) {
            if (coef > 0)
                this.coefMatieres.put(matiere, coef);
            else
                throw new IllegalArgumentException("le coefficient doit être strictement supérieur à 0");
        }
        else{
                throw new NullPointerException("Veuillez spécifier le nom de la matière lors de l'ajout");
        }
    }

    /**
     * Permet d'ajouter plusieurs matières avec leurs coefficients associés
     * @param map dictionnaire contenant les matières et leurs coefficients à ajouter
     */
    public void ajouterMatieres(Map<String, Double> map){
        if(map != null) {
            Set<String> setCles = map.keySet();
            for(String s : setCles){
                ajouterMat(s, map.get(s));
            }
        }
        else{
            throw new NullPointerException("Veuillez spécifier le dictionnaire contenant les matières et leurs coefficients lors de l'ajout");
        }
    }



    /**
     * Supprime une matière du dictionnaire
     * @param matiere le nom de la matière à supprimer
     */
    public void supprimerMat(String matiere){
        if(matiere != null){
            this.coefMatieres.remove(matiere);
        }
    }

    public double getCoef(String matiere){
        if(matiere != null && this.coefMatieres.containsKey(matiere))
            return this.coefMatieres.get(matiere);
        else
            throw new IllegalArgumentException("Cette matière n'existe pas dans cette formation");
    }

    /**
     * retourne toutes les clés du dictionnaire
     * @return les clés du du dictionnaire
     */
    public Set<String> getMatieres(){
        return this.coefMatieres.keySet();
    }






    // METHODE ACCESSEUR POUR TEST //
    public String getIdentifiant(){return this.identifiant;}

    public HashMap<String, Double> getCoefMatieres() {return coefMatieres;}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Formation){
            if(((Formation)obj).identifiant.equals(this.identifiant))
            return true;
        }

        return false;

    }
}
