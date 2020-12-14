
package com.reposteria.sugarfantasy.repository;

import com.reposteria.sugarfantasy.Entity.Postre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostreRepositorio extends JpaRepository<Postre,Long>{
    
}
