package com.grinder.service.implement;

import com.grinder.domain.dto.MenuDTO;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Menu;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.MenuType;
import com.grinder.repository.CafeRepository;
import com.grinder.repository.MenuRepository;
import java.util.List;
import java.util.UUID;

import com.grinder.repository.queries.MenuQueryRepository;
import com.grinder.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final AwsS3ServiceImpl s3Service;
    private final CafeRepository cafeRepository;

    @Override
    public List<Menu> findAllMenusByCafeId(String cafeId) {
        return menuRepository.findAllByCafe_CafeId(cafeId);
    }
    @Override
    @Transactional
    public boolean saveMyCafeMenu(MenuDTO.saveMenuRequest request) {
        String uuid = UUID.randomUUID().toString();

        if (request.getMenuImage() != null) {
            s3Service.uploadSingleImageBucket(request.getMenuImage(), uuid, ContentType.MENU);
        }
        Cafe cafe = cafeRepository.findById(request.getCafeId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 카페 입니다."));

        boolean limit = false;
        switch (request.getMenuIsLimited()) {
            case "한정 메뉴" :
                limit = true; break;
            case "상시" :
                break;
            default :
                throw new IllegalArgumentException("한정 메뉴 관련 잘못된 정보입니다.");
        }
        menuRepository.save(Menu.builder()
                .cafe(cafe)
                .menuId(uuid)
                .name(request.getMenuName())
                .menuType(MenuType.fromString(request.getMenuType()))
                .price(request.getMenuPrice())
                .volume(request.getMenuVolume())
                .allergy(request.getMenuAllergy())
                .details(request.getMenuDetails())
                .isLimited(limit).build());
        return true;
    }
}
