package com.bubak.put.ptsz.core.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Task {
    private int id;
    @NonNull
    private int processingTime;
    @NonNull
    private int readyTime;
    @NonNull
    private int dueDate;

    public Task(Task task) {
        this.id = task.getId();
        this.processingTime = task.getProcessingTime();
        this.readyTime = task.getReadyTime();
        this.dueDate = task.getDueDate();
    }
}
