package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    @Query("Select p from Producto p where p.baja is null")
    public List<Producto> lista();
}
