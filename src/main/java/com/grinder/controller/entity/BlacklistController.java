package com.grinder.controller.entity;

import com.grinder.domain.dto.BlacklistDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlacklistController {

    private final BlacklistService blacklistService;

    @GetMapping("/blacklist")
    public ResponseEntity<List<BlacklistDTO.findAllResponse>> findAllBlacklist() {
        String email = getEmail();
        List<BlacklistDTO.findAllResponse> list = blacklistService.findAllBlacklist(email);
        return ResponseEntity.ok().body(list);
    }
    @PostMapping("/blacklist/{email}")
    public ResponseEntity<SuccessResult> addBlacklist(@PathVariable("email")String black) {
        String email = getEmail();
        BlacklistDTO.AddRequest request = new BlacklistDTO.AddRequest();
        request.setBlockedMemberEmail(black);
        if (blacklistService.addBlacklist(request, email)) return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }
    @DeleteMapping("/blacklist/{id}")
    public ResponseEntity<SuccessResult> deleteBlacklist(@PathVariable("id")Long blacklistId) {
        String email = getEmail();
        if (blacklistService.deleteBlacklist(blacklistId, email)) return ResponseEntity.ok().body(new SuccessResult("Delete Success", "삭제되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
