
package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,Long>{
    
}
