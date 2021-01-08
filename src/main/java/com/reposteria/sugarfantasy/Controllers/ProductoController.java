package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Service.ProductoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@PreAuthorize("ROLE_ADMIN")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoS;

    @GetMapping("/")
    public String portal(ModelMap modelo) {
         modelo.put("productos", productoS.lista());
        return "abmproducto.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion,
            @RequestParam String precio ,@RequestParam MultipartFile archivo) {
        try {
            productoS.alta(nombre, descripcion, precio, archivo);
            modelo.put("exito", "Producto registrado con exito");
            modelo.put("productos", productoS.lista());
            return "abmproducto.html";
        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("productos", productoS.lista());
            return "abmproducto.html";
        }

    }

    @PostMapping("/modificar")
    public String modificar(ModelMap modelo,@RequestParam String id, @RequestParam String nombre, @RequestParam String descripcion,
            @RequestParam String precio,@RequestParam MultipartFile archivo) {
        try {
            productoS.modificar(id, nombre, descripcion, precio, archivo);
            modelo.put("exitom", "Producto registrado con exito");
            modelo.put("productos", productoS.lista());
            return "abmproducto.html";
        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorm", ex.getMessage());
            return "abmproducto.html";
        }

    }
    
    @PostMapping("/baja")
    public String baja(ModelMap modelo,@RequestParam String id) {
        try {
            productoS.baja(id);
            modelo.put("exitob", "Producto dado de baja con exito");
            modelo.put("productos", productoS.lista());
            return "abmproducto.html";
        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorb", ex.getMessage());
            return "abmproducto.html";
        }

    }

}
