package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

import java.util.Arrays;

public class DeleteCommand implements Command {

  private final TaskStorage taskStorage;

  public DeleteCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    System.out.println("delete: " + Arrays.toString(args));
  }
}
