/*
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
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
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionSimpleTest {

    private static final Jour DEFAULT_j = new Jour(1,2000);
    private static final int DEFAULT_VALUE = 1;
    private static final int INCORRECT_DAY = 0;
    private static final int INCORRECT_YEAR = 0;
    private static final String DEFAULT_LIB = "France 2";
    private static final String INCORRECT_LIB = "";
    private static final int INCORRECT_VALUE = -1;

    public ActionSimpleTest() {
    }

    @Test
    protected void testConstructorParametersAreCorrectSuccess() {
        //Arrange
        final ActionSimple act = new ActionSimple(DEFAULT_LIB);

        //Action
        final String expectedToString = DEFAULT_LIB;
        final String currentToString = act.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    

    @Test
    protected void testConstructorDayIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "le vide n'est pas un lib valide";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new ActionSimple(INCORRECT_LIB);
        }, "le vide n'est pas un lib valide");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
    
    @Test
    protected void testenrgCoursValueIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "on ne peut pas enregistrer un cours négatif pour l'action "+ DEFAULT_LIB;
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new ActionSimple(DEFAULT_LIB).enrgCours(DEFAULT_j, INCORRECT_VALUE);
        }, "le vide n'est pas un lib valide");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
    
    @Test
    protected void testenrgCoursDayIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "le cours pour "+DEFAULT_LIB+" est déja enregistré a cette date";
        //Action and asserts
        final ActionSimple act = new ActionSimple(DEFAULT_LIB);
        act.enrgCours(DEFAULT_j, DEFAULT_VALUE);
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            act.enrgCours(DEFAULT_j, DEFAULT_VALUE);
        }, "le vide n'est pas un lib valide");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
    
    @Test
    protected void testValeurParametersAreCorrectSuccess() {
        //Arrange
        final ActionSimple act = new ActionSimple(DEFAULT_LIB);

        //Action
        final String expectedToString = DEFAULT_LIB;
        final String currentToString = act.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    
    
}

