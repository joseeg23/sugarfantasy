
package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Pastel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastelRepositorio extends JpaRepository<Pastel,Long>{
    
}
