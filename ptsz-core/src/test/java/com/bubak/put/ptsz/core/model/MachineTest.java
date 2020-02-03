package com.bubak.put.ptsz.core.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    @Test
    void shouldAddTask() {
        // Given
        Machine machine = new Machine();
        Task task = new Task(5, 1, 10);
        // When
        machine.addTask(task);
        // Then
        assertAll(() -> assertEquals(1, machine.getTasksQuantity()),
                () -> assertEquals(6, machine.getLastEndTime()));
    }

    @Test
    void shouldNotAddTask() {
        // Given
        Machine machine = new Machine();
        // When
        machine.addTask(null);
        // Then
        assertAll(() -> assertEquals(0, machine.getTasksQuantity()),
                () -> assertEquals(0, machine.getLastEndTime()));
    }

    @Test
    void endTimeShouldEqualsZero() {
        // Given
        Machine machine = new Machine();
        // When
        machine.calculateEndTime();
        // Then
        assertEquals(0, machine.getLastEndTime());
    }

    @Test
    void endTimeShouldNotEqualsSix() {
        // Given
        Machine machine = new Machine();
        machine.setTasks(Collections.singletonList(new Task(5, 1, 10)));
        // When
        machine.calculateEndTime();
        // Then
        assertEquals(6, machine.getLastEndTime());
    }

    @Test
    void shouldReturnTask() {
        // Given
        Machine machine = new Machine();
        Task expectedTask = new Task(5, 1, 10);
        machine.addTask(expectedTask);
        // When
        Task task = machine.getTask(0);
        // Then
        assertEquals(expectedTask, task);
    }

    @Test
    void shouldReturnNull() {
        // Given
        Machine machine = new Machine();
        // When
        Task task = machine.getTask(0);
        // Then
        assertNull(task);
    }

    @Test
    void shouldReturnTasksQuantity() {
        // Given
        Machine machine = new Machine();
        machine.addTask(new Task(5, 1, 10));
        machine.addTask(new Task(5, 1, 10));
        // When
        int tasksQuantity = machine.getTasksQuantity();
        // Then
        assertEquals(2, tasksQuantity);
    }

    @Test
    void shouldReturnTasksSchedulingDelay() {
        // Given
        Machine machine = new Machine();
        machine.addTask(new Task(5, 1, 10));
        machine.addTask(new Task(5, 1, 10));
        // When
        long delay = machine.getDelay();
        // Then
        assertEquals(1, delay);
    }
}
