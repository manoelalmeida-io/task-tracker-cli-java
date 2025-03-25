package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;
import com.github.manoelalmeidaio.domain.Task;

public class UpdateCommand implements Command {

  private final TaskStorage taskStorage;

  public UpdateCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    if (args.length != 2) {
      System.out.println("Usage: task-cli update [id] [description]");
      return;
    }

    long id;
    String description = args[1];

    try {
      id = Long.parseLong(args[0]);
    } catch (Exception e) {
      System.out.println("Invalid task id");
      return;
    }

    Task task = this.taskStorage.findById(id);
    task.setDescription(description);
    this.taskStorage.update(task);

    System.out.printf("Task updated successfully (ID: %d)%n", task.getId());
  }
}
