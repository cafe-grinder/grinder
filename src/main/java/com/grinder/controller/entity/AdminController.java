package com.grinder.controller.entity;

import com.grinder.domain.dto.*;
import com.grinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CafeService cafeService;
    private final CafeRegisterService cafeRegisterService;
    private final CommentService commentService;
    private final FeedService feedService;
    private final MemberService memberService;
    private final ReportService reportService;
    private final SellerApplyService sellerApplyService;
    private final SellerInfoService sellerInfoService;

    @PostMapping("/api/cafe/{registerId}")
    public ResponseEntity<SuccessResult> addCafe(@PathVariable String registerId) {
        cafeService.saveCafe(registerId);
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add new cafe", "새로운 카페정보가 등록되었습니다."));
    }

    @GetMapping("/api/cafe")
    public ResponseEntity<Slice<CafeDTO.CafeSearchByAdminDTO>> searchCafeByAdmin(@RequestParam String keyword, @PageableDefault(size = 5) Pageable pageable) {
        Slice<CafeDTO.CafeSearchByAdminDTO> cafeSlice = cafeService.searchCafeByAdmin(keyword, pageable);
        return ResponseEntity.ok(cafeSlice);
    }

    @GetMapping("/api/cafe_register")
    public ResponseEntity<Slice<CafeRegisterDTO.FindCafeRegisterDTO>> findAllCafeRegisters(@PageableDefault(size = 5) Pageable pageable) {
        Slice<CafeRegisterDTO.FindCafeRegisterDTO> cafeRegisterSlice = cafeRegisterService.FindAllCafeRegisters(pageable);
        return ResponseEntity.ok(cafeRegisterSlice);
    }

    @DeleteMapping("/api/cafe_register/{registerId}")
    public ResponseEntity<SuccessResult> denyCafeRegister(@PathVariable String registerId) {
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.ok(new SuccessResult("Delete cafe_register", "신규 장소 신청이 삭제되었습니다."));
    }

    @GetMapping("/comment/{comment_id}")
    public ResponseEntity<CommentDTO.FindCommentDTO> findCommentForAdmin(@PathVariable String comment_id) {
        CommentDTO.FindCommentDTO commentDTO = commentService.findCommentForAdmin(comment_id);
        return ResponseEntity.ok(commentDTO);
    }

    @GetMapping("/feed/{feed_id}")
    public ResponseEntity<FeedDTO.FindFeedDTO> findFeedForAdmin(@PathVariable String feed_id) {
        FeedDTO.FindFeedDTO feedDTO = feedService.findFeedForAdmin(feed_id);
        return ResponseEntity.ok(feedDTO);
    }

    @PutMapping("/api/member/{memberId}/role")
    public ResponseEntity<SuccessResult> updateMemberRole(@PathVariable String memberId) {
        memberService.updateMemberRole(memberId);
        return ResponseEntity.ok(new SuccessResult("Success", "회원 인증 상태가 변경되었습니다."));
    }

    @DeleteMapping("/api/member/{memberId}")
    public ResponseEntity<SuccessResult> deleteMember(@PathVariable String memberId) {
        if (!memberService.deleteMember(memberId)) {
            return ResponseEntity.ok(new SuccessResult("Already deleted", "이미 삭제된 회원입니다."));
        }
        return ResponseEntity.ok(new SuccessResult("Delete member", "회원이 삭제 처리되었습니다."));
    }

    @PutMapping("/api/member/{memberId}/recovery")
    public ResponseEntity<SuccessResult> recoverMember(@PathVariable String memberId) {
        if (!memberService.recoverMember(memberId)) {
            return ResponseEntity.ok(new SuccessResult("Alive member", "정상 회원입니다."));
        }
        return ResponseEntity.ok(new SuccessResult("Recover member", "회원 삭제가 취소되었습니다."));
    }

    @GetMapping("/api/member/search")
    public ResponseEntity<Slice<MemberDTO.FindMemberDTO>> searchMemberByNicknameAndRole(@RequestParam String nickname, @RequestParam String role, @PageableDefault(size = 5) Pageable pageable) {
        Slice<MemberDTO.FindMemberDTO> memberSlice = memberService.searchMemberSlice(role, nickname, pageable);
        return ResponseEntity.ok(memberSlice);
    }

    @DeleteMapping("/api/report/{reportId}")
    public ResponseEntity<SuccessResult> deleteReport(@PathVariable String reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(new SuccessResult("Success", "신고 요청이 삭제되었습니다."));
    }

    @DeleteMapping("/api/report/{reportId}/accepted")
    public ResponseEntity<SuccessResult> deleteContent(@PathVariable String reportId) {
        reportService.deleteContent(reportId);

        return ResponseEntity.ok(new SuccessResult("report_accepted", "신고된 컨텐츠가 삭제되었습니다."));
    }

    @GetMapping("/api/report/search")
    public ResponseEntity<Slice<ReportDTO.FindReportDTO>> searchReportByContentAndType(@RequestParam String keyword, @RequestParam String contentType, @PageableDefault(size = 5) Pageable pageable) {
        Slice<ReportDTO.FindReportDTO> reportList = reportService.searchReportByContentAndType(keyword, contentType, pageable);

        return ResponseEntity.ok(reportList);
    }

    @GetMapping("/api/seller_apply")
    public ResponseEntity<Slice<SellerApplyDTO.FindSellerApplyDTO>> findAllSellerApplies(@PageableDefault(size = 5)Pageable pageable) {
        Slice<SellerApplyDTO.FindSellerApplyDTO> sellerApplySlice = sellerApplyService.findAllSellerApplies(pageable);
        return ResponseEntity.ok(sellerApplySlice);
    }

    @DeleteMapping("/api/seller_apply/{applyId}")
    public ResponseEntity<SuccessResult> deleteSellerApply(@PathVariable String applyId) {
        sellerApplyService.deleteSellerApply(applyId);
        return ResponseEntity.ok(new SuccessResult("Delete seller_apply", "판매자 신청이 삭제되었습니다."));
    }

    @PostMapping("/api/seller_info/{applyId}")
    public ResponseEntity<SuccessResult> saveSellerInfo(@PathVariable String applyId) {
        sellerInfoService.saveSellerInfo(applyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new  SuccessResult("Create seller_info", "판매자 정보 등록이 완료되었습니다"));
    }
}
