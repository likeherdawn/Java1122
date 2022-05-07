package com.offcn.dao;

import com.offcn.bean.Book;
import com.offcn.bean.Page;
import com.offcn.utils.DataSourceUtils;
import com.offcn.utils.DateUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class bookDaoImpl implements bookDao{
    @Override
    public int insertBook(Book book) {
        int result = 0;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into book value (null,?,?,?,?,?,?)";
        try {
            result = qr.update(sql,book.getBooknum(),book.getBookname(),book.getBookauthor(),book.getBookpublish(),book.getBookdate(),book.getBookprice());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> findAll(Page page) {
        List<Book> list = null;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        //?->（当前页-1）*每页条数 ?->每页条数
        String sql = "select * from book limit ? , ?";
        try {
            list = qr.query(sql,new BeanListHandler<>(Book.class),(page.getCurrentPage() - 1) * Page.PAGESIZE, Page.PAGESIZE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Book> findBookByCondition(Book book) {
        List<Book> list = null;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = getSql(book);
        try {
            list = qr.query(sql,new BeanListHandler<>(Book.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateBookById(Book book) {
        int result = 0;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update book set booknum = ?,bookname = ?,bookauthor = ?,bookpublish = ?,bookdate = ?,bookprice = ? where id = ?";
        try {
            result = qr.update(sql,new Object[]{book.getBooknum(),book.getBookname(),book.getBookauthor(),book.getBookpublish(),book.getBookdate(),book.getBookprice(),book.getId()});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Book selectBookById(int id) {
        Book book = null;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from book where id = ?";
        try {
            book = qr.query(sql, new BeanHandler<>(Book.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

    @Override
    public int delBookById(int id) {
        int result = 0;
        //传递数据源
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from book where id = ?;";
        try {
            result = qr.update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int findCount() {
        Long count = 0L;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from book";
        try {
            count = (Long) qr.query(sql,new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count.intValue();
    }


    private String getSql(Book book) {
        StringBuilder sb = new StringBuilder("select * from book where 1 = 1");
        if (book.getBooknum() != null && !"".equals(book.getBooknum().trim())){
            sb.append(" and booknum like '%").append(book.getBooknum()).append("%'");
        }
        if (book.getBookname() != null && !"".equals(book.getBookname().trim())){
            sb.append(" and bookname like '%").append(book.getBookname()).append("%'");
        }
        if (book.getBookauthor() != null && !"".equals(book.getBookauthor().trim())){
            sb.append(" and bookauthor like '%").append(book.getBookauthor()).append("%'");
        }
        if (book.getBookpublish() != null && !"".equals(book.getBookpublish().trim())){
            sb.append(" and bookpublish like '%").append(book.getBookpublish()).append("%'");
        }
        if (book.getBookdate() != null){
            sb.append(" and bookdate = '").append(DateUtils.setString(book.getBookdate())).append("'");
        }
        if (book.getBookprice() != 0){
            sb.append(" and bookprice = ").append(book.getBookprice());
        }
        return sb.toString();
    }
}
