package com.grinder.repository;

import java.util.List;
import com.grinder.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    void deleteByMenuIdAndCafe_CafeId(String menuId, String cafeId);
    List<Menu> findAllByCafe_CafeId(String cafeId);
}
