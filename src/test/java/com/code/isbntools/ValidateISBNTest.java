package com.code.isbntools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.code.isbntools.IsbnVersion.LONG;
import static com.code.isbntools.IsbnVersion.SHORT;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {
    ValidateISBN validator = new ValidateISBN();

    // validate ISBN 10

    @Test
    @DisplayName("Espera que o numero ISBN 10 seja valido")
    public void expectThatISBN10NumberIsValid() {
        boolean result = validator.checkISBN(new ISBN("8550800651", SHORT));
        boolean result2 = validator.checkISBN(new ISBN("8550804606", SHORT));

        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    @DisplayName("Espera que o numero ISBN 10 seja invalido")
    public void expectThatISBN10NumberIsNotValid() {
        boolean result = validator.checkISBN(new ISBN("8550800650", SHORT));
        assertFalse(result);
    }

    @Test
    @DisplayName("Não permite que o numero ISBN 10 tenha menos do que dez digitos")
    public void expectThatISBNNumberDontHaveLessThanTenDigits() {
        assertThrows(NumberFormatException.class, () -> validator.checkISBN(new ISBN("855080065", SHORT)));
    }

    @Test
    @DisplayName("Não permite que sejam passados numeros ISBNs que não sejam numericos")
    public void expectThatIsbnIsNumerical() {
        assertThrows(NumberFormatException.class, () -> validator.checkISBN(new ISBN("helloworld", SHORT)));
    }

    @Test
    @DisplayName("Permite que numeros ISBN contenham X no final")
    public void expectThatIsbnCanEndWithAnX() {
        assertTrue(validator.checkISBN(new ISBN("012000030X", SHORT)));
    }


    // validate ISBN 13

    @Test
    @DisplayName("Deve permitir e processar um numero ISBN de 13 digitos")
    public void expectThat13DigitISBNCanBeProcessed() {
        assertTrue(validator.checkISBN(new ISBN("9780321125217", LONG)));
    }

    @Test
    @DisplayName("Deve permitir e processar um numero ISBN de 13 digitos")
    public void validateInvalidISBN13NUmber() {
        assertFalse(validator.checkISBN(new ISBN("9780321125299", LONG)));
    }
}
