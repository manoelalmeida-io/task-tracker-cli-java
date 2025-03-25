package com.github.manoelalmeidaio.command;

import com.github.manoelalmeidaio.TaskStorage;
import com.github.manoelalmeidaio.domain.Task;
import com.github.manoelalmeidaio.domain.TaskStatus;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListCommand implements Command {

  private final TaskStorage taskStorage;

  public ListCommand(TaskStorage taskStorage) {
    this.taskStorage = taskStorage;
  }

  @Override
  public void run(String... args) {
    List<Task> tasks = this.taskStorage.list();

    if (args.length > 0) {
      TaskStatus status = switch (args[0]) {
        case "todo" -> TaskStatus.TODO;
        case "in-progress" -> TaskStatus.IN_PROGRESS;
        case "done" -> TaskStatus.DONE;
        default -> null;
      };

      if (status == null) {
        System.out.println("Usage: task-cli list [todo|in-progress|done]");
        return;
      }

      tasks = tasks.stream()
          .filter(task -> task.getStatus().equals(status))
          .toList();
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    StringBuilder message = new StringBuilder();
    message.append("""
        |%3.3s| %-50.50s| %-11s | %-16s | %-16s |
        +---+---------------------------------------------------+-------------+------------------+------------------+
        """.formatted("ID", "DESCRIPTION", "STATUS", "CREATED_AT", "UPDATED_AT"));
    for (Task task : tasks) {
      message.append("""
        |%3d| %-50.50s| %-11s | %-16s | %-16s |
        """.formatted(task.getId(), task.getDescription(), task.getStatus(),
          formatter.format(task.getCreatedAt()), formatter.format(task.getUpdatedAt())));
    }

    System.out.println(message);
  }
}
