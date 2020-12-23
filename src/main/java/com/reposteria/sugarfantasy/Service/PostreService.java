package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.Entity.Foto;
import com.reposteria.sugarfantasy.Entity.Postre;
import com.reposteria.sugarfantasy.repository.PostreRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostreService {

    @Autowired
    private PostreRepositorio repositorio;
    @Autowired
    private FotoService fotoS;

    @Transactional
    public void alta(String nombre, String descripcion, MultipartFile archivo) throws Exception {
        try {
            verificar(nombre, descripcion);
            Postre postre = new Postre(nombre, descripcion);
            Date alta = new Date();
            postre.setAlta(alta);
            Foto foto = fotoS.guardar(archivo);
            postre.setFoto(foto);

            repositorio.save(postre);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Transactional
    public void modificar(String id, String nombre, String descripcion, MultipartFile archivo) throws Exception {
        try {
            verificar(nombre, descripcion);
            Postre postre = repositorio.getOne(Long.parseLong(id));
            postre.setNombre(nombre);
            postre.setDescripcion(descripcion);

            Foto foto = fotoS.actualizar(postre.getFoto().getId(), archivo);
            if (foto != null) {
                postre.setFoto(foto);
            }

            repositorio.save(postre);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Transactional
    public void baja(String id) throws Exception {
        Optional<Postre> buscar = repositorio.findById(Long.parseLong(id));

        if (buscar.isPresent()) {
            Postre postre = buscar.get();
            Date baja = new Date();
            postre.setBaja(baja);
            repositorio.save(postre);
        } else {
            throw new Exception("Postre no encontrado para dar de baja");
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
