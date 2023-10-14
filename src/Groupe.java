import java.util.ArrayList;
import java.util.Iterator;
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
            this.etudiants = new ArrayList<>();
        }
        else{
            throw new NullPointerException("La formation est invalide pour la construction d'un groupe");
        }
        Etudiant[] tabEtu = this.etudiants.toArray(new Etudiant[0]);
    }

    /**
     * Méthode permettant d'ajouter un étudiant au groupe
     * @param etu l'étudiant à ajouter au groupe
     */
    public void ajouterEtu(Etudiant etu){
        if(etu != null){
            if(etu.getFormation() == this.formation) {
                if(!this.etudiants.contains(etu)) {
                    this.etudiants.add(etu);
                }
                else{
                    throw new IllegalArgumentException("Le groupe contient déjà cet élément");
                }
            }
            else{
                throw new IllegalArgumentException("Les formations du groupe et de l'étudiant doivent être les mêmes");
            }
        }
        else {
            throw new NullPointerException("L'étudiant en argument n'a pas été initialisé (null)");
        }
    }

    public void supprimerEtu(Etudiant etu){
        if(etu == null){
            throw new NullPointerException("L'étudiant en argument n'a pas été");
        }
        else if(!this.etudiants.contains(etu)){
            throw new IllegalArgumentException("L'étudiant passé en argument ne fait pas parti de ce groupe");
        }
        else{
            this.etudiants.remove(etu);
        }
    }

    public void triAlpha(){
        this.etudiants.sort(new ComparateurAlpha());
    }

    public void triAntiAlpha(){
        this.etudiants.sort(new ComparateurAntiAlpha());
    }

    public void triParMerite(){
        this.etudiants.sort(new ComparateurMerite());
    }


    public Double calculerMoyMat(String matiere){
        if (matiere == null) throw new NullPointerException();
        if (this.etudiants.isEmpty()) throw new IndexOutOfBoundsException();
        if (!this.formation.getMatieres().contains(matiere)) throw new IllegalArgumentException("matiere non conforme pour la formation du groupe");

        double somme = 0.0;
        double nbEtu = 0;

        Iterator<Etudiant> it = this.etudiants.iterator();
        while (it.hasNext()){
            Etudiant e = it.next();
            double note=-1;

            try {
                note = e.calculerMoyMat(matiere);
            }catch (IndexOutOfBoundsException e1){}

            if (note!=-1) {
                somme += note;
                nbEtu++;
            }
        }
        return somme / nbEtu;
    }

    public Double calculerMoyGen(){
        if (this.etudiants.isEmpty()) throw new IndexOutOfBoundsException();

        double somme = 0.0;
        for (Etudiant e : this.etudiants){
            somme += e.calculerMoyGen();
        }
        return somme / this.etudiants.size();
    }

    public Formation getFormation() {
        return formation;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }
}
