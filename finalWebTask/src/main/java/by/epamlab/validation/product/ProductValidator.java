package by.epamlab.validation.product;

import by.epamlab.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("productValidator")
public class ProductValidator implements Validator {
    @Override
    public boolean supports(final Class<?> clazz) {
        return ProductDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "message.product.name.required", "Product name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "message.product.description.required", "Product description is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "message.product.price.required", "Product price is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "message.product.category.required", "Product category is required.");

    }

}
