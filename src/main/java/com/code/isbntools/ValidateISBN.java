package com.code.isbntools;

import com.code.isbntools.validators.ISBNValidator;
import com.code.isbntools.validators.LongISBNValidator;
import com.code.isbntools.validators.ShortISBNValidator;

import java.util.HashMap;
import java.util.Map;

public class ValidateISBN {
    private final Map<IsbnVersion, ISBNValidator> validators = new HashMap<>();

    public ValidateISBN() {
        validators.put(IsbnVersion.SHORT, new ShortISBNValidator());
        validators.put(IsbnVersion.LONG, new LongISBNValidator());
    }

    public boolean checkISBN(ISBN isbn) {
        try {
            return this.validators.get(isbn.version()).validate(isbn);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
