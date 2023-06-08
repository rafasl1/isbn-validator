package com.code.isbntools.validators;

import com.code.isbntools.ISBN;

import static com.code.isbntools.errors.ISBNErrorMessages.*;

public class LongISBNValidator implements ISBNValidator {
    private static final int LONG_ISBN_LENGTH = 13;

    public boolean validate(ISBN isbn) {
        String number = isbn.value();
        validateLength(number);

        int sum = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            var iesimalCharacter = number.charAt(i);
            var numberAtPositionI = Character.getNumericValue(iesimalCharacter);
            var multiplicationFactor = getMultiplicationFactor(i);
            sum += numberAtPositionI * multiplicationFactor;
        }

        return sum % 10 == 0;
    }

    private void validateLength(String isbnNumber) {
        if (isbnNumber.length() != LONG_ISBN_LENGTH) {
            throw new NumberFormatException(ISBN13_INVALID_LENGTH_MESSAGE);
        }
    }

    private int getMultiplicationFactor(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 3;
        }
    }

}
