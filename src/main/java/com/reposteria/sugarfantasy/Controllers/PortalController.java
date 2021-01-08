package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Service.PastelService;
import com.reposteria.sugarfantasy.Service.PostreService;
import com.reposteria.sugarfantasy.Service.ProductoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    private PastelService pastelS;
    @Autowired
    private PostreService postreS;
       @Autowired
    private ProductoService productoS;

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
    public String productos(ModelMap modelo) {
          modelo.put("productos", productoS.lista());
        return "productos.html";
    }
    
     @GetMapping("/detallepastel/{id}")
    public String detallePAstel(ModelMap modelo, @PathVariable String id) {
        try {
            modelo.put("pastel", pastelS.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "redirect:/tortas";
        }
        return "detallepastel.html";
    }
    
      @GetMapping("/detallepostre/{id}")
    public String detallePostre(ModelMap modelo, @PathVariable String id) {
        try {
            modelo.put("postre", postreS.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "redirect:/postres";
        }
        return "detallepp.html";
    }
      @GetMapping("/detalleproducto/{id}")
    public String detalleProducto(ModelMap modelo, @PathVariable String id) {
        try {
            modelo.put("producto", productoS.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "redirect:/productos";
        }
        return "detallepp.html";
    }

}
