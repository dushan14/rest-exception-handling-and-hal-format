package com.practice.demo.exception.bookNotFound;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int id) {
        super("Could not find Book with id: "+id);
    }
}
