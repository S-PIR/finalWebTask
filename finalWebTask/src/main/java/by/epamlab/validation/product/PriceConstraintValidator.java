package by.epamlab.validation.product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceConstraintValidator implements ConstraintValidator<ValidPrice, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PRICE_PATTERN = "[0-9]+\\.[0-9]{2}";

    @Override
    public void initialize(final ValidPrice constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String price, final ConstraintValidatorContext context) {
        pattern = Pattern.compile(PRICE_PATTERN);
        matcher = pattern.matcher(price);
        return matcher.matches();
    }
}
