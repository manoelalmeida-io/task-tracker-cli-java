package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;
import com.github.manoelalmeidaio.domain.Task;

public class AddCommand implements Command {

  private final TaskStorage taskStorage;

  public AddCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    if (args.length != 1) {
      System.out.println("Usage: task-cli add [description]");
      return;
    }

    Task task = new Task(args[0]);
    this.taskStorage.add(task);
    System.out.printf("Task added successfully (ID: %d)%n", task.getId());
  }
}
