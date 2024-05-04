package com.grinder.service.implement;

import com.grinder.domain.dto.HeartDTO;
import com.grinder.domain.entity.Heart;
import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.ContentType;
import com.grinder.repository.HeartRepository;
import com.grinder.service.HeartService;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService {
    private final HeartRepository heartRepository;
    private final MemberService memberService;

    @Override
    public Heart addHeart(String memberEmail, HeartDTO.HeartRequestDTO request) {
        Member member = memberService.findMemberByEmail(memberEmail);
        return heartRepository.save(
                Heart.builder()
                        .member(member)
                        .contentId(request.getContentId())
                        .contentType(ContentType.valueOf(request.getContentType()))
                        .build()
        );
    }

    @Override
    public void deleteHeart(String memberEmail, HeartDTO.HeartRequestDTO request) {
        heartRepository.deleteByMember_EmailAndContentIdAndContentType(memberEmail, request.getContentId(), ContentType.valueOf(request.getContentType()));
    }

    @Override
    public Heart findHeart(String memberEmail, HeartDTO.HeartRequestDTO request) {
        return heartRepository.findByMember_EmailAndContentIdAndContentType(memberEmail, request.getContentId(), ContentType.valueOf(request.getContentType()));
    }

    @Override
    public List<Heart> findHeartList(HeartDTO.HeartRequestDTO request) {
        return heartRepository.findByContentIdAndContentType(request.getContentId(), ContentType.valueOf(request.getContentType()));
    }
}
