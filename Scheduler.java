import java.util.ArrayList;
import java.util.Comparator;

public class Scheduler {

        private ArrayList<Job> timeline;

        public Scheduler() {
                this.timeline = new ArrayList<>();
        }

        public void FCCS() {
                ArrayList<Job> fcfs = this.cloneTimeline();
                Queue inputQueue = new Queue(fcfs);
                Queue processQueue = new Queue();
                int checker = 0;
                for (int time = 0; checker < fcfs.size(); time++) {
                        while (!inputQueue.isEmpty() && inputQueue.front().getArrivalTime() == time) 
                                processQueue.enqueue(inputQueue.dequeue());

                        if (processQueue.isEmpty())
                                continue;

                        processQueue.incrementAllEndTime();        
                        Job current = processQueue.front();
                        current.decrementRemainingTime();
                        if (current.isDone()) {
                                current.calculateTurnAroundTime();
                                current.calculateWaitingTime();
                                processQueue.dequeue();
                                checker++;
                        }
                }
                this.printResults(fcfs, "FCFS");
        }

        public void RR(int quantum) {
                ArrayList<Job> rr = this.cloneTimeline();
                Queue inputQueue = new Queue(rr);
                Queue processQueue = new Queue();
                int checker = 0;
                int counter = 0;
                for (int time = 0; checker < rr.size(); time++) {
                        while (!inputQueue.isEmpty() && inputQueue.front().getArrivalTime() == time)
                                processQueue.enqueue(inputQueue.dequeue());

                        if (processQueue.isEmpty())
                                continue;

                        if (counter > quantum) {
                                processQueue.requeue();
                                counter = 1;
                        }
                        counter++;

                        processQueue.incrementAllEndTime();
                        Job current = processQueue.front();
                        current.decrementRemainingTime();
                        if (current.isDone()) {
                                current.calculateTurnAroundTime();
                                current.calculateWaitingTime();
                                processQueue.dequeue();
                                checker++;
                                counter = 1;
                        }
                }
                this.printResults(rr, "RR quantum = " + quantum);
        }

        public void addJob(Job job) {
                this.timeline.add(job);
                this.timeline.sort(Comparator.comparing(Job::getArrivalTime));
        }

        public ArrayList<Job> cloneTimeline() {
                ArrayList<Job> clone = new ArrayList<>();
                for (Job job : this.timeline) {
                        clone.add(job.copy());
                }
                return clone;
        }

        public void printTimeline() {
                System.out.println("[TIMELINE]");
                System.out.println("ID\tAT\tBT\tPrio\tRT\tLevel\tET\tTaT\tWT");
                for (Job job : this.timeline) {
                        System.out.println(job);
                }
        }

        public void printResults(ArrayList<Job> jobs, String header) {
                System.out.println("[" + header + "]");
                System.out.println("ID\tAT\tBT\tPrio\tRT\tLevel\tET\tTaT\tWT");
                double averageTAT = 0;
                double averageWT = 0;
                for (Job job : jobs) {
                        System.out.println(job);
                        averageTAT += job.getTurnAroundTime();
                        averageWT += job.getWaitingTime();
                }
                averageTAT /= jobs.size();
                averageWT /= jobs.size();
                System.out.printf("Average TaT: %.2f\n", averageTAT);
                System.out.printf("Average WT: %.2f\n", averageWT);
        }
}
