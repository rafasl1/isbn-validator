package com.code.isbntools;

import com.code.isbntools.dataservice.ExternalDataService;

public class ExternalISBNDataServiceMock implements ExternalDataService {
    @Override
    public Book getBookByIsbnNumber(String isbnNumber) {
        return new Book(new ISBN("0140177396", IsbnVersion.SHORT), "Of Mice and Men", "J. Steinbeck");
    }
}
