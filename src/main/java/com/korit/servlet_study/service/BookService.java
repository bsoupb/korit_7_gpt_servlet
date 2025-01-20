package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.BookDao;
import com.korit.servlet_study.entity.Book;

public class BookService {
    private BookDao bookDao;
    private static BookService bookService;

    private BookService() {
        bookDao = BookDao.getInstance();
    }

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    public Book addBook(Book book) {
        bookDao.saveAuthor(book.getAuthor());
        bookDao.savePublisher(book.getPublisher());
        bookDao.saveBookCategory(book.getBookCategory());

        return bookDao.saveBook(book).get();
    }


}