package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;

import java.util.Arrays;

public class ListCommand implements Command {

  private final TaskStorage taskStorage;

  public ListCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    System.out.println("list: " + Arrays.toString(args));
  }
}
