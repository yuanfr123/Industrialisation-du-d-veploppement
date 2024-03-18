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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortefeuilleTest {
    
    Jour DEFAULT_DAY = new Jour(1,1);
    Jour INCORRECT_DAY = new Jour(2,1);
    
    
    public PortefeuilleTest(){
    }

    @Test
    /**
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
     * tester ur des dates différentes 
     * portefeuille sur date +1 
     * enrgeistrement action date -1
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
    @Test
     /**
     * on test pour visualiser le portefeuille
     */
    public void testToString() {
        // Action 
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
}
