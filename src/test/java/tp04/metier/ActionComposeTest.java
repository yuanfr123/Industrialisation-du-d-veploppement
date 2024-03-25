/*
 * Copyright 2024 yinchenwang.
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
        

/**
 *
 * @author Rs yinc
 *
 * Afin d'effectuer nos tests,
 *  Nous définissons un portefeuille,
 *  deux actions simple,
 *  une action composee,
 *  le jour actuel,
 *  enregistrement des cours des actions simple au jour actuel, a la veille et de la semaine precedente,
 *  enregistrement de la composition de l'action composee
 */

public class ActionComposeTest {
    
   private Portefeuille portefeuille = new Portefeuille();
   
   private ActionSimple france2 = new ActionSimple("france 2");
   
   private ActionSimple france4 = new ActionSimple("france 4");
    
   private ActionComposee  franceTV = new ActionComposee("FranceTV");
   
   Jour jourActuelle = new Jour(2024, 1);
   
   /**
    *@author Rs yinc
    *enregistrer le cours des actions simples a differentes dates
    *enregistrer la composition de l action composee
    */
   @BeforeEach
    public void setUp() {
     
        france2.enrgCours(jourActuelle, 30.2f);
        france2.enrgCours(new Jour(2023, 365), 29.2f);
        france2.enrgCours(new Jour(2023, 359), 28.2f);
       
        
        france4.enrgCours(jourActuelle, 32.3f);
        france4.enrgCours(new Jour(2023, 365), 34.3f);
        france4.enrgCours(new Jour(2023, 359), 38.3f);
        
        // Créer une action composée
       franceTV.enrgComposition(france4, 0.50f);
       franceTV.enrgComposition(france2, 0.50f);


    }
    
    /**
     * @author Rs yinc
     * afficher la composition d'une action composee
     * 1) creation 2 action simple.
     * 2) creation  d'une action composee.
     * 3) definition la compostion action composee
     * 4) test la methode
     */
    @Test
    public void testConsulterCompositionActionComposee() {
        // Vérifier que la composition de l'action composée est correcte
        String compositionAttendue = "voici la composition de cette action: \n" +
                                      "Action: france 4, Composition: 50.0\n" +
                                      "Action: france 2, Composition: 50.0\n";
        Assertions.assertEquals(compositionAttendue, franceTV.consulterComposition());
    }
    
    /**
     * @author Rs yinc
     * test achat action composee
     * 1) creation 2 action simple.
     * 2) creation  d'une action composee.
     * 3) definition la compostion action composee
     * 4) achat d'une action composee et d'une action simple.
     * 5) test la methode portefeuille.
     */
    @Test
    public void testAcheterActionComposee() {

       portefeuille.acheter(franceTV, 3);
       
       portefeuille.acheter(france2, 5);
       
       //test pour verifier la modifi de qte d'une qction deja presente
       
       portefeuille.acheter(franceTV, 3);

        // Vérifier que la composition de l'action composée est correcte
        String compositionAttendue = "{FranceTV=6, france 2=5}";
                                 
        Assertions.assertEquals(compositionAttendue, portefeuille.toString());
    }
    
    /**
     * @author Rs yinc
     * test definition achat d'actions avec une qte non autorisee
     * 
     */
    
     @Test
    public void testDefinitionAchatActionShouldFail(){
        
        final String expectedMessage = "achter au moins une action";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            portefeuille.acheter(franceTV, 0);
        }, "achter au moins une action");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
    }
    
    
    /**
     * @author Rs yinc
     * test achat d'actions avec une qte autorisee
     */
    @Test
    protected void testAchatActionParametersAreCorrectSuccess() {
       
        portefeuille.acheter(franceTV, 3);
        
        String expectedMsg = "{FranceTV=3}";
        //Action
        final String currentPortefeuille = portefeuille.toString();

        
        //Assert
        Assertions.assertEquals(expectedMsg, currentPortefeuille);
    }
    
    
    /**
     * @author Rs yinc
     * test definition composition avec un pourcentage negatif
     */
    @Test
    public void testDefinitionMauvaisParamettreShouldFail(){
        
        final String expectedMessage = "Pas de valeur negative en pourcentage";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            franceTV.enrgComposition(france2, -0.50f);
        }, "Pas de valeur negative en pourcentage");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
    }
    
    /**
     * @author Rs yinc
     * test definition composition avec un pourcentage positif
     */
    @Test
    protected void testConstructorParametersAreCorrectSuccess() {
       
        String expectedMsg = "voici la composition de cette action: \n" +
                                      "Action: france 4, Composition: 50.0\n" +
                                      "Action: france 2, Composition: 50.0\n";
        //Action
        final String currentComposition = franceTV.consulterComposition();

        
        //Assert
        Assertions.assertEquals(expectedMsg, currentComposition);
    }


    /**
     * Génère le message attendu pour l'évolution de la valeur d'une action.
     * @author RS & Yinc
     * @param dateJour La date de la veille ou de la semaine precedente.
     * @param valeurVeille La valeur de l'action au jour précédent.
     * @param valeurJour La valeur de l'action au jour actuel.
     * @param pourcentageEvolution Le pourcentage d'évolution entre les deux jours.
     * @param direction La direction de l'évolution (↑ pour une augmentation, ↓ pour une diminution).
     * @return Le message formaté représentant l'évolution de la valeur de l'action.
     */

    private String getExpectedMessage(String dateJour, double valeurVeille, double valeurJour, double pourcentageEvolution, String direction) {
        return "Evolution de la valeur de l'action: \n" +
                "valeur au " + dateJour + ": " + valeurVeille + "\n" +
                "valeur au Jour{annee=2024, noJour=1}: " + valeurJour + "\n"+
                "Evolution : " + pourcentageEvolution + "% " + direction;
    }

    /**
     * @author Rs yinc
     *visualisation evolution de la valeur d une action composee par rapport a la veille avec le jour actuel etant le 1er jour de lannee
     */
    @Test
    public void testEvaluationActionComposeVeille() {
        String expectedMsg = getExpectedMessage("Jour{annee=2023, noJour=365}", 31.75, 31.25, -1.57, "↓");
        Assertions.assertEquals(expectedMsg, franceTV.evolutionValeurVeilleAction(jourActuelle));
    }

    /**
     * @author Rs yinc
     *visualisation evolution de la valeur d une action composee par rapport a la semaine precedente avec le jour actuel etant le 1er jour de lannee
     */
    @Test
    public void testEvaluationActionComposeSemaine() {
        String expectedMsg = getExpectedMessage("Jour{annee=2023, noJour=359}", 33.25, 31.25, -6.02, "↓");
        Assertions.assertEquals(expectedMsg, franceTV.evolutionValeurSemaineAction(jourActuelle));
    }




}
