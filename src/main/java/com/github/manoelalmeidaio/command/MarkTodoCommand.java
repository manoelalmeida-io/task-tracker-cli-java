package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;
import com.github.manoelalmeidaio.domain.Task;
import com.github.manoelalmeidaio.domain.TaskStatus;

public class MarkTodoCommand implements Command {

  private final TaskStorage taskStorage;

  public MarkTodoCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    if (args.length != 1) {
      System.out.println("Usage: task-cli mark-todo [id]");
      return;
    }

    long id;

    try {
      id = Long.parseLong(args[0]);
    } catch (Exception e) {
      System.out.println("Error: invalid task id");
      return;
    }

    Task task = this.taskStorage.findById(id);
    task.setStatus(TaskStatus.TODO);
    this.taskStorage.update(task);

    System.out.printf("Task done (ID: %d)%n", task.getId());
  }
}
