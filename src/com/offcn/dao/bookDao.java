package com.offcn.dao;

import com.offcn.bean.Book;
import com.offcn.bean.Page;

import java.util.List;

public interface bookDao {
    int insertBook(Book book);
    List<Book> findAll(Page page);
    List<Book> findBookByCondition(Book book);
    int updateBookById(Book book);
    Book selectBookById(int id);
    int delBookById(int id);
    int findCount();
}
