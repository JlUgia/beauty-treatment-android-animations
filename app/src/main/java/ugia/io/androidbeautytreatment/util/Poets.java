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
