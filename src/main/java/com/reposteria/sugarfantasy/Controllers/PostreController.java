package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Service.PostreService;
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
@RequestMapping("/postre")
public class PostreController {

    @Autowired
    private PostreService postreS;

    @GetMapping("/")
    public String portal(ModelMap modelo) {
        modelo.put("postres", postreS.lista());
        return "abmpostre.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion,
           @RequestParam String precio, @RequestParam MultipartFile archivo) {

        try {
            postreS.alta(nombre, descripcion,precio, archivo);
            modelo.put("exito", "postre registrado con exito");
             modelo.put("postres", postreS.lista());
            return "abmpostre.html";
        } catch (Exception ex) {
            Logger.getLogger(PostreController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
             modelo.put("postres", postreS.lista());
            return "abmpostre.html";
        }

    }

    @PostMapping("/modificar")
    public String modificar(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam String descripcion,
           @RequestParam String precio, @RequestParam MultipartFile archivo) {

        try {
            postreS.modificar(id, nombre, descripcion, precio, archivo);
            modelo.put("exitom", "postre modificado con exito");
             modelo.put("postres", postreS.lista());
            return "abmpostre.html";
        } catch (Exception ex) {
            Logger.getLogger(PostreController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorm", ex.getMessage());
             modelo.put("postres", postreS.lista());
            return "abmpostre.html";
        }

    }

    @PostMapping("/baja")
    public String baja(ModelMap modelo, @RequestParam String id) {

        try {
            postreS.baja(id);
            modelo.put("exitob", "postre dado de baja con exito");
             modelo.put("postres", postreS.lista());
            return "abmpostre.html";
        } catch (Exception ex) {
            Logger.getLogger(PostreController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorb", ex.getMessage());
             modelo.put("postres", postreS.lista());
            return "abmpostre.html";
        }

    }

}
