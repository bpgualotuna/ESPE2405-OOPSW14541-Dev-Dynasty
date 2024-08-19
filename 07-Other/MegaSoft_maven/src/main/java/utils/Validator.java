package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
public class Validator {

    public static boolean validatePassword(String text) {
        // Expresión regular para verificar que la contraseña tenga exactamente 8 caracteres
        // e incluya al menos una letra mayúscula, una letra minúscula y un carácter especial
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$";
         List<Character> caracteres = new ArrayList<>();
        
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                if (c == 'z'){
                    caracteres.add('a');
                } else if (c == 'Z') {
                    caracteres.add('A');
                } else {
                    caracteres.add((char) (c + 1));
                }
            } else {
                caracteres.add(c);
            }
        }
        
        boolean matches = text.matches(regex);
        return matches;
    }
    public static String generarContraseña(String texto) {
        List<Character> caracteres = new ArrayList<>();
        
        // Reemplazar cada letra con la siguiente en el alfabeto
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                if (c == 'z') {
                    caracteres.add('a');
                } else if (c == 'Z') {
                    caracteres.add('A');
                } else {
                    caracteres.add((char) (c + 1));
                }
            } else {
                caracteres.add(c);
            }
        }
        
        // Agregar caracteres especiales
        char[] caracteresEspeciales = "!@#$%^&*()".toCharArray();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            caracteres.add(caracteresEspeciales[random.nextInt(caracteresEspeciales.length)]);
        }
        
        // Agregar números
        char[] numeros = "0123456789".toCharArray();
        for (int i = 0; i < 2; i++) {
            caracteres.add(numeros[random.nextInt(numeros.length)]);
        }
        
        // Mezclar los caracteres
        Collections.shuffle(caracteres);
        
        // Unir los caracteres para formar la contraseña
        StringBuilder contraseña = new StringBuilder();
        for (char c : caracteres) {
            contraseña.append(c);
        }
        
        return contraseña.toString();
    }
    
    
}
