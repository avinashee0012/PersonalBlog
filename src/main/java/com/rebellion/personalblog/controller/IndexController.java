package com.rebellion.personalblog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebellion.personalblog.entity.Article;
import com.rebellion.personalblog.entity.Index;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    private ObjectMapper mapper;
    
    public IndexController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("")
    public ModelAndView getHomePage(ModelAndView modelAndView) throws StreamReadException, DatabindException, IOException {
        List<Index> list = mapper.readValue(new File("./articles/index.json"), new TypeReference<List<Index>>(){});
        modelAndView.addObject("list", list);
        modelAndView.setViewName("homepage");
        return modelAndView;
    }

    // TODO: Content should be HTML formatted.
    @GetMapping("article/{id}")
    public ModelAndView getViewArticlePage(@PathVariable int id, ModelAndView modelAndView) throws IOException {
        try {
            Article article = mapper.readValue(new File("./articles/article" + id + ".json"), Article.class);
            modelAndView.addObject("id", article.getId());
            modelAndView.addObject("title", article.getTitle());
            modelAndView.addObject("publishdate", article.getPublishdate());
            modelAndView.addObject("content", article.getContent());
        } catch (Exception e) {
            modelAndView.addObject("status", String.format("File not found with ID: %d!", id));
        }
        modelAndView.setViewName("articleview");
        return modelAndView;
    }

    @GetMapping("logout")
    public String logoutAdmin(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
}
