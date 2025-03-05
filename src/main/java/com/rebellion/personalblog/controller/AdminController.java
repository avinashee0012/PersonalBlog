package com.rebellion.personalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String getAdminLoginPage() {
        return "adminlogin";
    }
    
    @GetMapping("dashboard")
    public String getAdminDashboardPage() {
        return "admindashboard";
    }

    @GetMapping("createArticle")
    public String getCreateArticlePage() {
        return "createarticle";
    }

    @GetMapping("editArticle")
    public String getEditArticlePage() {
        return "editarticle";
    }
    
}
