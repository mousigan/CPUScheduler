import java.util.*;

class UniScheduler extends Scheduler {
    public UniScheduler(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        List<Process> remainingProcesses = new ArrayList<>(processes);
        List<Process> completedProcesses = new ArrayList<>();
        int currentTime = 0;
        double totalWaiting = 0;
        double totalTurnaround = 0;

        while (!remainingProcesses.isEmpty()) {
            Process shortest = null;
            int minRemainingTime = Integer.MAX_VALUE;

            for (Process p : remainingProcesses) {
                if (p.getArrivalTime() <= currentTime && p.getRemainingTime() < minRemainingTime && p.getRemainingTime() > 0) {
                    shortest = p;
                    minRemainingTime = p.getRemainingTime();
                }
            }

            if (shortest == null) {
                currentTime++;
                continue;
            }

            shortest.setRemainingTime(shortest.getRemainingTime() - 1);
            currentTime++;

            if (shortest.getRemainingTime() == 0) {
                shortest.setCompletionTime(currentTime);
                shortest.setTurnaroundTime(shortest.getCompletionTime() - shortest.getArrivalTime());
                shortest.setWaitingTime(shortest.getTurnaroundTime() - shortest.getBurstTime());
                completedProcesses.add(shortest);
                remainingProcesses.remove(shortest);
                totalWaiting += shortest.getWaitingTime();
                totalTurnaround += shortest.getTurnaroundTime();
            }
        }

        processes = completedProcesses;
        printResults(totalWaiting, totalTurnaround);
    }
}