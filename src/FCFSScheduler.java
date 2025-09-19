import java.util.*;

class FCFSScheduler extends Scheduler {
    public FCFSScheduler(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        int currentTime = 0;
        double totalWaiting = 0;
        double totalTurnaround = 0;

        for (Process p : processes) {
            if (currentTime < p.getArrivalTime()) {
                currentTime = p.getArrivalTime();
            }
            p.setWaitingTime(currentTime - p.getArrivalTime());
            p.setCompletionTime(currentTime + p.getBurstTime());
            p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
            currentTime = p.getCompletionTime();
            totalWaiting += p.getWaitingTime();
            totalTurnaround += p.getTurnaroundTime();
        }

        printResults(totalWaiting, totalTurnaround);
    }
}