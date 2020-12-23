package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Postre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostreRepositorio extends JpaRepository<Postre, Long> {

    @Query("Select p from Postre p where p.baja is null")
    public List<Postre> lista();
}
