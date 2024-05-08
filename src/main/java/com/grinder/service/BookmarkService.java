package com.grinder.service;

import com.grinder.domain.dto.BookmarkDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BookmarkService {
    public Slice<BookmarkDTO.findAllResponse> findAllBookmarksSlice(String email, Pageable pageable);
    public boolean addBookmark(String email, String cafeId);
    public boolean deleteBookmark(String email, String cafeId);
    public boolean existsBookmarkByEmailAndCafeId(String email, String cafeId);
}
