package com.SpringWebApplication.SpringWebApplicatoin.AppController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("uname")
//@ResponseBody
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String msg(ModelMap mp){
        String name =GetLoggedInUsername();
        mp.put("uname", GetLoggedInUsername());
        return "welcome";
    }
    private String GetLoggedInUsername(){
        Authentication authentication = // Spring security inbuild interface
                SecurityContextHolder.getContext().getAuthentication();// this also inbuilt classes
        return authentication.getName();
    }
}
