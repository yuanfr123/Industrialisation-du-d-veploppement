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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortefeuilleTest {
    
    Jour DEFAULT_DAY = new Jour(1,1);
    Jour INCORRECT_DAY = new Jour(2,1);
    
    Jour jourActuelle = new Jour(2024, 18);
    
    ActionSimple action1TestValueUp = new ActionSimple("Action1");
    ActionSimple action2TestValueUp = new ActionSimple("Action2");
    
    ActionSimple action3TestValueDown = new ActionSimple("Action3");
    ActionSimple action4TestValueDown = new ActionSimple("Action4");
        

    
    
    public PortefeuilleTest(){
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
     * 
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
    
    @Test 
    /**
     * on test pour un jour donnée la valeur du portefeuille
     * les actions et le portfeuille sont paramétrés pour le même jour 
     * 
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
    
    /*
    Tester que portefeuille.valeur() echoue si le portefeuille a des actions qui n ont pas de valeur pour un jour donne 
    */
    @Test
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
            final String expectedMessage = "le cours pour "+INCORRECT_DAY.toString()+" n'est pas encore enregistré";
            IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
                portefeuille.valeur(INCORRECT_DAY);
            });
        
        //Assert
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
    }
    
    @Test
     /**
      * 
     * on test pour visualiser le portefeuille
     */
    public void testVisuelToString() {
        // Arrange 
        Action action1 = new ActionSimple("AAPL");
        Action action2 = new ActionSimple("GOOG");
        Action action3 = new ActionComposee("GHJG");
        
        //Action 
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action1, 100);
        portefeuille.acheter(action2, 200);
        portefeuille.acheter(action3, 300);
        
        //Assert Attention : Order des clés alphabétique.
        Assertions.assertEquals("{AAPL=100, GHJG=300, GOOG=200}", portefeuille.toString());
    }
    
    /**
     * @author Rs yinc
     *Visualiser l evolution de la valeur du portefeuille par rapport a la veille (evolution positive)
     */
    @Test
    public void testEvaluationUpPortefeuilleVeille() {
        Portefeuille portefeuille = new Portefeuille();
            //création et enregistrement de la valeur de l'action1 avec une valeur de 1
          
            portefeuille.acheter(action1TestValueUp, 1);
            portefeuille.acheter(action2TestValueUp, 1);
            
            String expectedMsg = "Evolution de la valeur du portefeuille: \n" + 
                    "valeur au Jour{annee=2024, noJour=17}: 2.0\n" +
                    "valeur au Jour{annee=2024, noJour=18}: 6.0\n"+
                    "Evolution : 200.0% ↑";
            
            Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionVeille(jourActuelle));

    }
    
    /**
     * @author Rs yinc
     * Visualiser l evolution de la valeur du portefeuille par rapport a la veille (evolution negative)
     */
    @Test
    public void testEvaluationDownPortefeuilleVeille() {
        Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action3TestValueDown, 1);
            portefeuille.acheter(action4TestValueDown, 1);
            
            String expectedMsg = "Evolution de la valeur du portefeuille: \n" + 
                    "valeur au Jour{annee=2024, noJour=17}: 17.0\n" +
                    "valeur au Jour{annee=2024, noJour=18}: 6.0\n"+
                    "Evolution : -64.71% ↓";
            
            Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionVeille(jourActuelle));

    }
    
    /**
     * @author Rs yinc
     *Visualiser l evolution de la valeur du portefeuille par rapport a la semaine derniere (evolution positive)
     */
    @Test
    public void testEvaluationUpPortefeuilleSemaine() {
        Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action1TestValueUp, 1);
            portefeuille.acheter(action2TestValueUp, 1);
            
            String expectedMsg = "Evolution de la valeur du portefeuille: \n" + 
                    "valeur au Jour{annee=2024, noJour=11}: 2.0\n" +
                    "valeur au Jour{annee=2024, noJour=18}: 6.0\n"+
                    "Evolution : 200.0% ↑";
            
            Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionSemaine(jourActuelle));

    }
    
    /**
     * @author Rs yinc
     *Visualiser l evolution de la valeur du portefeuille par rapport a la semaine derniere (evolution negative)
     */
    @Test
    public void testEvaluationDownPortefeuilleSemaine() {
        Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action3TestValueDown, 1);
            portefeuille.acheter(action4TestValueDown, 1);
            
            String expectedMsg = "Evolution de la valeur du portefeuille: \n" + 
                    "valeur au Jour{annee=2024, noJour=11}: 17.0\n" +
                    "valeur au Jour{annee=2024, noJour=18}: 6.0\n"+
                    "Evolution : -64.71% ↓";
            
            Assertions.assertEquals(expectedMsg, portefeuille.afficherEvolutionSemaine(jourActuelle));

    }
}
