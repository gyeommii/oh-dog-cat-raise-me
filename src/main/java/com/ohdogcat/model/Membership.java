package com.ohdogcat.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Membership {
    private Long membership_pk;
    private String grade;
    private Long min_stack;
    private Long max_stack;
    private Integer point_rate;
}
