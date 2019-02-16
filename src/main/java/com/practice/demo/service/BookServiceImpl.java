package com.practice.demo.service;

import com.practice.demo.dao.BookDao;
import com.practice.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getBooks() {
        return bookDao.getAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book updateBook(Book book, int id) {
        return bookDao.update(book, id);
    }
}
