
public class Identite {

    private String nip, nom, prenom;

    protected Identite(String nip, String nom, String prenom){
        if (nip==null || nom==null | prenom==null) throw new NullPointerException();
        this.nip =nip;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNip() {return nip;}
    public void setNip(String nip) {this.nip = nip;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!=Identite.class) throw new ClassCastException();
        Identite id = (Identite) obj;
        return (nip.equals(id.nip) && nom.equals(id.nom) && prenom.equals(id.prenom));
    }

    @Override
    public String toString() {return nip+" "+nom+" "+prenom;}
}
