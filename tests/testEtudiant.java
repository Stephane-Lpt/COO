import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testEtudiant {
    private Formation f1;
    private Etudiant e1;

    /**
     * Préparation des données :
     * Plusieurs matières avec des coefficients différents
     * L'élève n'a pas de notes dans certaines matières (Français)
     */
    @BeforeEach
    public void beforeEach(){
        f1 = new Formation("f1");
        f1.ajouterMat("Français", 3);
        f1.ajouterMat("Mathématiques", 10);
        f1.ajouterMat("Physique", 5);
        f1.ajouterMat("SVT", 2);
        e1 = new Etudiant("e1", "Manoudou", "Florent", f1);
        e1.ajouterNote("Mathématiques", 10.0);
        e1.ajouterNote("Mathématiques", 20.0);
        e1.ajouterNote("Physique", 5.0);
        e1.ajouterNote("SVT", 8.0);
    }

    /**
     * Méthode testant l'ajout d'une note à un étudiant
     */
    @Test
    public void test_ajouterNote(){

        e1.ajouterNote("Français", 10.0);

        Assertions.assertFalse(e1.getMatNotes().isEmpty());
        Assertions.assertTrue(e1.getMatNotes().containsKey("Français"));
        Assertions.assertTrue(e1.getMatNotes().containsKey("Mathématiques"));
        Assertions.assertTrue(e1.getMatNotes().containsKey("Physique"));
        Assertions.assertEquals(10.0, e1.getMatNotes().get("Français").get(0));
    }

    /**
     * Méthode testant que l'ajout de note déclenche une exception si la matière n'existe pas
     * dans la formation de l'étudiant
     */
    @Test
    public void test_ajouterNote_MatiereInexistanteFormation(){

        Assertions.assertFalse(e1.getMatNotes().isEmpty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            e1.ajouterNote("Espagnol", 10.0);
        });
    }

    /**
     * Méthode testant l'ajout d'une note pas comprise dans l'intervalle [0,20]
     * Doit lancer une exception par conséquent
     */
    @Test
    public void test_ajouterNote_NoteIllegale(){

        Assertions.assertFalse(e1.getMatNotes().isEmpty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            e1.ajouterNote("Français", 50.0);
        });
    }

    /**
     * Méthode testant le calcul de la moyenne d'une matière
     */
    @Test
    public void test_calculerMoyMatiere(){
        Assertions.assertEquals(15.0 ,e1.calculerMoyMat("Mathématiques"));
    }

    /**
     * Méthode testant que le calcul de la moyenne d'une matière inexistante lance bien
     * une exception
     */
    @Test
    public void test_calculerMoyMatiereInexistante(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            e1.calculerMoyMat("Espagnol");
        });
    }

    /**
     * Méthode vérifiant le bon calcul de la moyenne générale d'un étudiant
     * (Si une matière a été référencée mais que l'étudiant n'a pas encore de notes,
     * son coefficient ne doit pas être compté dans la somme totale des coefficients)
     */
    @Test
    public void test_calculerMoyG(){
        Assertions.assertEquals((2*8.0 + 5*5.0 + e1.calculerMoyMat("Mathématiques")*10) / 17, e1.calculerMoyGen().doubleValue());
    }

}
