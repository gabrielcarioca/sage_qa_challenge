package Utility;

import java.util.Date;
import java.util.Objects;

public class Task {

    private String taskName;
    private String taskNote;
    private Date taskTimeLimit;

    public Task(String taskName, String taskNote, Date taskTimeLimit) {
        this.taskName = taskName;
        this.taskNote = taskNote;
        this.taskTimeLimit = taskTimeLimit;
    }

    public Task(){

    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public void setTaskTimeLimit(Date taskTimeLimit) {
        this.taskTimeLimit = taskTimeLimit;
    }

    public Date getTaskTimeLimit() {
        return taskTimeLimit;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskNote() {
        return taskNote;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        // field comparison
        return Objects.equals(taskNote, task.taskNote)
                && Objects.equals(taskName, task.taskName)
                && Objects.equals(taskTimeLimit, taskTimeLimit);
    }
}
