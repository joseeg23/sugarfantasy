
package com.reposteria.sugarfantasy.Controllers;

import com.reposteria.sugarfantasy.Entity.Foto;
import com.reposteria.sugarfantasy.Service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoController {
    
    @Autowired
    private FotoService fotoService;


   @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable Long id) {
        Foto foto = fotoService.buscarPorId(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
    }
}
