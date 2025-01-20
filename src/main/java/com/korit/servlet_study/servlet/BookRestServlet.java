package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.BookDao;
import com.korit.servlet_study.entity.Author;
import com.korit.servlet_study.entity.Book;
import com.korit.servlet_study.entity.BookCategory;
import com.korit.servlet_study.entity.Publisher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {

    private BookDao bookDao;

    public BookRestServlet() {
        bookDao = BookDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = new Author(1, "테스트저자");
        Publisher publisher = new Publisher(10, "테스트출판사");
        BookCategory bookCategory = new BookCategory(100, "테스트카테고리");
        
        ObjectMapper objectMapper = new ObjectMapper();
        
//        List<Book> books = bookDao.findAllBySearchValue("홍");
        
        Book book = Book.builder()
                .bookId(200)
                .bookName("테스트도서명")
                .isbn("abcd1234")
                .bookImgUrl("http://test.com/1234")
                .authorId(author.getAuthorId())
                .publisherId(publisher.getPublisherId())
                .categoryId(bookCategory.getCategoryId())
                .author(author)
                .publisher(publisher)
                .bookCategory(bookCategory)
                .build();
        
        String jsonBook = objectMapper.writeValueAsString(book);
//        String jsonBook = objectMapper.writeValueAsString(books);
        System.out.println(jsonBook);


        resp.setContentType("application/json");
        resp.getWriter().println(jsonBook);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
