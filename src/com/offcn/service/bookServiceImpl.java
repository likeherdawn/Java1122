package com.offcn.service;

import com.offcn.bean.Book;
import com.offcn.bean.Page;
import com.offcn.dao.bookDao;
import com.offcn.dao.bookDaoImpl;
import com.offcn.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class bookServiceImpl implements bookService{
    bookDao bd = new bookDaoImpl();
    @Override
    public int insertBook(Book book) {
        return bd.insertBook(book);
    }

    @Override
    public List<Book> findAll(Page page) {
        return bd.findAll(page);
    }

    @Override
    public List<Book> findBookByCondition(Book book) {
        return bd.findBookByCondition(book);
    }

    @Override
    public int updateBookById(Book book) {
        return bd.updateBookById(book);
    }

    @Override
    public Book selectBookById(int id) {
        return bd.selectBookById(id);
    }

    @Override
    public int delBookById(int id) {
        return bd.delBookById(id);
    }

    @Override
    public int findCount() {
        return bd.findCount();
    }

}

