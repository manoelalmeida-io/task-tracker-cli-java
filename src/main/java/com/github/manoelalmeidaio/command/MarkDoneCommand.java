package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

import java.util.Arrays;

public class MarkDoneCommand implements Command {

  private final TaskStorage taskStorage;

  public MarkDoneCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    System.out.println("mark-done: " + Arrays.toString(args));
  }
}
