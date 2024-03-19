/*
 * Copyright 2024 alexphr.
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
 *
 * @author groupe1;
 */
public class PortefeuilleAcheterActionTest {

    /**
    * Test pour vérifier si l'action achetée et sa quantité est contenue dans le portefeuille
    */
    @Test
    protected void testActionSimpleAchetee() {
        // Arrange
        final Portefeuille portefeuille = new Portefeuille();
        final ActionSimple action = new ActionSimple("France 2");
        final int qteAchetee = 3; // Quantité d'actions achetées
        

        // Action
        portefeuille.acheter(action, qteAchetee);
        

        // Assert
        // Vérifier que l'action achetée est contenue dans le portefeuille
        Assertions.assertTrue(portefeuille.getMapLignes().containsKey(action));
        // Vérifier que la quantité d'actions dans le portefeuille est correcte
        Assertions.assertEquals(qteAchetee, portefeuille.getMapLignes().get(action).getQte());
    }
}


