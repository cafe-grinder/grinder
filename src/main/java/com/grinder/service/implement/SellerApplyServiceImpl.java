package com.grinder.service.implement;

import com.grinder.domain.dto.SellerApplyDTO;
import com.grinder.domain.entity.SellerApply;
import com.grinder.repository.SellerApplyRepository;
import com.grinder.service.SellerApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.grinder.domain.dto.SellerApplyDTO.*;

@Service
@RequiredArgsConstructor
public class SellerApplyServiceImpl implements SellerApplyService {

    private final SellerApplyRepository sellerApplyRepository;

    @Override
    public List<SellerApplyDTO.FindSellerApplyDTO> findAllSellerApplies() {
        List<SellerApply> sellerApplyList = sellerApplyRepository.findAll();

        List<FindSellerApplyDTO> sellerApplyDTOList = sellerApplyList.stream().map(sellerApply -> new FindSellerApplyDTO(sellerApply)).toList();

        return sellerApplyDTOList;
    }

    @Override
    public void deleteSellerApply(String applyId) {
        sellerApplyRepository.deleteById(applyId);
    }
}
