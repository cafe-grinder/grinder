package com.grinder.service.implement;

import com.grinder.domain.dto.MenuDTO;
import com.grinder.repository.MenuRepository;
import com.grinder.repository.SellerInfoRepository;
import com.grinder.repository.queries.MenuQueryRepository;
import com.grinder.service.MyMenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyMenuServiceImpl implements MyMenuService {

    private final MenuQueryRepository menuQueryRepository;
    private final MenuRepository menuRepository;
    private final SellerInfoRepository sellerInfoRepository;

    public List<MenuDTO.findAllMenuResponse> findAllMenuWithImage(String email, String cafeId) {
        if (!sellerInfoRepository.existsByMember_EmailAndCafe_CafeId(email, cafeId)) throw new EntityNotFoundException("관리자만 수정할 수 있습니다.");
        List<MenuDTO.findAllMenuResponse> list = menuQueryRepository.findAllMenuWithImage(cafeId);
        if (list.isEmpty()) throw new IllegalArgumentException("메뉴 정보가 없습니다");
        else return list;
    }

    @Transactional
    public boolean deleteMenu(String menuId, String cafeId) {
        menuRepository.deleteByMenuIdAndCafe_CafeId(menuId, cafeId);
        return true;
    }
}
