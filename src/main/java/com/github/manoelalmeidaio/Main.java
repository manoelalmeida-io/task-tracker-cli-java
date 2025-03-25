package com.github.manoelalmeidaio;

import com.github.manoelalmeidaio.command.AddCommand;
import com.github.manoelalmeidaio.command.Command;
import com.github.manoelalmeidaio.command.DeleteCommand;
import com.github.manoelalmeidaio.command.ListCommand;
import com.github.manoelalmeidaio.command.MarkDoneCommand;
import com.github.manoelalmeidaio.command.MarkInProgressCommand;
import com.github.manoelalmeidaio.command.MarkTodoCommand;
import com.github.manoelalmeidaio.command.UpdateCommand;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Usage: task-cli [command] [args...]");
      return;
    }

    TaskStorage storage = new TaskStorage();
    String commandStr = args[0];

    Command command = switch (commandStr) {
      case "add" -> new AddCommand(storage);
      case "update" -> new UpdateCommand(storage);
      case "delete" -> new DeleteCommand(storage);
      case "list" -> new ListCommand(storage);
      case "mark-todo" -> new MarkTodoCommand(storage);
      case "mark-in-progress" -> new MarkInProgressCommand(storage);
      case "mark-done" -> new MarkDoneCommand(storage);
      default -> null;
    };

    if (command != null) {
      command.run(Arrays.copyOfRange(args, 1, args.length));
    } else {
      System.out.println("Usage: task-cli [command] [args...]");
    }
  }
}