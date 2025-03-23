package com.github.manoelalmeidaio.domain;

import java.time.LocalDateTime;

public class Task {

  private Long id;
  private String description;
  private TaskStatus status = TaskStatus.TODO;
  private LocalDateTime createdAt = LocalDateTime.now();
  private LocalDateTime updatedAt = LocalDateTime.now();

  public Task() {}

  public Task(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "\n\t\t{" +
        "\n\t\t\t\"id\": " + id + ", " +
        "\n\t\t\t\"description\": " + '"' + description + '"' + ", " +
        "\n\t\t\t\"status\": " + '"' + status + '"' + ", " +
        "\n\t\t\t\"createdAt\": " + '"' + createdAt + '"' + ", " +
        "\n\t\t\t\"updatedAt\": " + '"' + updatedAt + '"' +
        "\n\t\t}";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
