package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.Entity.Foto;
import com.reposteria.sugarfantasy.Entity.Producto;
import com.reposteria.sugarfantasy.repository.ProductoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepositorio repositorio;
    @Autowired
    private FotoService fotoS;
    
    @Transactional
    public void alta(String nombre, String descripcion, String precio, MultipartFile archivo) throws Exception {
        try {
            verificar(nombre, descripcion);
            int p = Integer.parseInt(precio);
            Producto producto = new Producto(nombre, descripcion, p);
            Date alta = new Date();
            producto.setAlta(alta);
            Foto foto = fotoS.guardar(archivo);
            producto.setFoto(foto);
            
            repositorio.save(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    @Transactional
    public void modificar(String id, String nombre, String descripcion, String precio, MultipartFile archivo) throws Exception {
        try {
            verificar(nombre, descripcion);
            Producto producto = repositorio.getOne(Long.parseLong(id));
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            int p = Integer.parseInt(precio);
            producto.setPrecio(p);
            Foto foto = fotoS.actualizar(producto.getFoto().getId(), archivo);
            if (foto != null) {
                producto.setFoto(foto);
            }
            
            repositorio.save(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    @Transactional
    public void baja(String id) throws Exception {
        Optional<Producto> buscar = repositorio.findById(Long.parseLong(id));
        
        if (buscar.isPresent()) {
            Producto producto = buscar.get();
            Date baja = new Date();
            producto.setBaja(baja);
            repositorio.save(producto);
        } else {
            throw new Exception("Producto no encontrado para dar de baja");
        }
    }
    
     @Transactional
    public Producto buscarPorId(String id) throws Exception {
        Optional<Producto> buscar = repositorio.findById(Long.parseLong(id));
        
        if (buscar.isPresent()) {
            Producto producto = buscar.get();
            return producto;
        } else {
            throw new Exception("Producto no encontrado");
        }
    }
    
    public List lista() {
        return repositorio.lista();
    }
    
    public void verificar(String nombre, String descripcion) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        if (descripcion.isEmpty()) {
            throw new Exception("debe agregar una descripcion");
        }
    }
    
}
