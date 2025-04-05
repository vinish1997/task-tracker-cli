# ✨ Task Tracker CLI — Command-Line Productivity, Supercharged!

A simple, elegant, and Java-powered CLI tool to help you manage your daily tasks without ever leaving your terminal. Built with Spring Shell, this tool brings efficiency and focus to your workflow.

---

## 📚 Features

* ✍️ **Add Tasks** with a simple command
* 🔍 **List Tasks** by status (`TO_DO`, `IN_PROGRESS`, `DONE`) or view all
* ✏️ **Update** task descriptions
* ⏳ **Mark Tasks** as `IN_PROGRESS` or `DONE`
* ❌ **Delete Tasks** you no longer need

---

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/task-tracker-cli.git
cd task-tracker-cli
```

### 2. Run the App

Make sure you have JDK 17+ installed.

```bash
./mvnw spring-boot:run
```

Once started, you'll enter an interactive shell. Welcome to productive bliss!

---

## 🔧 Commands Overview

### Add a Task

```bash
task-cli add <description>
```

### List All Tasks

```bash
task-cli list
```

### List Tasks by Status

```bash
task-cli list <status>
```

### Update Task Description

```bash
task-cli update <id> <description>
```

### Mark as In Progress

```bash
task-cli mark-in-progress <id>
```

### Mark as Done

```bash
task-cli mark-done <id>
```

### Delete a Task

```bash
task-cli delete <id>
```

---

## 📄 Task Object Structure

```java
Task {
  Integer id;
  String description;
  TaskStatus status; // TO_DO, IN_PROGRESS, DONE
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
}
```

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot
* Spring Shell
* Lombok
* Maven

---

## ✨ Future Improvements

* Persistence with a real database
* Task deadlines & reminders
* Search & filtering enhancements
* Export to CSV or JSON

---

## 🙌 Contributions Welcome

Found a bug? Got an idea? Want to add features? Fork it, push it, and raise a PR. Let's build it better together!

---

## ❤️ Author

Made with passion by [Vinish Choudhary](https://github.com/vinish1997)

### Project : [task-cli](https://roadmap.sh/projects/task-tracker)
