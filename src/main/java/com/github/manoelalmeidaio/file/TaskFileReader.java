package com.github.manoelalmeidaio.file;

import com.github.manoelalmeidaio.domain.Task;
import com.github.manoelalmeidaio.domain.TaskStatus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TaskFileReader {

  public TaskFile read() {
    try {
      InputStreamReader isr = new InputStreamReader(new FileInputStream("tasks.json"));
      BufferedReader reader = new BufferedReader(isr);

      StringBuilder json = new StringBuilder();

      int c;
      while ((c = reader.read()) != -1) {
        json.append((char) c);
      }

      return fromString(json.toString());
    } catch (FileNotFoundException e) {
      return new TaskFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private TaskFile fromString(String json) {
    TaskFile taskFile = new TaskFile();

    Map<?, ?> result = mapString(json);
    taskFile.setCurrentId(Long.parseLong((String) result.get("currentId")));

    List<?> tasksMap = (List<?>) result.get("tasks");
    List<Task> tasks = new ArrayList<>(tasksMap.stream().map(task -> {
      Map<?, ?> map = (Map<?, ?>) task;
      Task oTask = new Task();
      oTask.setId(Long.parseLong((String) map.get("id")));
      oTask.setDescription((String) map.get("description"));
      oTask.setStatus(TaskStatus.valueOf((String) map.get("status")));
      oTask.setCreatedAt(LocalDateTime.parse((String) map.get("createdAt")));
      oTask.setUpdatedAt(LocalDateTime.parse((String) map.get("updatedAt")));
      return oTask;
    }).toList());

    taskFile.setTasks(tasks);
    return taskFile;
  }

  private Map<?, ?> mapString(String json) {
    Stack<String> tokenStack = new Stack<>();
    Stack<String> propertyStack = new Stack<>();
    Stack<Object> valueStack = new Stack<>();
    Stack<Map.Entry<String, Object>> entryStack = new Stack<>();

    for (int i = 0; i < json.length(); i++) {
      if (json.charAt(i) == '{') {
        tokenStack.push("{");
      } else if (json.charAt(i) == '[') {
        tokenStack.push("[");
      } else if (json.charAt(i) == '"' && !tokenStack.peek().equals(":")) {
        int indexClose = json.indexOf('"', i + 1);
        String text = json.substring(i + 1, indexClose);
        propertyStack.push(text);
        i = indexClose;
      } else if (Character.isDigit(json.charAt(i))) {
        int indexClose = json.indexOf(',', i + 1);
        String text = json.substring(i, indexClose);
        valueStack.push(text);
        i = indexClose - 1;
      } else if (json.charAt(i) == '"') {
        int indexClose = json.indexOf('"', i + 1);
        String text = json.substring(i + 1, indexClose);
        valueStack.push(text);
        i = indexClose;
      } else if (json.charAt(i) == ':') {
        tokenStack.push(":");
      } else if (json.charAt(i) == ',') {
        if (tokenStack.peek().equals(":")) {
          Map.Entry<String, Object> entry = new AbstractMap.SimpleEntry<>(propertyStack.pop(), valueStack.pop());
          entryStack.push(entry);
          tokenStack.pop();
        }

        tokenStack.push(",");
      } else if (json.charAt(i) == '}') {
        Map<String, Object> object = new HashMap<>();

        if (tokenStack.peek().equals(":")) {
          object.put(propertyStack.pop(), valueStack.pop());
          tokenStack.pop();
        }

        while (!tokenStack.peek().equals("{")) {
          Map.Entry<String, Object> entry = entryStack.pop();
          object.put(entry.getKey(), entry.getValue());
          tokenStack.pop();
        }
        valueStack.push(object);
        tokenStack.pop();
      } else if (json.charAt(i) == ']') {
        List<Object> list = new ArrayList<>();

        while (tokenStack.peek().equals(",")) {
          list.add(valueStack.pop());
          tokenStack.pop();
        }

        if (tokenStack.peek().equals("[")) {
          list.add(valueStack.pop());
          tokenStack.pop();
        }

        valueStack.push(list);
      }
    }

    if (valueStack.peek() instanceof Map<?, ?>) {
      return (Map<?, ?>) valueStack.pop();
    }

    throw new RuntimeException("File not readable");
  }
}
