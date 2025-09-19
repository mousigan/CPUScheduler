import java.util.*;

class SJFScheduler extends Scheduler {
    public SJFScheduler(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        List<Process> remainingProcesses = new ArrayList<>(processes);
        List<Process> completedProcesses = new ArrayList<>();
        List<Process> readyQueue = new ArrayList<>();
        int currentTime = 0;
        double totalWaiting = 0;
        double totalTurnaround = 0;

        while (!remainingProcesses.isEmpty() || !readyQueue.isEmpty()) {
            Iterator<Process> iterator = remainingProcesses.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if (p.getArrivalTime() <= currentTime) {
                    readyQueue.add(p);
                    iterator.remove();
                }
            }

            if (readyQueue.isEmpty()) {
                if (!remainingProcesses.isEmpty()) {
                    remainingProcesses.sort(Comparator.comparingInt(Process::getArrivalTime));
                    currentTime = remainingProcesses.get(0).getArrivalTime();
                }
                continue;
            }

            readyQueue.sort(Comparator.comparingInt(Process::getBurstTime));
            Process p = readyQueue.remove(0);
            p.setWaitingTime(currentTime - p.getArrivalTime());
            p.setCompletionTime(currentTime + p.getBurstTime());
            p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
            currentTime = p.getCompletionTime();
            completedProcesses.add(p);
            totalWaiting += p.getWaitingTime();
            totalTurnaround += p.getTurnaroundTime();
        }

        processes = completedProcesses;
        printResults(totalWaiting, totalTurnaround);
    }
}