package com.grinder.controller;


import com.grinder.domain.dto.ErrorResult;
import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.grinder.domain.dto.MemberDTO.*;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/{memberId}/role")
    @ResponseBody
    public ResponseEntity<SuccessResult> updateMemberRole(@PathVariable String memberId) {
        memberService.updateMemberRole(memberId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }

    @PutMapping("/{memberId}/isDeleted")
    @ResponseBody
    public ResponseEntity<SuccessResult> updateMemberIsDeleted(@PathVariable String memberId) {
        memberService.updateMemberIsDeleted(memberId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<FindMemberDTO>> searchMemberByNicknameAndRole(@RequestParam String nickname, @RequestParam String role, Pageable pageable) {
        List<FindMemberDTO> memberList = memberService.searchMemberSlice(role, nickname, pageable);
        return ResponseEntity.ok(memberList);
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResult> addMember(@RequestBody MemberDTO.MemberRequestDto request) {
        if (memberService.addMember(request)) return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }

    @GetMapping("/email/check")
    public ResponseEntity<SuccessResult> checkEmail(@RequestParam String email){
        System.out.println(email);
       if(memberService.checkEmail(email)) {
           return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Email is already in use","중복된 이메일입니다."));
       }
       else return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Email is available","사용가능한 이메일입니다."));
    }

    @GetMapping("/nickname/check")
    public ResponseEntity<SuccessResult> checkNickname(@RequestParam String nickname){
        if(memberService.checkNickname(nickname)) {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Nickname is already in use","중복된 닉네임입니다."));
        }
        else return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Nickname is available","사용가능한 닉네임입니다."));
    }
}
