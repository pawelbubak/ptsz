package com.bubak.put.ptsz.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private String owner;
    private List<Statistic> statistics = new ArrayList<>();

    public Statistics(String owner) {
        this.owner = owner;
    }

    public void addStatistic(Statistic statistic) {
        this.statistics.add(statistic);
    }
}
