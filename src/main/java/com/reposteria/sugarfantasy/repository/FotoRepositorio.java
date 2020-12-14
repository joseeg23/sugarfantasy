
package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto,Long> {
    
}
