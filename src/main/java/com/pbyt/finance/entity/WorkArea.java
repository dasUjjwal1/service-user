package com.pbyt.finance.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class WorkArea {
    private Set<Integer> workArea = new HashSet<>();
}
