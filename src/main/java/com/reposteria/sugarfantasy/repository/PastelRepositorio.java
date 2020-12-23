package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Pastel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PastelRepositorio extends JpaRepository<Pastel, String> {

    @Query("Select p from Pastel p where p.baja is null")
    public List<Pastel> lista();
}
