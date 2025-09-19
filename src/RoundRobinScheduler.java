import java.util.*;

class RoundRobinScheduler extends Scheduler {
    private int quantum;

    public RoundRobinScheduler(List<Process> processes, int quantum) {
        super(processes);
        this.quantum = quantum;
    }

    @Override
    public void schedule() {
        List<Process> remainingProcesses = new ArrayList<>(processes);
        Queue<Process> queue = new LinkedList<>();
        List<Process> completedProcesses = new ArrayList<>();
        int currentTime = 0;
        double totalWaiting = 0;
        double totalTurnaround = 0;

        while (!remainingProcesses.isEmpty() || !queue.isEmpty()) {
            Iterator<Process> iterator = remainingProcesses.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if (p.getArrivalTime() <= currentTime) {
                    queue.add(p);
                    iterator.remove();
                }
            }

            if (queue.isEmpty()) {
                if (!remainingProcesses.isEmpty()) {
                    remainingProcesses.sort(Comparator.comparingInt(Process::getArrivalTime));
                    currentTime = remainingProcesses.get(0).getArrivalTime();
                }
                continue;
            }

            Process p = queue.poll();
            int execTime = Math.min(quantum, p.getRemainingTime());
            p.setRemainingTime(p.getRemainingTime() - execTime);
            currentTime += execTime;

            if (p.getRemainingTime() > 0) {
                queue.add(p);
            } else {
                p.setCompletionTime(currentTime);
                p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                completedProcesses.add(p);
                totalWaiting += p.getWaitingTime();
                totalTurnaround += p.getTurnaroundTime();
            }
        }

        processes = completedProcesses;
        printResults(totalWaiting, totalTurnaround);
    }
}