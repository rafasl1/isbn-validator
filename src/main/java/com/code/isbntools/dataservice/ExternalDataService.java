package com.code.isbntools.dataservice;

import com.code.isbntools.Book;

public interface ExternalDataService {
    public Book getBookByIsbnNumber(String isbnNumber);
}
