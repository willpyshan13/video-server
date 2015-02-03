package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.utils.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: Landy
 * Date: 13-10-9
 * Time: 下午2:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {
    @RequestMapping(value = {"/login", "/", "login/index"}, method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String submit(@RequestParam("username") String username, @RequestParam("password") String password) {

        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
            UserInfo.password = password;
            UserInfo.username = username;
            return "redirect:/manager/index";
        } catch (Exception e) {
            return "redirect:/login";
        }

    }
    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return  "redirect:/login";
    }
}
