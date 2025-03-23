package com.github.manoelalmeidaio;

import com.github.manoelalmeidaio.domain.Task;

public class Main {

  public static void main(String[] args) {

    TaskStorage storage = new TaskStorage();
    storage.add(new Task("Dar banho no peixe"));
    storage.add(new Task("Chamar o Joca para almoçar"));
  }
}