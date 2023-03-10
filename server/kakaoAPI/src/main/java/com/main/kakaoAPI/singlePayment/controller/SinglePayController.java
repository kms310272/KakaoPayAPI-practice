package com.main.kakaoAPI.singlePayment.controller;

import com.main.kakaoAPI.exception.BusinessException;
import com.main.kakaoAPI.exception.ExceptionCode;
import com.main.kakaoAPI.singlePayment.dto.kakao.ApproveResponseDto;
import com.main.kakaoAPI.singlePayment.dto.PaymentRequestDto;
import com.main.kakaoAPI.singlePayment.dto.kakao.ReadyResponseDto;
import com.main.kakaoAPI.singlePayment.service.SinglePaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class SinglePayController {
    private final SinglePaymentService singlePaymentService;

    //결제 요청
    @PostMapping("/ready")
    public ReadyResponseDto paymentRequest(@RequestBody PaymentRequestDto paymentRequestDto) {
         return singlePaymentService.kakaoPayReady(paymentRequestDto); // "redirect: https://online-pay.kakao.com/mockup/v1/80db866d0c2bbeb45c69d1f5603feb3be67e492edfefc0018bd2991c9237d68a/info";
    }

    //http://125.176.52.40:8080/success?pg_token=2c44d553eb444534f36d
    // 결제 성공
    @GetMapping("/success")
    public void successPaymentRequest(@RequestParam("pg_token") String pgToken, HttpServletResponse response) throws IOException {
        ApproveResponseDto approve = singlePaymentService.approveResponse(pgToken);
        String redirectUri="http://125.176.52.40:8080";
        response.sendRedirect(redirectUri);
    }

    // 결제준비 취소
    @GetMapping("/cancel")
    public void cancelPaymentRequest() {
        log.info("취소해쪙");
        throw new BusinessException(ExceptionCode.PAY_CANCEL);
    }

    // 결제 실패
    @GetMapping("/fail")
    public void failPaymentRequest() {
        log.info("실패해쪙");
        throw new BusinessException(ExceptionCode.PAY_FAILED);
    }
}
