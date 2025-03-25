package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

import java.util.Arrays;

public class UpdateCommand implements Command {

  private final TaskStorage taskStorage;

  public UpdateCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    System.out.println("update: " + Arrays.toString(args));
  }
}
