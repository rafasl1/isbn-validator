package com.code.isbntools.validators;

import com.code.isbntools.ISBN;

import static com.code.isbntools.errors.ISBNErrorMessages.ISBN10_INVALID_LENGTH_MESSAGE;
import static com.code.isbntools.errors.ISBNErrorMessages.ISBN_MUST_ONLY_CONTAIN_NUMBERS;

public class ShortISBNValidator implements ISBNValidator {
    private static final int SHORT_ISBN_LENGTH = 10;

    public boolean validate(ISBN isbn) {
        String number = isbn.value();
        validateLength(number);

        int sum = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            var iesimalCharacter = number.charAt(i);

            if (characterIsAlphabetical(iesimalCharacter)) {
                sum += getAlphabeticalCharacterValueInPosition(iesimalCharacter, i);
            } else {
                var numberAtPositionI = Character.getNumericValue(iesimalCharacter);
                var multiplicationFactor = SHORT_ISBN_LENGTH - i;
                sum += numberAtPositionI * multiplicationFactor;
            }
        }

        return sum % 11 == 0;
    }

    private void validateLength(String isbn) {
        if (isbn.length() != SHORT_ISBN_LENGTH) {
            throw new NumberFormatException(ISBN10_INVALID_LENGTH_MESSAGE);
        }
    }

    private boolean characterIsAlphabetical(char character) {
        return !Character.isDigit(character);
    }

    private int getAlphabeticalCharacterValueInPosition(char character, int characterPosition) {
        if (isLastCharacterOfWordAndIsAnX(character, characterPosition)) {
            return 10;
        } else {
            throw new NumberFormatException(ISBN_MUST_ONLY_CONTAIN_NUMBERS);
        }
    }

    private boolean isLastCharacterOfWordAndIsAnX(char character, int characterPosition) {
        return character == 'X' && characterPosition == 9;
    }
}
