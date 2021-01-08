package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.Entity.Foto;
import com.reposteria.sugarfantasy.Entity.Pastel;
import com.reposteria.sugarfantasy.repository.PastelRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
     * @param preciop
     * @param preciom
     * @param archivo para foto
     * @throws Exception metodo para guardar un objeto de tipo pastel
     */
    @Transactional
    public void alta(String nombre, String relleno, String bizcocho, String cubierta, String tamano, String preciop, String preciom, MultipartFile archivo) throws Exception {

        try {
            verificar(nombre, relleno, bizcocho, cubierta, tamano);

            Pastel pastel = new Pastel(nombre, relleno, bizcocho, cubierta, tamano);
            int p = Integer.parseInt(preciop);
            int p2 = Integer.parseInt(preciom);
            pastel.setPrecioP(p);
            pastel.setPrecioM(p2);
            Date alta = new Date();
            pastel.setAlta(alta);
            Foto foto = fotoS.guardar(archivo); //guardo foto y seteo en objeto
            pastel.setFoto(foto);

            repositorio.save(pastel);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void modificar(String id, String nombre, String relleno, String bizcocho, String cubierta, String tamano, String preciop, String preciom, MultipartFile archivo) throws Exception {

        try {

            verificar(nombre, relleno, bizcocho, cubierta, tamano);

            Pastel pastel = repositorio.getOne(id);
            pastel.setBizcocho(bizcocho);
            pastel.setCubierta(cubierta);
            pastel.setNombre(nombre);
            pastel.setRelleno(relleno);
            pastel.setTamano(tamano);

            int p = Integer.parseInt(preciop);
            int p2 = Integer.parseInt(preciom);
            pastel.setPrecioP(p);
            pastel.setPrecioM(p2);
            
            Foto foto = fotoS.actualizar(pastel.getFoto().getId(), archivo);
            if (foto != null) {
                pastel.setFoto(foto);
            }

            repositorio.save(pastel);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void baja(String id) throws Exception {
        Optional<Pastel> buscar = repositorio.findById(id);

        if (buscar.isPresent()) {
            Pastel pastel = buscar.get();
            Date baja = new Date();
            pastel.setBaja(baja);
            repositorio.save(pastel);
        } else {
            throw new Exception("Pastel no encontrado para dar de baja");
        }
    }
    
     @Transactional
    public Pastel buscarPorId(String id) throws Exception {
        Optional<Pastel> buscar = repositorio.findById(id);

        if (buscar.isPresent()) {
            Pastel pastel = buscar.get();
           return pastel;
        } else {
            throw new Exception("Pastel no encontrado");
        }
    }

    public List<Pastel> lista() {
        return repositorio.lista();
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
