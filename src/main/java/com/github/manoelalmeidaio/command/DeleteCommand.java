package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

public class DeleteCommand implements Command {

  private final TaskStorage taskStorage;

  public DeleteCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    if (args.length != 1) {
      System.out.println("Usage: task-cli delete [id]");
      return;
    }

    long id;

    try {
      id = Long.parseLong(args[0]);
    } catch (Exception e) {
      System.out.println("Error: invalid task id");
      return;
    }

    if (this.taskStorage.delete(id)) {
      System.out.printf("Task deleted successfully (ID: %d)%n", id);
    } else {
      System.out.printf("Error: task not found (ID: %d)%n", id);
    }
  }
}
