package com.frame.appServer.requestMethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("appServer/product")
public class TestController {
	  @RequestMapping(value = "/test")
	   @ResponseBody
	    public String getFile() {
		JSONObject json = new JSONObject();
		String[] array = { "1", "2", "3", "4", "5", "6", "7" };
		for (String s : array) {
		    json.put(s, 11);
		}
		return json.toString();
	    }
}
