package com.grinder.service.implement;

import com.grinder.domain.entity.Menu;
import com.grinder.repository.MenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class menuServiceImpl {
    private final MenuRepository menuRepository;
    public List<Menu> findAllMenusByCafeId(String cafeId) {
        return menuRepository.findAllByCafe_CafeId(cafeId);
    }
}
