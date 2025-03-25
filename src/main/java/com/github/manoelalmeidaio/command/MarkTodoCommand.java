package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

import java.util.Arrays;

public class MarkTodoCommand implements Command {

  private final TaskStorage taskStorage;

  public MarkTodoCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    System.out.println("mark-todo: " + Arrays.toString(args));
  }
}
