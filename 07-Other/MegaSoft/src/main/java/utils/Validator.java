package utils;

/**
 *
 * @author Dev Dynasty, DCCO-ESPE
 */
public class Validator {

    public static boolean validatePassword(String password) {
        // Expresión regular para verificar que la contraseña tenga exactamente 8 caracteres
        // e incluya al menos una letra mayúscula, una letra minúscula y un carácter especial
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[@$!%?&])[A-Za-z@$!%?&]{8}$";
        boolean matches = password.matches(regex);
        return matches;
    }
}
