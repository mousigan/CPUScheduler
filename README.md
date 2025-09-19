
## Files in the Project

- `Process.java`: Defines a process (like a task) with details like ID and time needed.
- `Scheduler.java`: A template for all scheduling methods.
- `FCFSScheduler.java`: Code for FCFS scheduling.
- `SJFScheduler.java`: Code for SJF scheduling.
- `RoundRobinScheduler.java`: Code for Round Robin scheduling.
- `UniScheduler.java`: Code for SRTF scheduling.
- `CPUScheduler.java`: The main file that runs the program and shows the menu.

## Tips for Beginners

- **Arrival Time**: When the task arrives (e.g., 0 means at the start).
- **Burst Time**: How long the task needs to run (e.g., 5 time units).
- **Time Quantum**: For Round Robin, how long each task gets before switching (e.g., 2 units).
- Try small numbers first (e.g., 3 processes) to understand the output.
- If you get errors, check that all files are in the same folder and Java is installed.

## Adding to GitHub

1. Create a GitHub repository called `CPUScheduler`.
2. Push the files:
   ```bash
   git init
   git add .
   git commit -m "Initial commit: CPU Scheduler Simulator"
   git remote add origin https://github.com/yourusername/CPUScheduler.git
   git push -u origin main
