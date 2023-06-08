package com.code.isbntools;

import com.code.isbntools.dataservice.ExternalDataService;

public class StockManager {
    private final ExternalDataService service;

    public StockManager(ExternalDataService isbnDataService) {
        this.service = isbnDataService;
    }

    public String getLocatorCode(String isbnNumber) {
        Book book = this.service.getBookByIsbnNumber(isbnNumber);

        return isbnNumber.substring(isbnNumber.length() - 4) +
                book.author().substring(0, 1) +
                book.title().split(" ").length;
    }
}
