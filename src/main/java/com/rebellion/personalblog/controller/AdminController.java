package com.rebellion.personalblog.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebellion.personalblog.entity.Admin;
import com.rebellion.personalblog.entity.Article;
import com.rebellion.personalblog.entity.Index;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private HttpSession session;
    private ObjectMapper mapper;
    List<Index> listJson;

    public AdminController(HttpSession session, ObjectMapper mapper) throws StreamReadException, DatabindException, IOException {
        this.session = session;
        this.mapper = mapper;
        listJson = mapper.readValue(new File("./articles/index.json"), new TypeReference<List<Index>>(){});
    }

    @GetMapping("")
    public String getAdminLoginPage() {
        // if already logged-in ==> dashboard
        if (session.getAttribute("usertype") != null) {
            return "redirect:/admin/dashboard";
        }
        // else ==> login form
        return "adminlogin";
    }

    @PostMapping("")
    public String verifyAdminLogin(@RequestBody Admin input) {
        Admin admin = new Admin();
        // if session not found ==> route to adminlogin ==> create session on success and route to dashboard
        if ((input.getUsername().equals(admin.getUsername())) && (input.getPassword().equals(admin.getPassword()))) {
            session.setAttribute("username", input.getUsername());
            session.setAttribute("password", input.getPassword());
            session.setAttribute("usertype", "admin");
            return "redirect:/admin/dashboard";
        }
        return "adminlogin";
    }

    @GetMapping("dashboard")
    public ModelAndView getAdminDashboardPage(ModelAndView modelAndView) throws StreamReadException, DatabindException, IOException {
        // if already logged-in ==> dashboard
        if (session.getAttribute("usertype") != null) {
            List<Index> list = mapper.readValue(new File("./articles/index.json"), new TypeReference<List<Index>>(){});
            modelAndView.addObject("list", list);
            modelAndView.setViewName("admindashboard");
            return modelAndView;
        }
        // else ==> login form
        modelAndView.setViewName("adminlogin");
        return modelAndView;
    }

    @GetMapping("createArticle")
    public String getCreateArticlePage() {
        // if already logged-in ==> createarticle
        if (session.getAttribute("usertype") != null) {
            return "createarticle";
        }
        // else ==> login form
        return "adminlogin";
    }

    @PostMapping("createArticle")
    public String saveArticleInJson(@RequestBody Article article) throws IOException {
        article.setId(listJson.size()+1);
        // write one article to sepearate json file
        mapper.writeValue(new File("./articles/article" + article.getId() + ".json"), article);

        // enter the entry to index json file
        Index index = new Index();
        index.setId(article.getId());
        index.setDate(article.getPublishdate());
        index.setTitle(article.getTitle());
        listJson.add(index);
        mapper.writeValue(new File("./articles/index.json"), listJson);

        return "redirect:/admin/dashboard";
    }

    @GetMapping("editArticle/{id}")
    public ModelAndView getEditArticlePage(@PathVariable int id, ModelAndView modelAndView) throws StreamReadException, DatabindException, IOException {
        Article article = mapper.readValue(new File("./articles/article" + id + ".json"), Article.class);
        modelAndView.addObject("model", article);
        modelAndView.setViewName("editarticle");
        return modelAndView;
    }

    // TODO: editArticle submission is inconsistent.
    @PostMapping("editArticle/{id}")
    public String postEditArticlePage(@PathVariable int id, @RequestBody Article input) throws StreamReadException, DatabindException, IOException {
        Article article = new Article();
        article.setTitle(input.getTitle());
        article.setPublishdate(input.getPublishdate());
        article.setContent(input.getContent());
        mapper.writeValue(new File("./articles/article" + id + ".json"), article);

        Index index = new Index();
        index.setId(id);
        index.setDate(article.getPublishdate());
        index.setTitle(article.getTitle());

        for (Index i : listJson) {
            if(i.getId() == id) {
                listJson.add(listJson.indexOf(i), index);
                break;
            }
        }
        mapper.writeValue(new File("./articles/index.json"), listJson);

        return "redirect:/admin/dashboard";
    }

    @GetMapping("clear")
    public ModelAndView deleteAllArticles(ModelAndView modelAndView) throws StreamReadException, DatabindException, IOException {
        for (Index i : listJson) {
            File file = new File("./articles/article" + i.getId() + ".json");
            file.delete();
        }
        FileWriter writer = new FileWriter(new File("./articles/index.json"));
        writer.write("[]");
        writer.close();
        listJson.clear();
        modelAndView.setViewName("redirect:/admin/dashboard");
        return modelAndView;
    }
}
