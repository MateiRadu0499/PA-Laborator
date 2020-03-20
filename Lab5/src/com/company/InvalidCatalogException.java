package com.company;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException() {
        super("Invalid catalog file.");
    }
}

