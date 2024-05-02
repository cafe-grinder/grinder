package com.grinder.service.implement;

import com.grinder.domain.entity.Heart;
import com.grinder.domain.enums.ContentType;
import com.grinder.repository.HeartRepository;
import com.grinder.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService {
    private final HeartRepository heartRepository;

    @Override
    public void addLike(Heart like) {
        heartRepository.save(like);
    }

    @Override
    public void deleteLike(String memberId, String contentId, String contentType) {
        heartRepository.deleteByMember_MemberIdAndContentIdAndContentType(memberId, contentId, ContentType.valueOf(contentType));
    }

    @Override
    public Heart findLike(String memberId, String contentId, String contentType) {
        return heartRepository.findByMember_MemberIdAndContentIdAndContentType(memberId, contentId, ContentType.valueOf(contentType));
    }

    @Override
    public List<Heart> findByContent(String contentId, String contentType) {
        return heartRepository.findByContentIdAndContentType(contentId, ContentType.valueOf(contentType));
    }
}
