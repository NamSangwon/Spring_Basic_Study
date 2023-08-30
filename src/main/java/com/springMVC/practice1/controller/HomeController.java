package com.springMVC.practice1.controller;

import com.springMVC.practice1.dao.ContentDao;
import com.springMVC.practice1.dao.IDao;
import com.springMVC.practice1.dto.ContentDto;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SqlSession sqlSession;

    //    ContentDao dao;
//    @Autowired
//    public void setDao(ContentDao dao) {
//        this.dao = dao;
//    }

    @RequestMapping("/")
    public String home(Locale locale, Model model){
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    @RequestMapping("/list")
    public String list(Model model){
//        ArrayList<ContentDto> dtos = dao.listDao();
//        model.addAttribute("list", dtos);
        IDao dao = sqlSession.getMapper(IDao.class);
        model.addAttribute("list", dao.listDao());
        return "/list";
    }

    @RequestMapping("/writeForm")
    public String writeForm(){
        return "/writeForm";
    }

    @RequestMapping("/write")
    public String write(HttpServletRequest request, Model model){
        //dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
        IDao dao = sqlSession.getMapper(IDao.class);
        dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
        return "redirect:list";
    }

    @RequestMapping("/view")
    public String view(){
        return "/view";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model){
        //dao.deleteDao(request.getParameter("mId"));
        IDao dao = sqlSession.getMapper(IDao.class);
        dao.deleteDao(request.getParameter("mId"));
        return "redirect:list";
    }
}
