
package com.reposteria.sugarfantasy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalController {
    
    public String index(){
        return "index.html";
    }
}
