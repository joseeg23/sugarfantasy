package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Service.PastelService;
import com.reposteria.sugarfantasy.Service.PostreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    private PastelService pastelS;
    @Autowired
    private PostreService postreS;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/tortas")
    public String tortas(ModelMap modelo) {
        modelo.put("pasteles", pastelS.lista());
        return "tortas.html";
    }

    @GetMapping("/postres")
    public String postres(ModelMap modelo) {
        modelo.put("postres", postreS.lista());
        return "postres.html";
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos.html";
    }

}
