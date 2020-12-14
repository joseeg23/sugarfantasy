package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.repository.FotoRepositorio;
import com.reposteria.sugarfantasy.Entity.Foto;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardar(MultipartFile archivo) throws Exception {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                fotoRepositorio.save(foto);
                return foto;

            } catch (Exception e) {
                throw new Exception(e.getMessage());

            }

        }

        return null;
    }

    @Transactional
    public Foto actualizar(Long idFoto, MultipartFile archivo) throws Exception {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }

                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                fotoRepositorio.save(foto);
                return foto;

            } catch (Exception e) {
                throw new Exception(e.getMessage());

            }
        }

        return null;
    }

    public Foto buscarPorId(Long id) {
        Optional<Foto> buscar = fotoRepositorio.findById(id);
        if (buscar.isPresent()) {
            Foto foto = buscar.get();
            return foto;
        } else {
            return null;
        }
    }

}
