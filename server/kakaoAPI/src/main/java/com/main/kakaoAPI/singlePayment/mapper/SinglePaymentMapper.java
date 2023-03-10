package com.main.kakaoAPI.singlePayment.mapper;

import com.main.kakaoAPI.singlePayment.dto.kakao.ApproveResponseDto;
import com.main.kakaoAPI.singlePayment.entity.SinglePayment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SinglePaymentMapper {
    SinglePayment approveResponseDtoToSinglePayment(ApproveResponseDto approveResponseDto);
}
