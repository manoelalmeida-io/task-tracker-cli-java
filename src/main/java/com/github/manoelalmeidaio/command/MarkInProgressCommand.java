package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

import java.util.Arrays;

public class MarkInProgressCommand implements Command {

  private final TaskStorage taskStorage;

  public MarkInProgressCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    System.out.println("mark-in-progress: " + Arrays.toString(args));
  }
}
