package com.bubak.put.ptsz.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Instance {
    private List<Task> tasks;

    public Instance() {
        this.tasks = new ArrayList<>();
    }

    public int tasksQuantity() {
        return this.tasks.size();
    }
}
