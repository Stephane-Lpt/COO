import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class testFormation {

    Formation fNull, fVide, f1, f3;

    @BeforeEach
    public void setUp(){
        fVide = new Formation("FormationVide");

        f1 = new Formation("Formation1");
        f1.ajouterMat("Matière1", 2);

        f3 = new Formation("Formation1");
        f3.ajouterMat("Matière1", 2);
        f3.ajouterMat("Matière2", 1);
        f3.ajouterMat("Matière3", 1.5);
    }


    @Test
    public void test_constructeur1PArametre() {
        assertEquals(0, fVide.getMatieres().size(), "le nombre de matière devrait être 0");
        assertEquals("FormationVide", fVide.getIdentifiant(), "l'identifiant de la formation devrait être 'FormationVide'");

        NullPointerException e = assertThrows(
                NullPointerException.class,
                () -> {fNull = new Formation(null);}
        );
    }

    @Test
    public void test_ajouterMat(){
        NullPointerException e1 = assertThrows(
                NullPointerException.class,
                () -> {f1.ajouterMat(null, 1);}
        );

        IllegalArgumentException e4 = assertThrows(
                IllegalArgumentException.class,
                () -> {f1.ajouterMat("Matiere", -1);}
        );

        assertEquals(1, f1.getCoefMatieres().size());
        assertTrue(f1.getCoefMatieres().containsKey("Matière1"));
        assertEquals(2, f1.getCoefMatieres().get("Matière1"));

        assertEquals(3, f3.getCoefMatieres().size());
        assertTrue(f3.getCoefMatieres().containsKey("Matière3"));
        assertEquals(1.5, f3.getCoefMatieres().get("Matière3"));
    }

    @Test
    public void test_ajouterMatieres(){
        HashMap<String, Double> mapMat = new HashMap<String, Double>();
        mapMat.put("Matière1", 1.5);
        mapMat.put("Matière2", 2.0);
        mapMat.put("Matière3", 1.0);
        mapMat.put("Matière4", 0.5);
        mapMat.put("Matière5", 3.0);


        NullPointerException e1 = assertThrows(
                NullPointerException.class,
                () -> {f1.ajouterMatieres(null);}
        );

        fVide.ajouterMatieres(mapMat);

        assertEquals(5, fVide.getCoefMatieres().size());
        assertTrue(fVide.getCoefMatieres().containsKey("Matière1"));
        assertTrue(fVide.getCoefMatieres().containsKey("Matière2"));
        assertTrue(fVide.getCoefMatieres().containsKey("Matière5"));
        assertEquals(1.5, fVide.getCoefMatieres().get("Matière1"));
        assertEquals(1.0, fVide.getCoefMatieres().get("Matière3"));



        f1.ajouterMatieres(mapMat);

        assertEquals(5, f1.getCoefMatieres().size());
        assertTrue(f1.getCoefMatieres().containsKey("Matière1"));
        assertTrue(f1.getCoefMatieres().containsKey("Matière2"));
        assertTrue(f1.getCoefMatieres().containsKey("Matière5"));
        assertEquals(1.5, f1.getCoefMatieres().get("Matière1"));
        assertEquals(1.0, f1.getCoefMatieres().get("Matière3"));
    }

    @Test
    public void test_supprimerMat(){
        //supprimer un matière=null, ne rien faire
        f3.supprimerMat(null);
        assertEquals(3, f3.getCoefMatieres().size());

        //supprimer une matière de la formation
        f3.supprimerMat("Matière1");
        assertEquals(2, f3.getCoefMatieres().size());
        assertTrue(!f3.getCoefMatieres().containsKey("Matière1"));
        assertTrue(f3.getCoefMatieres().containsKey("Matière2"));
        assertTrue(f3.getCoefMatieres().containsKey("Matière3"));

        //supprimer Matière non dans la liste, ne rien faire
        f3.supprimerMat("Matière100");
        assertEquals(2, f3.getCoefMatieres().size());
        assertTrue(f3.getCoefMatieres().containsKey("Matière2"));
        assertTrue(f3.getCoefMatieres().containsKey("Matière3"));
    }

    @Test
    public void test_getCoef(){
        assertEquals(2, f3.getCoef("Matière1"));
        assertEquals(2, f1.getCoef("Matière1"));


        IllegalArgumentException e1 = assertThrows(
                IllegalArgumentException.class,
                () -> {f3.getCoef("Matière100");}
        );

        IllegalArgumentException e2 = assertThrows(
                IllegalArgumentException.class,
                () -> {f3.getCoef(null);}
        );

    }
}
