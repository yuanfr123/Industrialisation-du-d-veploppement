/*
 * Copyright 2024 22638.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

/**
 *
 * @author Ravaka & Yujie
 */
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortefeuilleTest {
    
    Jour DEFAULT_DAY = new Jour(1,1);
    Jour INCORRECT_DAY = new Jour(2,1);
    
    Jour jourActuelle = new Jour(2024, 18);
    
    Action action1 = new ActionSimple("AAPL");
    Action action2 = new ActionComposee("GOOG");
    Action action3 = new ActionComposee("GHJG");
    Action action4 = new ActionSimple("action4");
    
    ActionSimple action1TestValueUp = new ActionSimple("Action1");
    ActionSimple action2TestValueUp = new ActionSimple("Action2");
    
    ActionSimple action3TestValueDown = new ActionSimple("Action3");
    ActionSimple action4TestValueDown = new ActionSimple("Action4");
        

    
    
    public PortefeuilleTest(){
            ActionSimple actionSimple = (ActionSimple) action1;
            actionSimple.enrgCours(DEFAULT_DAY, 1);
    }
    
    
    /**
     * @author Rs yinc
     * mettre les valeurs des actions pour le jour actuel, precedent et de la semaine precedente
     */
    @BeforeEach
    public void setUp() {
        action1TestValueUp.enrgCours(jourActuelle, 4);
        action1TestValueUp.enrgCours(new Jour(2024,17), 1);
        action1TestValueUp.enrgCours(new Jour(2024,11), 1);

        
        action2TestValueUp.enrgCours(jourActuelle, 2);
        action2TestValueUp.enrgCours(new Jour(2024,17), 1);
        action2TestValueUp.enrgCours(new Jour(2024,11), 1);
        
        action3TestValueDown.enrgCours(jourActuelle, 4);
        action3TestValueDown.enrgCours(new Jour(2024,17), 8);
        action3TestValueDown.enrgCours(new Jour(2024,11), 8);

        
        action4TestValueDown.enrgCours(jourActuelle, 2);
        action4TestValueDown.enrgCours(new Jour(2024,17), 9);
        action4TestValueDown.enrgCours(new Jour(2024,11), 9);
        
       
    }

    @Test
    /**
     *  @author RY
     * Méthode de test:
     * Le portfeuille exist.
     * HashMap doit être manipulable.
     * HashMap est vide au commencement.
     */
    protected void testConstructorMapLignesInitialization() {
        //Arrange
        Portefeuille portefeuille = new Portefeuille();
        // Action
        Map<?, ?> mapLignes = portefeuille.getMapLignes();

        // Assert
        assertNotNull(mapLignes, "mapLignes should not be null");//Exist ou pas
        assertTrue(mapLignes instanceof Map, "mapLignes should be an instance of HashMap");
        assertTrue(mapLignes.isEmpty(), "mapLignes should be empty initially");//Vide 
    }
    /**
     *  @author RY
     * Méthode générale
     */
    
private Portefeuille createPortefeuilleAvecAction(Action action1, Object action2, Object action3, Object action4) {
    Portefeuille portefeuille = new Portefeuille();
    portefeuille.acheter(action1, 100);


    if (action2 instanceof Action){
        portefeuille.acheter(this.action2, 200);
    }
    if (action3 instanceof Action){
        portefeuille.acheter(this.action3, 300);
    }
    if (action4 instanceof Action){
        portefeuille.acheter(this.action4, 400);
    }
    return portefeuille;
}
 
    @Test
    /**
     *  @authorYR
    *Testez la vente d'actions quand vous avez autant d'actions (pas également).
    */
    public void testVendreSuccess(){
        // Arrange 
        Portefeuille portefeuille = createPortefeuilleAvecAction(action1, action2,null,null);
        
        //Action 
        
        portefeuille.vendre(action1, 50);
        portefeuille.vendre(action2, 50);
        
        //Assert Attention : Order des clés alphabétique.
        Assertions.assertEquals("{AAPL=50, GOOG=150}", portefeuille.toString()); 
    }
    
    @Test
    /**
     *  @author YR
    *Testez la vente d'actions quand vous avez autant d'actions (également).
    */
    public void testVendreSuccessTous(){
        // Arrange 
        Portefeuille portefeuille = createPortefeuilleAvecAction(action1, action2,null,null);
        
        //Action         
        portefeuille.vendre(action1, 100);
        portefeuille.vendre(action2, 200);
        
        //Assert Attention : Order des clés alphabétique.
        Assertions.assertEquals("{}", portefeuille.toString()); 
    }
    
    @Test
    /**
     * @author YR
    *Testez la vente d'actions quand vous n'avez pas autant d'actions.
    */
    public void testVendreFailSuperieur(){
        // Arrange 
        Portefeuille portefeuille = createPortefeuilleAvecAction(action1, action2,null,null);
        
        //Action 
        
        //Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->{
        portefeuille.vendre(action1, 1000);
        portefeuille.vendre(action2, 2000);});
        
        assertEquals("Vous n'avez pas autant d'actions.",exception.getMessage());
    }
    @Test
    /**
     * @author YR
    *Testez la vente d'actions quand vous n'avez pas d'actions.
    */
        public void testVendreFailNePossederPas(){
        // Arrange 
        Portefeuille portefeuille = createPortefeuilleAvecAction(action1, null,null,null);
        
        //Action 
        
        //Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->{
        portefeuille.vendre(action2, 200);});
        
        assertEquals("Ne peut pas vendre les actions qu'on n'possède pas.",exception.getMessage());
    }
    
    @Test
     /**
      * @author YR
     * on test pour visualiser le portefeuille.
     */
    public void testVisuelToString() {
        // Arrange 
        Portefeuille portefeuille = createPortefeuilleAvecAction(action1, action2,action3,null);
        
        //Action 
        
        
        //Assert Attention : Order des clés alphabétique.
        Assertions.assertEquals("{AAPL=100, GHJG=300, GOOG=200}", portefeuille.toString());
    }
    
    @Test 
    /**
     * @author RY
     *on test pour un jour donnée la valeur du portefeuille.
     *les actions et le portfeuille sont paramétrés pour le même jour.
     */
  protected void testValeurPortefeuilleShouldSuccess(){
        //Arrange
        Portefeuille portefeuille = new Portefeuille();
            //création et enregistrement de la valeur de l'action1 avec une valeur de 1
            ActionSimple action1 = new ActionSimple("Action1");
            action1.enrgCours(DEFAULT_DAY, 1);
            float valeur1 = action1.valeur(DEFAULT_DAY);
            //création et enregistrement de la valeur de l'action2 avec une valeur de 2
            ActionSimple action2 = new ActionSimple("Action2");
            action2.enrgCours(DEFAULT_DAY, 2);
            float valeur2 = action2.valeur(DEFAULT_DAY);
        
        //Action 
            //création du portefeuille et consulation de sa valeur 
            portefeuille.acheter(action1, 1);
            portefeuille.acheter(action2, 1);
            float valeurPortefeuille = portefeuille.valeur(DEFAULT_DAY);
        
        //Assert
        Assertions.assertEquals(valeurPortefeuille, (valeur1 + valeur2), "La somme des valeurs des actions détenues n'égalent pas la valeur du portefeuille"); //égalité de valeur 
    }

    
    @Test
    /**
     * @author RY
     *on test pour un jour donnée la valeur du portefeuille.
     *les actions et le portfeuille sont paramétrés pour un jour différent.
     */
    public void testValeurPortefeuilleShouldFail(){
        //Arrange
        Portefeuille portefeuille = new Portefeuille();
            //création et enregistrement de la valeur de l'action1 avec une valeur de 1
            ActionSimple action1 = new ActionSimple("Action1");
            action1.enrgCours(DEFAULT_DAY, 1);
            float valeur1 = action1.valeur(DEFAULT_DAY);
            //création et enregistrement de la valeur de l'action2 avec une valeur de 2
            ActionSimple action2 = new ActionSimple("Action2");
            action2.enrgCours(DEFAULT_DAY, 2);
            float valeur2 = action2.valeur(DEFAULT_DAY);
        
        //Action 
            //création du portefeuille et consulation de sa valeur 
            portefeuille.acheter(action1, 1);
            portefeuille.acheter(action2, 1);
            
            final String expectedMessage = "le cours pour "
                    +INCORRECT_DAY.toString()+" n'est pas encore enregistré";
            IllegalArgumentException assertThrowsExactly = 
                    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
                portefeuille.valeur(INCORRECT_DAY);
            });
        
        //Assert
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
    }

    /**
     * Génère le message attendu pour l'évolution de la valeur du portefeuille.
     *
     * @author RS & yinc
     * @param dateJour               La date de la veille ou de la semaine precedente.
     * @param valeurReference       La valeur de référence du portefeuille.
     * @param valeurActuelle        La valeur actuelle du portefeuille.
     * @param pourcentageEvolution Le pourcentage d'évolution entre la valeur de référence et la valeur actuelle.
     * @param direction             La direction de l'évolution (↑ pour une augmentation, ↓ pour une diminution).
     * @return Le message formaté représentant l'évolution de la valeur du portefeuille.
     */
    private String generateExpectedMessage(String dateJour, double valeurReference, double valeurActuelle, double pourcentageEvolution, String direction) {
        return "Evolution de la valeur du portefeuille: \n" +
                "valeur au " + dateJour + ": " + valeurReference + "\n" +
                "valeur au Jour{annee=2024, noJour=18}: " + valeurActuelle + "\n" +
                "Evolution : " + pourcentageEvolution + "% " + direction;
    }

    /**
     * @author RS & yinc
     * Visualiser l'évolution de la valeur du portefeuille par rapport à la veille (évolution positive).
     */
    @Test
    public void testEvaluationUpPortefeuilleVeille() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action1TestValueUp, 1);
        portefeuille.acheter(action2TestValueUp, 1);

        String expectedMsg = generateExpectedMessage("Jour{annee=2024, noJour=17}", 2.0, 6.0, 200.0, "↑");
        Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionVeille(jourActuelle));
    }

    /**
     * @author RS & yinc
     * Visualiser l'évolution de la valeur du portefeuille par rapport à la veille (évolution négative).
     */
    @Test
    public void testEvaluationDownPortefeuilleVeille() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action3TestValueDown, 1);
        portefeuille.acheter(action4TestValueDown, 1);

        String expectedMsg = generateExpectedMessage("Jour{annee=2024, noJour=17}", 17.0, 6.0, -64.71, "↓");
        Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionVeille(jourActuelle));
    }

    /**
     * @author RS & yinc
     * Visualiser l'évolution de la valeur du portefeuille par rapport à la semaine dernière (évolution positive).
     */
    @Test
    public void testEvaluationUpPortefeuilleSemaine() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action1TestValueUp, 1);
        portefeuille.acheter(action2TestValueUp, 1);

        String expectedMsg = generateExpectedMessage("Jour{annee=2024, noJour=11}", 2.0, 6.0, 200.0, "↑");
        Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionSemaine(jourActuelle));
    }

    /**
     * @author RS & yinc
     * Visualiser l'évolution de la valeur du portefeuille par rapport à la semaine dernière (évolution négative).
     */
    @Test
    public void testEvaluationDownPortefeuilleSemaine() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action3TestValueDown, 1);
        portefeuille.acheter(action4TestValueDown, 1);

        String expectedMsg = generateExpectedMessage("Jour{annee=2024, noJour=11}", 17.0, 6.0, -64.71, "↓");
        Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionSemaine(jourActuelle));
    }

}
