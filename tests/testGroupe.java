import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class testGroupe {
    private Formation f1;
    private Formation f2;
    private Etudiant e1;
    private Etudiant e2;
    private Etudiant e3;

    private Groupe g1;
    private Groupe g2;

    @BeforeEach
    public void setUp(){
        f1 = new Formation("f1");
        f1.ajouterMat("Français", 3);
        f1.ajouterMat("Mathématiques", 10);
        f1.ajouterMat("Physique", 5);
        f1.ajouterMat("SVT", 2);

        f2 = new Formation("f2");

        e1 = new Etudiant("e1", "Manoudou", "Florent", f1);
        e1.ajouterNote("Mathématiques", 10.0);
        e1.ajouterNote("Mathématiques", 20.0);
        e1.ajouterNote("Physique", 5.0);
        e1.ajouterNote("SVT", 8.0);

        e2 = new Etudiant("e2", "Loppinet", "Stephane", f1);
        e2.ajouterNote("Mathématiques", 20.0);

        e3 = new Etudiant("e3", "Hallyday", "Johny", f1);
        e3.ajouterNote("Mathématiques", 5.0);

        g1 = new Groupe(f1);
        g2 = new Groupe(f2);
    }


    @Test
    public void test_ajouterEtu() {
        Assertions.assertTrue(this.g1.getEtudiants().isEmpty());
        this.g1.ajouterEtu(e1);
        Assertions.assertFalse(this.g1.getEtudiants().isEmpty());
        Assertions.assertEquals(e1, this.g1.getEtudiants().get(0));
    }

    @Test
    public void test_ajouterEtu_FormationInvalide() {
        Assertions.assertTrue(this.g2.getEtudiants().isEmpty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.g2.ajouterEtu(e1);
        });
    }

    @Test
    public void test_ajouterEtu_EtudiantDejaDansLeGroupe() {
        Assertions.assertTrue(this.g1.getEtudiants().isEmpty());
        this.g1.ajouterEtu(e1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.g1.ajouterEtu(e1);
        });
    }

    @Test
    public void test_ajouterEtu_EtudiantNull() {
        Assertions.assertTrue(this.g1.getEtudiants().isEmpty());
        Assertions.assertThrows(NullPointerException.class, () -> {
            this.g1.ajouterEtu(null);
        });
    }

    @Test
    public void test_supprimerEtu() {

        Assertions.assertTrue(this.g1.getEtudiants().isEmpty());
        this.g1.ajouterEtu(e1);
        this.g1.supprimerEtu(e1);
        Assertions.assertTrue(this.g1.getEtudiants().isEmpty());
    }

    @Test
    public void test_supprimerEtudiantInexistant() {
        Assertions.assertTrue(this.g1.getEtudiants().isEmpty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.g1.supprimerEtu(e1);
        });
    }

    @Test
    public void test_calculerMoyGrpMat() {

    }

    @Test
    public void test_calculerMoyGrpGen() {

    }

    @Test
    public void test_triAlpha() {

    }

    @Test
    public void test_triAntiAlpha() {

    }

    @Test
    public void test_triParMerite(){

    }

}
