package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.Author;
import com.korit.servlet_study.entity.Book;
import com.korit.servlet_study.entity.BookCategory;
import com.korit.servlet_study.entity.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {
    private DBConnectionMgr mgr;
    private static BookDao bookDao;

    private BookDao() {
        mgr = DBConnectionMgr.getInstance();
    }

    public static BookDao getInstance() {
        if (bookDao == null) {
            bookDao = new BookDao();
        }
        return bookDao;
    }

    public Optional<Author> saveAuthor(Author author) {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into author_tb values (default, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getAuthorName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                author.setAuthorId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(author);
    }

    public Optional<Publisher> savePublisher(Publisher publisher) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into publisher_tb values (default, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, publisher.getPublisherName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                publisher.setPublisherId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(publisher);
    }

    public Optional<BookCategory> saveBookCategory(BookCategory bookCategory) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into category_tb values (default, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookCategory.getCategoryName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bookCategory.setCategoryId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(bookCategory);
    }

    public Optional<Book> saveBook(Book book) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into book_tb values (default, ?, ?, ?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getBookName());
            ps.setInt(2, book.getAuthor().getAuthorId());
            ps.setString(3, book.getIsbn());
            ps.setInt(4, book.getPublisher().getPublisherId());
            ps.setInt(5, book.getBookCategory().getCategoryId());
            ps.setString(6, book.getBookImgUrl());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(book);
    }

    public List<Book> findAllBySearchValue(String searchValue) {
        List<Book> books = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con =  DBConnectionMgr.getInstance().getConnection();
            String sql = """
                        select
                            *
                        from
                            book_tb
                        where
                            book_name like concat ('%', ?, '%')                
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, searchValue);
            rs = ps.executeQuery();

            while(rs.next()) {
                books.add(Book.builder()
                        .bookId(rs.getInt("book_id"))
                        .bookName(rs.getString("book_name"))
                        .isbn(rs.getString("isbn"))
                        .authorId(rs.getInt("author_id"))
                                .author(new Author(rs.getInt("author_id"), rs.getString("author_name")))
                        .publisherId(rs.getInt("publisher_id"))
                        .categoryId(rs.getInt("category_id"))
                        .bookImgUrl((rs.getString("book_img_url")))
                        .build()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

}