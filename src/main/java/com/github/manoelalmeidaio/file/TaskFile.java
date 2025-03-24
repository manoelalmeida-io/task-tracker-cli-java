package com.github.manoelalmeidaio.file;

import com.github.manoelalmeidaio.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskFile {

  private Long currentId = 0L;
  private List<Task> tasks = new ArrayList<>();

  public Long getCurrentId() {
    return currentId;
  }

  public void setCurrentId(Long currentId) {
    this.currentId = currentId;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  @Override
  public String toString() {
    return "TaskFile{" +
        "currentId=" + currentId +
        ", tasks=" + tasks +
        '}';
  }
}
