package com.github.manoelalmeidaio;

import com.github.manoelalmeidaio.domain.Task;
import com.github.manoelalmeidaio.file.TaskFile;
import com.github.manoelalmeidaio.file.TaskFileReader;
import com.github.manoelalmeidaio.file.TaskFileWriter;

public class TaskStorage {

  private final TaskFileReader fileReader;
  private final TaskFileWriter fileWriter;

  public TaskStorage() {
    this.fileReader = new TaskFileReader();
    this.fileWriter = new TaskFileWriter();
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
}
