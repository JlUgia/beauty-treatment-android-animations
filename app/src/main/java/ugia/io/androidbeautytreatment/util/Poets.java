/*
 * Poets.java
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ugia.io.androidbeautytreatment.util;

import ugia.io.androidbeautytreatment.model.Contact;

/**
 * Ghost data provider utils that simulates a source of information.
 * <p/>
 * Created by joseluisugia on 18/11/15.
 */
public class Poets {

    public static Contact[] generate() {

        return new Contact[] {

                new Contact("Pio Baroja", "123", true),
                new Contact("Miguel Hernandez", "456", false),
                new Contact("Miguel de Cervantes", "789", false),
                new Contact("Juan Ramón Jimenez", "101", true),
                new Contact("Miguel de Unamuno", "112", true),
                new Contact("Antonio Machado", "175", false),
                new Contact("Federico García Lorca", "623", false),
                new Contact("Ramiro de Maeztu", "999", true),
                new Contact("Ramón María del Valle-Inclán", "747", false),
                new Contact("Ángel Ganivet", "622", false),
                new Contact("Enrique de Mesa", "278", true),
                new Contact("Azorín", "345", false)
        };
    }

}
