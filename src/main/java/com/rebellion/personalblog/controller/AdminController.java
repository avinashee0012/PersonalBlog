package com.rebellion.personalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rebellion.personalblog.entity.Admin;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String getAdminLoginPage() {
        // TODO: getAdminLoginPage(), adminlogin.html & verifyAdminLogin()
        // get Login Page --> on submit --> POST /verify --> redirect to appropriate page
        return "adminlogin";
    }

    @PostMapping("verify")
    public String verifyAdminLogin(@RequestBody Admin entity) {
        // verify if form input matches the harcoded credentials
            // if yes --> redirect to /dashboard
            // else --> redirect to "/admin"
        return "redirect:/dashboard";
    }
    
    @GetMapping("dashboard")
    public String getAdminDashboardPage() {
        // TODO: getAdminDashboardPage() & admindashboard.html
        // get list of all articles from JSON
        return "admindashboard";
    }

    @GetMapping("createArticle")
    public String getCreateArticlePage() {
        return "createarticle";

        // TODO: getCreateArticlePage()
        // No change needed in this method. Implement createarticle.html to post to /createArticle
    }

    @PostMapping("createArticle")
    public String saveArticleInJson() {
        // TODO: saveArticleInJson()
        // get details from form createarticle.html, save in JSON and redirect to /dashboard  
        return "redirect:/dashboard";
    }

    @GetMapping("editArticle/{id}")
    public String getEditArticlePage() {
        // TODO: getEditArticlePage()
        // use getCreateArticlePage() with a model that pre-fills the form and allows to edit the article
        return "editarticle";
    }

    @PostMapping("editArticle/{id}")
    public String postEditArticlePage() {
        // TODO: postEditArticlePage()
        return "redirect:/dashboard";
    }
}
