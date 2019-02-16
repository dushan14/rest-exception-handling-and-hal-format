package com.practice.demo.dao;

import com.practice.demo.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book save(Book book);

    Book update(Book book, int id);

    Book getById(int id);

    void deleteById(int id);
}
