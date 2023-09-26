import java.util.*;

public class Etudiant extends Identite{

    private HashMap<String, List<Double>> matNotes;

    private Formation formation;


    public Etudiant(String nip, String nom, String prenom, Formation formation) {
        super(nip, nom, prenom);

        if (formation==null) throw new NullPointerException();
        this.formation = formation;

        this.matNotes = new HashMap<String, List<Double>>();
    }




    public void ajouterNote(String matiere, Double note){
        // valeur fournie non null
        if (matiere==null || note==null) throw new NullPointerException();

        // valeur note compris entre 0 et 20 inclue
        if (note < 0 || note > 20) throw new IllegalArgumentException("0 >= note >= 20");

        // matiere fournie present dans la formatino de l'étudiant
        Set<String> matieres = this.formation.getMat();
        if (!matieres.contains(matiere)) throw new IllegalArgumentException("nom de la matière non valide pour la formation de l'etudiant");

        List listeNotes = this.matNotes.get(matiere);
        listeNotes.add(note);
        this.matNotes.put(matiere, listeNotes);
    }

    public void ajouterNote(String matiere, List<Double> note){
        // valeur fournie non null
        if (matiere==null || note==null) throw new NullPointerException();

        // matiere fournie present dans la formatino de l'étudiant
        Set<String> matieres = this.formation.getMat();
        if (!matieres.contains(matiere)) throw new IllegalArgumentException("nom de la matière non valide pour la formation de l'etudiant");

        //parcours de la liste de note
        List listeNotes = this.matNotes.get(matiere);
        for (Double n : note) {
            // valeur note compris entre 0 et 20 inclue
            if (n < 0 || n > 20) throw new IllegalArgumentException("0 >= note >= 20: Value="+n);
            ajouterNote(matiere, n);
        }
    }





    public Double calculerMoyMat(String matiere){
        if (matiere==null) throw new NullPointerException();

        List<Double> notes = this.matNotes.get(matiere);
        if (notes ==null) return null;

        double somme = 0.0;
        for(Double n : notes){
            somme += n;
        }

        return somme /(notes.size());
    }



    public Double calculerMoyGen(){
        double somme = 0.0;
        for (String m : this.matNotes.keySet()){
            somme += calculerMoyMat(m);
        }
        return somme/(this.matNotes.size());
    }



    public HashMap<String, List<Double>> getMatNotes() {return matNotes;}
    public Formation getFormation() {return formation;}
    public void setFormation(Formation f){this.formation = f;}

    @Override
    public boolean equals(Object obj) {
        return formation==formation && matNotes==matNotes && super.equals(obj);
    }

    @Override
    public String toString() {return super.toString()+" "+this.formation+" "+this.matNotes;}
}
