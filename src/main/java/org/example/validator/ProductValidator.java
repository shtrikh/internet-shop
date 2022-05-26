package org.example.validator;

import org.example.enums.Category;
import org.example.enums.Size;

import java.util.Arrays;

public class ProductValidator {

    private static final String INVALID_PRICE = "Invalid price: ";

    public void validateSize(String sizeString){
        if(Arrays.stream(Size.values()).noneMatch(size -> size.toString().equals(sizeString))){
            throw new IllegalArgumentException("Invalid size: " + sizeString);
        }
    }
    public void validateCategory(String categoryString){
        if(Arrays.stream(Category.values()).noneMatch(category -> category.toString().equals(categoryString))){
            throw new IllegalArgumentException("Invalid size: " + categoryString);
        }
    }
    public void validatePrice(String priceString){
        try {
            if(Double.parseDouble(priceString) <= 0){
                throw new IllegalArgumentException(INVALID_PRICE + priceString);
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALID_PRICE + priceString);
        }

    }
    public void validatePriceUah(String priceUahString){
        try {
            if(Double.parseDouble(priceUahString) <= 0){
                throw new IllegalArgumentException(INVALID_PRICE + priceUahString);
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALID_PRICE + priceUahString);
        }

    }
}
