/**
 * //  get("/order/check")로 들어왔을 때 optionList 받아서 service로 넘길 때 사용하는 Dto
 */

package com.ohdogcat.dto.purchase;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderParameterDto {

    private List<Long> optionList;
    private Long member_fk;
}
