package cl.huellaschile.medicalcon.validate;

import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidRutValidator implements ConstraintValidator<ValidRut, String> {

    private Boolean isOptional;

    @Override
    public void initialize(ValidRut validRut) {
        this.isOptional = validRut.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean validRut = validateRut(value);
        return Boolean.TRUE.equals(isOptional) ? (validRut || (Strings.isNullOrEmpty(value))) : validRut;
    }

    /**
     * Validate format rut XXXXXXXX-X
     */
    public static Boolean validateRut(String rut) {
        final Pattern pattern = Pattern.compile("^\\d+-[0-9kK]$");
        final Matcher matcher = pattern.matcher(rut);
        if (!matcher.matches()) {
            return false;
        } else {
            String[] stringRut = rut.split("-");
            return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
        }
    }

    /**
     * Validate DV
     */
    private static String dv(String rut) {
        int m = 0;
        int s = 1;
        int t = Integer.parseInt(rut);
        for (; t != 0; t /= 10) {
            s = (s + t % 10 * (9 - m++ % 6)) % 11;
        }
        return (s > 0) ? String.valueOf(s - 1) : "k";
    }
}
