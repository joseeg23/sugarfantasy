/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Service.UsuarioService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
   
    @Autowired
    private UsuarioService usuarioS;
    
    @GetMapping("/registro")
    public String registro(){
        return "registro.html";
    }
   
    @PostMapping("/registrar")
    public String registrar(ModelMap model, @RequestParam String username, @RequestParam String nombre, @RequestParam String mail,
            @RequestParam String clave){
        
        try {
            usuarioS.alta(username, nombre, mail, clave);
            model.put("exito","usuario registrado, ya puede acceder al portal de sugar fatasy");
                return "login.html";
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            model.put("error", ex.getMessage());
            return "registro.html";
        }
    
    }
    
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo) {
        if (error != null && !error.isEmpty()) {
            modelo.put("error", "Username o clave incorrecto");
        }
        if (logout != null && !logout.isEmpty()) {
            modelo.put("logout", "Usted ha cerrado la sesion");

        }
        return "login.html";
    }
    
}

