package com.grinder.controller;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/find")
    public void findAllMembers(Model model) {
        List<MemberDTO.FindMemberDTO> memberList = memberService.findAllMembers();
        model.addAttribute("memberList", memberList);
    }

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

    @GetMapping("/search/nickname")
    public void searchMemberByNickname(@RequestParam String nickname, Model model) {
        List<MemberDTO.FindMemberDTO> memberList = memberService.searchMemberByNickname(nickname);
        model.addAttribute("memberList", memberList);
    }
}
