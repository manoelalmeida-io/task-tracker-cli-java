package com.github.manoelalmeidaio.file;

import com.github.manoelalmeidaio.domain.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TaskFileWriter {

  public void write(TaskFile file) {
    try {
      FileOutputStream outputStream = new FileOutputStream("tasks.json");
      byte[] strToBytes = toStr(file).getBytes(StandardCharsets.UTF_8);
      outputStream.write(strToBytes);
      outputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String toStr(TaskFile file) {

    StringBuilder tasks = new StringBuilder();

    for (int i = 0; i < file.getTasks().size(); i++) {
      Task task = file.getTasks().get(i);
      tasks.append("""
                {
                    "id": %d,
                    "description": "%s",
                    "status": "%s",
                    "createdAt": "%s",
                    "updatedAt": "%s"
        """.formatted(task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt()));
      if (i < file.getTasks().size() - 1) {
        tasks.append("        },\n");
      } else {
        tasks.append("        }");
      }
    }

    return """
        {
            "currentId": %d,
            "tasks": [
        %s
            ]
        }
        """.formatted(file.getCurrentId(), tasks);
  }
}
