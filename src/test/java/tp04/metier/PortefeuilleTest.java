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
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class PortefeuilleTest {
    public PortefeuilleTest(){}

    @Test
    /**
     * Méthode de test:
     * Le portfeuille exist.
     * HashMap doit être manipulable.
     * HashMap est vide au commencement.
     */
    protected void testConstructorMapLignesInitialization() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();

        // Action
        Map<?, ?> mapLignes = portefeuille.getMapLignes();

        // Assert
        assertNotNull(mapLignes, "mapLignes should not be null");//Exist ou pas
        assertTrue(mapLignes instanceof Map, "mapLignes should be an instance of HashMap");
        assertTrue(mapLignes.isEmpty(), "mapLignes should be empty initially");//Vide 
    }
}
