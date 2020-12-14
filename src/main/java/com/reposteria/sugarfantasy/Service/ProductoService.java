
package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.Entity.Foto;
import com.reposteria.sugarfantasy.Entity.Producto;
import com.reposteria.sugarfantasy.repository.ProductoRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoService {
     @Autowired
    private ProductoRepositorio repositorio;
    @Autowired
    private FotoService fotoS;
    
    public void alta(String nombre, String descripcion, MultipartFile archivo) throws Exception{
        try{
            verificar(nombre, descripcion);
            Producto producto = new Producto(nombre, descripcion);
            Date alta = new Date();
            producto.setAlta(alta);
            Foto foto= fotoS.guardar(archivo);
            producto.setFoto(foto);
            
            repositorio.save(producto);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }
    
     public void modificar(Long id, String nombre, String descripcion, MultipartFile archivo) throws Exception{
        try{
            verificar(nombre, descripcion);
            Producto producto = repositorio.getOne(id);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
          
            Foto foto= fotoS.actualizar(producto.getFoto().getId(), archivo);
            if(foto!=null){
            producto.setFoto(foto);}
            
            repositorio.save(producto);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }
     
     public void baja(Long id) throws Exception{
         Optional<Producto> buscar = repositorio.findById(id);
         
         if(buscar.isPresent()){
             Producto producto= buscar.get();
             Date baja = new Date();
             producto.setBaja(baja);
             repositorio.save(producto);
         }else{
             throw new Exception("Producto no encontrado para dar de baja");
         }
     }
     
    
    public void verificar(String nombre, String descripcion) throws Exception{
        if (nombre.isEmpty()){
            throw new Exception("Debe indicar un nombre");
            }
        if(descripcion.isEmpty()){
            throw new Exception("debe agregar una descripcion");
        }
    }
    
    
}
