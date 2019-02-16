package com.practice.demo.dao;

import com.practice.demo.exception.bookNotFound.BookNotFoundException;
import com.practice.demo.model.Book;
import com.practice.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book, int id) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public Book getById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException(id)
                );
    }

    @Override
    public void deleteById(int id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(id);
        }
    }

}
