package com.practice.demo.service;

import com.practice.demo.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book addBook(Book book);

    Book updateBook(Book book, int id);

    Book getBookById(int id);

    void deleteBookById(int id);
}
