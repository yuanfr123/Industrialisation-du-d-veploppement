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
        

/**
 *
 * @author yinchenwang
 */

public class ConsulterActionComposeTest {
    
   
    
    @Test
    public void testConsulterCompositionActionComposee() {
        // Ajouter des actions simples au panier
        ActionSimple france2 = new ActionSimple("france 2");
        ActionSimple france4 = new ActionSimple("france 4");
        
        france2.enrgCours(new Jour(2024, 20), 30.2f);
        france4.enrgCours(new Jour(2024, 20), 32.3f);
        // Créer une action composée
       ActionComposee franceTV = new ActionComposee("FranceTV");
       franceTV.enrgComposition(france4, 0.50f);
       franceTV.enrgComposition(france2, 0.50f);
       
       

        // Vérifier que la composition de l'action composée est correcte
        String compositionAttendue = "voici la composition de cette action: \n" +
                                      "Action: france 4, Composition: 50.0\n" +
                                      "Action: france 2, Composition: 50.0\n";
        Assertions.assertEquals(compositionAttendue, franceTV.consulterComposition());
    }
}
