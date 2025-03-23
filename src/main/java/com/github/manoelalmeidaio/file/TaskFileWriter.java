package com.github.manoelalmeidaio.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TaskFileWriter {

  public void write(TaskFile file) {
    try {
      FileOutputStream outputStream = new FileOutputStream("tasks.json");
      byte[] strToBytes = file.toString().getBytes(StandardCharsets.UTF_8);
      outputStream.write(strToBytes);
      outputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
