package com.ohdogcat.dto.option;

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
