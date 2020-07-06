package thuy.codegym.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
  public class SecurityController {
    private String getPrincipal(){
        String username=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            username= ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
    @GetMapping(value = {"/","/home"})
    public String homePage(Model model){
        model.addAttribute("user",getPrincipal());
        return "welcome";
    }
    @GetMapping("/admin")
    public String adminPage(ModelMap modelMap){
        modelMap.addAttribute("user",getPrincipal());
        return "admin";

    }
    @GetMapping("/Access_Denied")
    public String AccessDeniedPage(ModelMap modelMap){
        modelMap.addAttribute("user",getPrincipal());
        return "accessdenied";
    }
    @RequestMapping(value = "/dba", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }
}