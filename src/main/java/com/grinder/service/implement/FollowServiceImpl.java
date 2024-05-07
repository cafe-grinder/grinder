package com.grinder.service.implement;

import com.grinder.domain.dto.FollowDTO;
import com.grinder.domain.entity.Follow;
import com.grinder.domain.entity.Member;
import com.grinder.repository.FollowRepository;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.queries.FollowQueryRepository;
import com.grinder.service.FollowService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final FollowQueryRepository followQueryRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<FollowDTO.findAllFollowingResponse> findAllFollowingSlice(String email, Pageable pageable) {
        Slice<FollowDTO.findAllFollowingResponse> slice = followQueryRepository.findAllFollowingSlice(email, pageable);
        return slice.getContent();
    }
    @Override
    public List<FollowDTO.findAllFollowerResponse> findAllFollowerSlice(String email, Pageable pageable) {
        Slice<FollowDTO.findAllFollowerResponse> slice = followQueryRepository.findAllFollowerSlice(email, pageable);
        return slice.getContent();
    }
    @Override
    public boolean addFollow(String email, String followEmail) {
        if (email.equals(followEmail)) throw new IllegalArgumentException("자기 자신은 추가할 수 없습니다.");
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        Member following = memberRepository.findByEmail(followEmail).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        if (followQueryRepository.existsByMemberEmailAndFollowEmail(email, followEmail)) {
            throw new IllegalArgumentException("이미 존재하는 팔로우 입니다.");
        }
        Follow follow = Follow.builder().member(member).following(following).build();
        followRepository.save(follow);
        return true;
    }
    @Override
    public boolean deleteFollow(String email, String followEmail) {
        if (!followQueryRepository.existsByMemberEmailAndFollowEmail(email, followEmail)) {
            throw new IllegalArgumentException("존재하지 않는 팔로우 입니다.");
        }
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        Member following = memberRepository.findByEmail(followEmail).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Follow follow = followRepository.findByMemberAndFollowing(member,following).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        followRepository.delete(follow);
        return true;
    }
}
