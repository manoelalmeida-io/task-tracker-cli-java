package com.github.manoelalmeidaio;

import com.github.manoelalmeidaio.domain.Task;
import com.github.manoelalmeidaio.file.TaskFile;
import com.github.manoelalmeidaio.file.TaskFileReader;
import com.github.manoelalmeidaio.file.TaskFileWriter;

import java.time.LocalDateTime;
import java.util.List;

public class TaskStorage {

  private final TaskFileReader fileReader;
  private final TaskFileWriter fileWriter;

  public TaskStorage() {
    this.fileReader = new TaskFileReader();
    this.fileWriter = new TaskFileWriter();
  }

  public List<Task> list() {
    TaskFile file = this.fileReader.read();
    return file.getTasks();
  }

  public Task findById(Long id) {
    TaskFile file = this.fileReader.read();
    return file.getTasks().stream()
        .filter(task -> task.getId().equals(id))
        .findFirst().orElseThrow(() -> new RuntimeException("Task not found"));
  }

  public Task add(Task task) {
    TaskFile file = this.fileReader.read();
    Long newId = file.getCurrentId() + 1;

    task.setId(newId);
    file.setCurrentId(newId);
    file.getTasks().add(task);
    this.fileWriter.write(file);
    return task;
  }

  public Task update(Task task) {
    TaskFile file = this.fileReader.read();
    for (Task fileTask : file.getTasks()) {
      if (fileTask.getId().equals(task.getId())) {
        fileTask.setDescription(task.getDescription());
        fileTask.setStatus(task.getStatus());
        fileTask.setUpdatedAt(LocalDateTime.now());
      }
    }
    this.fileWriter.write(file);
    return task;
  }

  public boolean delete(Long id) {
    TaskFile file = this.fileReader.read();
    boolean deleted = file.getTasks().removeIf(item -> item.getId().equals(id));

    if (deleted) {
      this.fileWriter.write(file);
    }

    return deleted;
  }
}
