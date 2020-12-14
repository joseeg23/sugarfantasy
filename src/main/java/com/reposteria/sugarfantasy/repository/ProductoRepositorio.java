
package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepositorio extends JpaRepository<Producto,Long>{
    
}
