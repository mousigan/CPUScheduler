import java.util.*;

public class CPUScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            int n;
            do {
                System.out.print("Enter number of processes: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    scanner.next();
                }
                n = scanner.nextInt();
                if (n <= 0) {
                    System.out.println("Number of processes must be positive.");
                }
            } while (n <= 0);

            List<Process> processes = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                System.out.print("Enter arrival time for process " + i + ": ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    scanner.next();
                }
                int arrival = scanner.nextInt();

                System.out.print("Enter burst time for process " + i + ": ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    scanner.next();
                }
                int burst = scanner.nextInt();
                if (burst <= 0) {
                    System.out.println("Burst time must be positive. Re-enter:");
                    i--;
                    continue;
                }
                processes.add(new Process(i, arrival, burst));
            }

            int quantum;
            do {
                System.out.print("Enter time quantum for Round Robin: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    scanner.next();
                }
                quantum = scanner.nextInt();
                if (quantum <= 0) {
                    System.out.println("Time quantum must be positive.");
                }
            } while (quantum <= 0);

            boolean innerLoop = true;
            while (innerLoop) {
                System.out.println("\nOptions:");
                System.out.println("1. Run FCFS");
                System.out.println("2. Run SJF");
                System.out.println("3. Run Round Robin");
                System.out.println("4. Run Uni (SRTF)");
                System.out.println("5. Run All");
                System.out.println("6. Display Input");
                System.out.println("7. Exit");
                System.out.print("Choose an option (1-7): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer (1-7).");
                    scanner.next();
                }
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\nFCFS Scheduling:");
                        new FCFSScheduler(processes).schedule();
                        break;
                    case 2:
                        System.out.println("\nSJF (Non-Preemptive) Scheduling:");
                        new SJFScheduler(processes).schedule();
                        break;
                    case 3:
                        System.out.println("\nRound Robin Scheduling:");
                        new RoundRobinScheduler(processes, quantum).schedule();
                        break;
                    case 4:
                        System.out.println("\nUni (SRTF) Scheduling:");
                        new UniScheduler(processes).schedule();
                        break;
                    case 5:
                        System.out.println("\nFCFS Scheduling:");
                        new FCFSScheduler(processes).schedule();
                        System.out.println("\nSJF (Non-Preemptive) Scheduling:");
                        new SJFScheduler(processes).schedule();
                        System.out.println("\nRound Robin Scheduling:");
                        new RoundRobinScheduler(processes, quantum).schedule();
                        System.out.println("\nUni (SRTF) Scheduling:");
                        new UniScheduler(processes).schedule();
                        break;
                    case 6:
                        System.out.println("\nInput Processes:");
                        System.out.println("Process\tArrival\tBurst");
                        for (Process p : processes) {
                            System.out.println(p.getId() + "\t" + p.getArrivalTime() + "\t" + p.getBurstTime());
                        }
                        System.out.println("Round Robin Quantum: " + quantum);
                        break;
                    case 7:
                        innerLoop = false;
                        continueRunning = false;
                        System.out.println("Process stopped.");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-7.");
                }
            }
        }
        scanner.close();
    }
}