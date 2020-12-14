
package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.Entity.Foto;
import com.reposteria.sugarfantasy.Entity.Pastel;
import com.reposteria.sugarfantasy.repository.PastelRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PastelService {

    @Autowired
    private PastelRepositorio repositorio;
    @Autowired
    private FotoService fotoS;

    /**
     *
     * @param nombre del pastel
     * @param relleno sabor
     * @param bizcocho sabor
     * @param cubierta tipo
     * @param tamano cuantas personas
     * @param archivo para foto
     * @throws Exception 
     * metodo para guardar un objeto de tipo pastel
     */
    public void alta(String nombre, String relleno, String bizcocho, String cubierta, String tamano, MultipartFile archivo) throws Exception {

        try {
            verificar(nombre, relleno, bizcocho, cubierta, tamano);
            
            Pastel pastel = new Pastel(nombre, relleno, bizcocho, cubierta, tamano);
            Date alta = new Date();
            pastel.setAlta(alta);
            Foto foto = fotoS.guardar(archivo); //guardo foto y seteo en objeto
            pastel.setFoto(foto);

            repositorio.save(pastel);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
      public void modificar(Long id, String nombre, String relleno, String bizcocho, String cubierta, String tamano, MultipartFile archivo) throws Exception {

        try {
            
            verificar(nombre, relleno, bizcocho, cubierta, tamano);
            
            Pastel pastel = repositorio.getOne(id);
            pastel.setBizcocho(bizcocho);
            pastel.setCubierta(cubierta);
            pastel.setNombre(nombre);
            pastel.setRelleno(relleno);
            pastel.setTamano(tamano);
            Foto foto = fotoS.actualizar(pastel.getFoto().getId(), archivo);
            if(foto!=null){
            pastel.setFoto(foto);}

            repositorio.save(pastel);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
      
      public void baja (Long id) throws Exception{
          Optional<Pastel> buscar = repositorio.findById(id);
          
          if(buscar.isPresent()){
              Pastel pastel = buscar.get();
              Date baja = new Date();
              pastel.setBaja(baja);
              repositorio.save(pastel);
          }else{
              throw new Exception("Pastel no encontrado para dar de baja");
          }
      }

    public void verificar(String nombre, String relleno, String bizcocho, String cubierta, String tamano) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        if (relleno.isEmpty()) {
            throw new Exception("Debe indicar el sabor del relleno");
        }
        if (bizcocho.isEmpty()) {
            throw new Exception("Debe indicar el sabor del bizcocho");
        }
        if (cubierta.isEmpty()) {
            throw new Exception("Debe indicar el tipo de cubierta");
        }
        if (tamano.isEmpty()) {
            throw new Exception("Debe indicar un tamaño");
        }
    }
}
