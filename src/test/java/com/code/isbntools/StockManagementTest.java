package com.code.isbntools;

import com.code.isbntools.dataservice.ExternalDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTest {
    private StockManager stockManager;
    private ExternalDataService dataService = mock(ExternalISBNDataServiceMock.class);

    @BeforeEach
    public void configure() {
        stockManager = new StockManager(dataService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {
        String isbnNumber = "0140177396";
        ISBN isbn = new ISBN(isbnNumber, IsbnVersion.SHORT);

        when(dataService.getBookByIsbnNumber(isbnNumber)).thenReturn(getMockedBook());
        String locatorCode = stockManager.getLocatorCode(isbn.value());

        assertEquals("7396J4", locatorCode);
        verify(dataService, times(1)).getBookByIsbnNumber(anyString());
    }

    private Book getMockedBook() {
        return new Book(new ISBN("0140177396", IsbnVersion.SHORT), "Of Mice and Men", "J. Steinbeck");
    }
}
