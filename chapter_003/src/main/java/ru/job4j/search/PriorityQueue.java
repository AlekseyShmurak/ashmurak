package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        boolean isAdded = false;
        for (Task inTasks : this.tasks) {
            if (inTasks.getPriority() > task.getPriority()) {
                this.tasks.add(tasks.indexOf(inTasks), task);
                isAdded = true;
                break;
            }
        }
        if(!isAdded) {
            this.tasks.addLast(task);
        }

    }

    public Task take() {
        return this.tasks.poll();
    }
}
