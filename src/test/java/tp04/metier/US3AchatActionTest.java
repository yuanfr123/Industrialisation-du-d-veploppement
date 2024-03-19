/*
 * Copyright 2024 ldasi.
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Nous définissons un portefeuille de test
 * ainsi qu'une action de test qui sera achetée
 * @author groupe 1
 */
public class US3AchatActionTest {
   private final Portefeuille portefeuille = new Portefeuille();
   
   private static final ActionSimple france2 = new ActionSimple("france 2");

    /**
     * Test vérifiant qu'une action est ajoutée au portefeuille
     * lors de l'achat lorsque
     * les paramètres sont bons
     */
    @Test
    protected void testAchatActionCorrectSuccess() {
        //Arrange
        portefeuille.acheter(france2, 2);

        //Action
        final String expectedToString = "{france 2=2}";
        final String currentToString = portefeuille.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString,
                "Basic construction");
    }
    
    /**
     * Test vérifiant qu'une action est ajoutée au portefeuille 
     * lors de l'achat lorsque les paramètres sont bons
     * et que l'action est déja présente dans le portefeuille
     */
    @Test
    protected void testAchatActionDejaPresenteCorrectSuccess() {
        //Arrange
        portefeuille.acheter(france2, 2);
        portefeuille.acheter(france2, 3);

        //Action
        final String expectedToString = "{france 2=5}";
        final String currentToString = portefeuille.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    
    /**
     * Test vérifiant qu'une quantité négative ou nulle d'action 
     * ne puisse pas etre achetée
     */
    @Test
    protected void testAchatActionNegativeIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "achter au moins une action";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = 
                Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            portefeuille.acheter(france2, 0);
        });
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
}

