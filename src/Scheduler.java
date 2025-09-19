import java.util.*;

abstract class Scheduler {
    protected List<Process> processes;

    public Scheduler(List<Process> processes) {
        this.processes = copyProcesses(processes);
    }

    public abstract void schedule();

    protected List<Process> copyProcesses(List<Process> processes) {
        List<Process> copy = new ArrayList<>();
        for (Process p : processes) {
            copy.add(p.copy());
        }
        return copy;
    }

    protected void printResults(double totalWaiting, double totalTurnaround) {
        System.out.println("Process\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        processes.sort(Comparator.comparingInt(Process::getId));
        for (Process p : processes) {
            System.out.println(p.getId() + "\t" + p.getArrivalTime() + "\t" + p.getBurstTime() + "\t" +
                    p.getCompletionTime() + "\t\t" + p.getTurnaroundTime() + "\t\t" + p.getWaitingTime());
        }
        int n = processes.size();
        System.out.println("Average Waiting Time: " + (totalWaiting / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaround / n));
    }
}