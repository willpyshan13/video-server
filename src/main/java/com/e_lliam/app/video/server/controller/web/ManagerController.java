package com.e_lliam.app.video.server.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@RequestMapping(value="/index")
	public String loginSuccess(){
		return "/manager/index";
	}

	@RequestMapping("/exception")
	public void exception(){
		
	}
}
