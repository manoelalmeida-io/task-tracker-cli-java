# Task Tracker CLI

Command-line interface application for managing tasks. It allows you to mark tasks as in-progress, done, and list tasks based on their status.

This project is a solution for this [task-tracker](https://roadmap.sh/projects/task-tracker) project from [roadmap.sh](https://roadmap.sh)

## Features

- Add, Update, and Delete tasks
- Mark a task as in progress or done
- List tasks with filtering options

## Requirements

- JDK (GraalVM)
- Maven

## Installation

1. Clone the repository:
```sh
git clone https://github.com/manoelalmeida-io/task-tracker-cli-java.git
cd task-tracker-cli-java
```

2. Navigate to the project directory:
```sh
cd task-cli
```

3. Build native executable using Maven:
```sh
mvn -Pnative package
```

## Usage

### Create Task

```sh
./target/task-tracker add "Buy groceries"
```


