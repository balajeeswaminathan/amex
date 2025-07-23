# Scheduled Command Executor

This utility executes scheduled shell commands using two scheduling patterns:
- One-time scheduled execution
- Recurring scheduled execution

---

## Problem Statement

Create a utility to execute shell commands at scheduled times based on two input formats.

---

##  Features

- **One-time scheduled command**
  - Format:
    `Minute Hour Day Month Year <command>`
  - Example:
    `30 17 30 4 2025 date && echo "One-time command"`

- **Recurring scheduled command**
  - Format:  
    `*/n <command>`
  - Supported values of `n`: `1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 30, 60`
  - Example:  
    `*/5 date && echo "This runs every 5 minutes"`

---

## How to Run

### 1. Add your commands
Edit the `tmp/commands.txt` file and include valid commands in the supported formats.

### 2. Compile and Run
javac -d out src/main/java/*.java
java -cp out main.java.CommandScheduler