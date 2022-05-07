package com.offcn.servlet;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.offcn.bean.Book;
import com.offcn.bean.Page;
import com.offcn.dao.bookDao;
import com.offcn.dao.bookDaoImpl;
import com.offcn.service.bookService;
import com.offcn.service.bookServiceImpl;
import com.offcn.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    bookService bs = new bookServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("add".equals(method)){
            add(request,response);
        }else if ("findAll".equals(method)){
            findAll(request,response);
        }else if ("findBookByCondition".equals(method)){
            findBookByCondition(request,response);
        }else if ("preupdate".equals(method)){
            preupdate(request,response);
        }else if ("update".equals(method)){
            update(request,response);
        }else if ("del".equals(method)){
            del(request,response);
        }
    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int result = bs.delBookById(Integer.parseInt(id));
        if (result > 0){
            response.sendRedirect("BookServlet?method=findAll");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("defeated");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        String id = request.getParameter("id");
        String booknum = request.getParameter("booknum");
        String bookname = request.getParameter("bookname");
        String bookauthor = request.getParameter("bookauthor");
        String bookpublish = request.getParameter("bookpublish");
        String bookdate = request.getParameter("bookdate");
        String bookprice = request.getParameter("bookprice");
        Book book = new Book();
        book.setId(Integer.parseInt(id));
        book.setBooknum(booknum);
        book.setBookname(bookname);
        book.setBookauthor(bookauthor);
        book.setBookpublish(bookpublish);
        try {
            book.setBookdate(DateUtils.setDate(bookdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setBookprice(Integer.parseInt(bookprice));
        bs.updateBookById(book);
        response.sendRedirect("BookServlet?method=findAll");
    }

    private void preupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Book b = bs.selectBookById(Integer.parseInt(id));
        //System.out.println(b);
        request.setAttribute("book",b);
        request.getRequestDispatcher("preupdate.jsp").forward(request,response);
    }

    private void findBookByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String booknum = request.getParameter("booknum");
        String bookname = request.getParameter("bookname");
        String bookauthor = request.getParameter("bookauthor");
        String bookpublish = request.getParameter("bookpublish");
        String bookdate = request.getParameter("bookdate");
        String bookprice = request.getParameter("bookprice");
        Book book = new Book();
        book.setBooknum(booknum);
        book.setBookname(bookname);
        book.setBookauthor(bookauthor);
        book.setBookpublish(bookpublish);
        if (bookdate != null && !"".equals(bookdate.trim()) ){
            try {
                book.setBookdate(DateUtils.setDate(bookdate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (bookprice != null && !"".equals(bookprice.trim())){
            book.setBookprice(Integer.parseInt(bookprice));
        }
        List<Book> bbc = bs.findBookByCondition(book);
        request.setAttribute("fa",bbc);
        request.getRequestDispatcher("showBook.jsp").forward(request,response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //计算分页的信息 开始
        //当前页 默认查询的页码为第一页
        int currentPage = 1;
        String currPage = request.getParameter("currPage");
        if (currPage != null && !"".equals(currPage)){
            currentPage = Integer.parseInt(currPage);
        }
        Page page = new Page();
        //设置当前页
        page.setCurrentPage(currentPage);
        //计算总条数
        int totalNum = bs.findCount();
        //设置总条数
        page.setTotalNum(totalNum);
        //计算总页数
        int totalPage = 0;
        if (totalNum % Page.PAGESIZE == 0){
            totalPage = totalNum / Page.PAGESIZE;
        }else {
            totalPage = totalNum / Page.PAGESIZE + 1;
        }
        page.setTotalPage(totalPage);
        //System.out.println(page);
        List<Book> fa = bs.findAll(page);
        request.setAttribute("fa",fa);
        request.setAttribute("page",page);
        request.getRequestDispatcher("showBook.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        String booknum = request.getParameter("booknum");
        String bookname = request.getParameter("bookname");
        String bookauthor = request.getParameter("bookauthor");
        String bookpublish = request.getParameter("bookpublish");
        String bookdate = request.getParameter("bookdate");
        String bookprice = request.getParameter("bookprice");
        Book book = new Book();
        book.setBooknum(booknum);
        book.setBookname(bookname);
        book.setBookauthor(bookauthor);
        book.setBookpublish(bookpublish);
        try {
            book.setBookdate(DateUtils.setDate(bookdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setBookprice(Integer.parseInt(bookprice));
//        bookDao bd = new bookDaoImpl();
//        bd.insertBook(book);
        int result = bs.insertBook(book);
        response.sendRedirect("BookServlet?method=findAll");
    }

}
