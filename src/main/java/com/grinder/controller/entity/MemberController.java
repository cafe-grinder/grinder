package com.grinder.controller.entity;


import com.grinder.domain.dto.ErrorResult;
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

    @DeleteMapping("/{memberId}")
    public ResponseEntity<SuccessResult> deleteMember(@PathVariable String memberId) {
        if (!memberService.deleteMember(memberId)) {
            return ResponseEntity.ok(new SuccessResult("Already deleted", "이미 삭제된 회원입니다."));
        }
        return ResponseEntity.ok(new SuccessResult("Delete member", "회원이 삭제 처리되었습니다."));
    }

    @PutMapping("/{memberId}/recovery")
    public ResponseEntity<SuccessResult> recoverMember(@PathVariable String memberId) {
        if (!memberService.recoverMember(memberId)) {
            return ResponseEntity.ok(new SuccessResult("Alive member", "정상 회원입니다."));
        }
        return ResponseEntity.ok(new SuccessResult("Recover member", "회원 삭제가 취소되었습니다."));
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

    @PutMapping("/update")
    public ResponseEntity<SuccessResult> updateMember(@RequestBody MemberDTO.MemberUpdateRequestDto request) {
        if (memberService.updateMember(request))
            return ResponseEntity.ok(new SuccessResult("Add Success", "추가되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }

    @GetMapping("/email/check")
    public ResponseEntity<SuccessResult> checkEmail(@RequestParam String email){
        System.out.println(email);
       if(memberService.checkEmail(email)) {
           return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Email is already in use","중복된 이메일입니다."));
       }
       else return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Email is available","사용 가능한 이메일입니다."));
    }

    @GetMapping("/nickname/check")
    public ResponseEntity<SuccessResult> checkNickname(@RequestParam String nickname){
        if(memberService.checkNickname(nickname)) {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Nickname is already in use","중복된 닉네임입니다."));
        }
        else return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Nickname is available","사용 가능한 닉네임입니다."));
    }

    @PostMapping("/email/verification-requests")
    public ResponseEntity<SuccessResult> sendMessage(@RequestParam("email") String email) {
        if(memberService.sendCodeToEmail(email)) {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Request Email Verification","이메일 요청을 완료했습니다."));
        }
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }

    @GetMapping("/email/verifications")
    public ResponseEntity<?> verificationEmail(@RequestParam("email") String email,
                                            @RequestParam("code") String authCode) {
        if(memberService.verifiedCode(email, authCode)) {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Verify Email Success", "이메일 인증에 성공했습니다."));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Verify Email Failed","이메일 인증에 실패했습니다."));
    }

    @PatchMapping("/email/password")
    public ResponseEntity<SuccessResult> resetPassword(@RequestParam("email") String email ){
        if(memberService.changePassword(email)){
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Change Password Success", "비밀번호 변경에 성공했습니다."));
        }
        else throw new IllegalArgumentException("비밀번호 변경에 실패했습니다.");
    }


}

