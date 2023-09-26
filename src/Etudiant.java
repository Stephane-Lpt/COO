import java.util.*;

/**
 * classe représentant un Etudiant fils de la classe Identite
 */
public class Etudiant extends Identite{

    //======================//
    //      ATTRIBUTS       //
    //======================//

    /**
     * attribut représentant les notes de l'etudiant par matière
     */
    private HashMap<String, List<Double>> matNotes;

    /**
     * attribut représentant la formation suivit par l'étudiant
     */
    private Formation formation;



    //=========================//
    //      CONSTRUCTEUR       //
    //=========================//


    /**
     * constructeur par defaut a 4 paramètres, NullPointerException si au moins 1 paramètre ==  null
     * @param nip Numero identifiant Personnel
     * @param nom nom de l'etudiant
     * @param prenom prenom de l'etudiant
     * @param formation instance de classe Formation
     */
    public Etudiant(String nip, String nom, String prenom, Formation formation) {
        super(nip, nom, prenom);

        if (formation==null) throw new NullPointerException();
        this.formation = formation;

        this.matNotes = new HashMap<String, List<Double>>();
        Set<String> listeMat = this.formation.getMatieres();
        for (String m : listeMat){
            this.matNotes.put(m, new ArrayList<Double>());
        }

    }



    //=====================//
    //      METHODES       //
    //=====================//

    /**
     * méthode qui permet d'ajouter 1 note pour 1 matière à l'étudiant si et seulement si 0 <= note <= 20 et
     * que la matière soit comprise dans la formation de  l'etudiant
     * si matière fournie non valide, IllegalArgumentException
     * si note != 0<=note<=20,IllegalArgumentException
     * si note=null ou matiere=null, NullpointerException
     * @param matiere nom de la matière
     * @param note valeur de la note a ajouter
     */
    public void ajouterNote(String matiere, Double note){
        // valeur fournie non null
        if (matiere==null || note==null) throw new NullPointerException();

        // valeur note compris entre 0 et 20 inclue
        if (note < 0 || note > 20) throw new IllegalArgumentException("0 >= note >= 20");

        // matiere fournie present dans la formatino de l'étudiant
        Set<String> matieres = this.formation.getMatieres();
        if (!matieres.contains(matiere)) throw new IllegalArgumentException("nom de la matière non valide pour la formation de l'etudiant");

        List listeNotes = this.matNotes.get(matiere);
        listeNotes.add(note);
        this.matNotes.put(matiere, listeNotes);
    }

    /**
     * méthode qui permet d'ajouter une liste de note pour 1 matière à l'étudiant,
     * si matière fournie non valide, IllegalArgumentException
     * si 1 note != 0<=note<=20 alors note non ajouté
     * si liste des note=null ou matiere=null, NullpointerException
     * @param matiere
     * @param note
     */
    public void ajouterNote(String matiere, List<Double> note){
        // valeur fournie non null
        if (matiere==null || note==null) throw new NullPointerException();

        // matiere fournie present dans la formatino de l'étudiant
        Set<String> matieres = this.formation.getMatieres();
        if (!matieres.contains(matiere)) throw new IllegalArgumentException("nom de la matière non valide pour la formation de l'etudiant");

        //parcours de la liste de note
        List listeNotes = this.matNotes.get(matiere);
        for (Double n : note) {
            try{
                ajouterNote(matiere, n);
            }catch(Exception e){}
        }
    }


    /**
     * méthode de calcule de la moyenne par matière
     * si matiere=null, NullPointerException
     * si matiere pas dans la formation de l'etudiant, IllegalArgumentException
     * @param matiere moyenne des notes dans la matiere fournie
     * @return
     */
    public Double calculerMoyMat(String matiere){
        if (matiere==null) throw new NullPointerException();

        List<Double> notes = this.matNotes.get(matiere);
        if (notes ==null) throw new IllegalArgumentException();

        double somme = 0.0;
        for(Double n : notes){
            somme += n;
        }

        return somme /(notes.size());
    }


    /**
     * méthode de calcule de la moyenne générale de l'etudiant par rapport au coefficient des matière dans sa formation
     * @return moyenne générale etudiant selon coef formation
     */
    public Double calculerMoyGen(){
        double somme = 0.0;
        double sommeCoef = 0.0;
        for (String m : this.matNotes.keySet()){
            if (!this.matNotes.get(m).isEmpty()){
                double coef = this.formation.getCoef(m);
                sommeCoef += coef;
                somme += calculerMoyMat(m)*coef;
            }
        }
        return somme/sommeCoef;
    }


    //================================//
    //      METHODES ACCESSEURS       //
    //================================//
    public HashMap<String, List<Double>> getMatNotes() {return matNotes;}
    public Formation getFormation() {return formation;}
    public void setFormation(Formation f){this.formation = f;}



    //==============================//
    //      METHODES OVERRIDE       //
    //==============================//
    @Override
    public boolean equals(Object obj) {
        return formation==formation && matNotes==matNotes && super.equals(obj);
    }

    @Override
    public String toString() {return super.toString()+" "+this.formation+" "+this.matNotes;}
}
