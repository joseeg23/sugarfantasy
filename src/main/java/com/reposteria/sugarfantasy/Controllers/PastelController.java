package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Service.PastelService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pastel")
public class PastelController {

    @Autowired
    private PastelService pastelS;

    @GetMapping("/")
    public String portal(ModelMap modelo) {
        modelo.put("pasteles", pastelS.lista());
        return "abmpastel.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String relleno,
            @RequestParam String bizcocho, @RequestParam String cubierta, @RequestParam String tamano,
            @RequestParam MultipartFile archivo) {
        try {
            pastelS.alta(nombre, relleno, bizcocho, cubierta, tamano, archivo);
             modelo.put("exito", "Pastel registrado con exito");
             modelo.put("pasteles", pastelS.lista());
             return "abmpastel.html";
        } catch (Exception ex) {
            Logger.getLogger(PastelController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("pasteles", pastelS.lista());
            modelo.put("error", ex.getMessage());
             return "abmpastel.html";

        }
    }

    @PostMapping("/modificar")
    public String modificar(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam String relleno,
            @RequestParam String bizcocho, @RequestParam String cubierta, @RequestParam String tamano,
            @RequestParam MultipartFile archivo) {
        try {
            pastelS.modificar(id, nombre, relleno, bizcocho, cubierta, tamano, archivo);
            modelo.put("exitom", "Pastel modificado con exito");
            modelo.put("pasteles", pastelS.lista());
             return "abmpastel.html";
        } catch (Exception ex) {
            Logger.getLogger(PastelController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("pasteles", pastelS.lista());
            modelo.put("errorm", ex.getMessage());
             return "abmpastel.html";

        }
    }
    @PostMapping("/baja")
    public String baja(ModelMap modelo, @RequestParam String id) {
        try {
            pastelS.baja(id);
            modelo.put("exitob", "Pastel dado de baja con exito");
            modelo.put("pasteles", pastelS.lista());
             return "abmpastel.html";
        } catch (Exception ex) {
            Logger.getLogger(PastelController.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorb", ex.getMessage());
            modelo.put("pasteles", pastelS.lista()); 
             return "abmpastel.html";

        }
    }
}
