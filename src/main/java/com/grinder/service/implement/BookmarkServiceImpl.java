package com.grinder.service.implement;

import com.grinder.domain.dto.BookmarkDTO;
import com.grinder.domain.entity.Bookmark;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import com.grinder.exception.MaximumRangeAlreadyAddedException;
import com.grinder.repository.BookmarkRepository;
import com.grinder.repository.CafeRepository;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.queries.BookmarkQueryRepository;
import com.grinder.service.BookmarkService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;
    private final BookmarkQueryRepository bookmarkQueryRepository;


    public Slice<BookmarkDTO.findAllResponse> findAllBookmarksSlice(String email, Pageable pageable) {
        Slice<BookmarkDTO.findAllResponse> slice = bookmarkQueryRepository.findAllBookmarkSlice(email, pageable);
        return slice;
    }

    public boolean addBookmark(String email, String cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new EntityNotFoundException("해당 카페가 존재하지 않습니다."));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        if(bookmarkRepository.existsByMemberAndCafe(member, cafe)) throw new MaximumRangeAlreadyAddedException("이미 추가된 카페입니다.");

        bookmarkRepository.save(Bookmark.builder().member(member).cafe(cafe).build());
        return true;
    }

    public boolean deleteBookmark(String email, String cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new EntityNotFoundException("해당 카페가 존재하지 않습니다."));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        if(!bookmarkRepository.existsByMemberAndCafe(member, cafe)) throw new EntityNotFoundException("존재하지 않는 북마크 입니다.");

        Bookmark bookmark = bookmarkRepository.findByMemberAndCafe(member, cafe).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 북마크 입니다."));

        bookmarkRepository.delete(bookmark);
        return true;
    }

    public boolean existsBookmarkByEmailAndCafeId(String email, String cafeId) {
        return bookmarkQueryRepository.existsByMemberEmailAndCafeId(email, cafeId);
    }
}
