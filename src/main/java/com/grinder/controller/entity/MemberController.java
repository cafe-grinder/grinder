package com.grinder.controller.entity;


import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.grinder.domain.dto.MemberDTO.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/{memberId}/role")
    public ResponseEntity<SuccessResult> updateMemberRole(@PathVariable String memberId) {
        memberService.updateMemberRole(memberId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }

    @PutMapping("/{memberId}/isDeleted")
    public ResponseEntity<SuccessResult> updateMemberIsDeleted(@PathVariable String memberId) {
        memberService.updateMemberIsDeleted(memberId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }

    @GetMapping("/search")
    public ResponseEntity<Slice<FindMemberDTO>> searchMemberByNicknameAndRole(@RequestParam String nickname, @RequestParam String role,@PageableDefault(size = 5) Pageable pageable) {
        Slice<FindMemberDTO> memberSlice = memberService.searchMemberSlice(role, nickname, pageable);
        return ResponseEntity.ok(memberSlice);
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResult> addMember(@RequestBody MemberDTO.MemberRequestDto request) {
        if (memberService.addMember(request)) return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }
}
