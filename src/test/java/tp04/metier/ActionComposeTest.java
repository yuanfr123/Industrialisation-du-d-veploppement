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
 * @author yinchenwang
 */

public class ActionComposeTest {
    
   private Portefeuille portefeuille = new Portefeuille();
   
   private ActionSimple france2 = new ActionSimple("france 2");
   
   private ActionSimple france4 = new ActionSimple("france 4");
    
   private ActionComposee  franceTV = new ActionComposee("FranceTV");
   
   @BeforeEach
    public void setUp() {
     
        france2.enrgCours(new Jour(2024, 20), 30.2f);
        france4.enrgCours(new Jour(2024, 20), 32.3f);
    }
    
    /**
     * afficher la composition d'une action composee
     * 1) creation 2 action simple.
     * 2) creation  d'une action composee.
     * 3) definition la compostion action composee
     * 4) test la methode
     */
    @Test
    public void testConsulterCompositionActionComposee() {
        
        // Créer une action composée
       franceTV.enrgComposition(france4, 0.50f);
       franceTV.enrgComposition(france2, 0.50f);
       
       

        // Vérifier que la composition de l'action composée est correcte
        String compositionAttendue = "voici la composition de cette action: \n" +
                                      "Action: france 4, Composition: 50.0\n" +
                                      "Action: france 2, Composition: 50.0\n";
        Assertions.assertEquals(compositionAttendue, franceTV.consulterComposition());
    }
    
    /**
     * test achat action composee
     * 1) creation 2 action simple.
     * 2) creation  d'une action composee.
     * 3) definition la compostion action composee
     * 4) achat d'une action composee et d'une action simple.
     * 5) test la methode portefeuille.
     */
    @Test
    public void testAcheterActionComposee() {
        
  
       franceTV.enrgComposition(france4, 0.50f);
       franceTV.enrgComposition(france2, 0.50f);
       
       
       
       portefeuille.acheter(franceTV, 3);
       
       portefeuille.acheter(france2, 5);
       
       //test pour verifier la modifi de qte d'une qction deja presente
       
       portefeuille.acheter(franceTV, 3);

        // Vérifier que la composition de l'action composée est correcte
        String compositionAttendue = "{FranceTV=6, france 2=5}";
                                 
        Assertions.assertEquals(compositionAttendue, portefeuille.toString());
    }
    
    /**
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
     * test definition composition avec un pourcentage positif
     */
    @Test
    protected void testConstructorParametersAreCorrectSuccess() {
       
        franceTV.enrgComposition(france2, 0.50f);
        
        String expectedMsg = "voici la composition de cette action: \n" +
                                      "Action: france 2, Composition: 50.0\n";
        //Action
        final String currentComposition = franceTV.consulterComposition();

        
        //Assert
        Assertions.assertEquals(expectedMsg, currentComposition);
    }

}
