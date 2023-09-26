/**
 * classe représentant l'identification d'un personne unique
 */
public class Identite {

    //======================//
    //      ATTRIBUTS       //
    //======================//

    /**
     * attribut Numero Identification Personnel
     */
    private String nip;

    /**
     * attribut relatif au nom de la personne
     */
    private String nom;

    /**
     * attribut relatif au prenom de la personne
     */
    private String prenom;



    //=========================//
    //      CONSTRUCTEUR       //
    //=========================//

    /**
     * constructeur 3 paramètres, si au moins 1 paramètre == null alors NullPointerException
     * @param nip Numero Identifiant Personnel
     * @param nom nom de la personne
     * @param prenom prenom de la personne
     */
    protected Identite(String nip, String nom, String prenom){
        if (nip==null || nom==null | prenom==null) throw new NullPointerException();
        this.nip =nip;
        this.nom = nom;
        this.prenom = prenom;
    }



    //================================//
    //      METHODES ACCESSEURS       //
    //================================//


    public String getNip() {return nip;}
    public void setNip(String nip) {this.nip = nip;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}




    //===============================//
    //      METHODES OVERRRIDE       //
    //===============================//

    /**
     * comparateur d'égalite de la classe
     * @param obj instance de la classe Identite
     * @return true = si nip=obj.nip et nom==obj.nom et prenom==obj.prenom     sinon = false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Identite)) throw new ClassCastException();
        Identite id = (Identite) obj;
        return (nip.equals(id.nip) && nom.equals(id.nom) && prenom.equals(id.prenom));
    }

    /**
     * méthode d'affichage de la classe
     * @return "nip nom prenom"
     */
    @Override
    public String toString() {return nip+" "+nom+" "+prenom;}
}
