package com.ohdogcat.dto.purchase;

import com.ohdogcat.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPointDto {

    private Long member_pk;
    private Long used_point;
    private Long accumulated_point;

    public static MemberPointDto toMemberPointDto(Payment payment, Integer point_rate,
        Long member_pk) {

        return MemberPointDto.builder().used_point(payment.getUsed_point())
            .accumulated_point(calculatePointToAccumulate(payment, point_rate))
            .member_pk(member_pk).build();
    }

    private static Long calculatePointToAccumulate(Payment payment, Integer point_rate) {
        return Math.round((payment.getTotal_price()) * (point_rate / 100.0));
    }
}
