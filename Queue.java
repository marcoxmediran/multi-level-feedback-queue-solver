import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
        public String name;
        private ArrayList<Job> queue;
        private Queue next;
        private Queue result;

        public Queue(ArrayList<Job> timeline, String name) {
                this.queue = new ArrayList<>();
                for (Job job : timeline) {
                        this.queue.add(job);
                }
                this.name = name;
        }

        public Queue(Queue input, String name) {
                this.queue = new ArrayList<>();
                for (Job job : input.queue) {
                        this.queue.add(job);
                }
                this.name = name;
        }

        public Queue(String name) {
                this.queue = new ArrayList<>();
                this.name = name;
        }

        public void enqueue(Job job) {
                this.queue.add(job);
        }

        public Job dequeue() {
                return this.queue.removeFirst();
        }

        public void offer() {
                System.out.println(this.name + " -> " + this.next.name);
                this.next.enqueue(this.dequeue());
        }

        public void moveToResult() {
                System.out.println(this.name + " -> " + this.result.name);
                this.result.enqueue(this.dequeue());
        }

        public void requeue() {
                this.enqueue(this.dequeue());
        }

        public Job front() {
                return this.queue.getFirst();
        }

        public int size() {
                return this.queue.size();
        }

        public boolean isEmpty() {
                return this.queue.isEmpty();
        }

        public void setNextQueue(Queue next) {
                this.next = next;
        }

        public void setResultQueue(Queue result) {
                this.result = result;
        }

        public void incrementAllEndTime(int time) {
                for (Job job : this.queue) {
                        job.incrementEndTime(time);
                }
        }

        public void RR(int quantum, Logger logger) {
                Job currentJob = this.front();
                System.out.println(currentJob.getQuantumTime() + "/" + quantum);
                System.out.println(currentJob);
                logger.incrementTime(1);
                currentJob.setEndTime(logger.time);
                currentJob.decrementRemainingTime(1);
                currentJob.incrementQuantumTime();

                if (currentJob.getRemainingTime() == 0) {
                        logger.incrementCount();
                        currentJob.resetQuantumTime();
                        System.out.println(currentJob.getQuantumTime() + "/" + quantum);
                        System.out.println(currentJob);
                        this.moveToResult();
                }

                if (currentJob.getQuantumTime() == quantum) {
                        System.out.println(currentJob.getQuantumTime() + "/" + quantum);
                        System.out.println(currentJob);
                        currentJob.resetQuantumTime();
                        this.offer();
                }
        }

        public void FCFS(Logger logger) {
                Job currentJob = this.front();
                logger.incrementTime(1);
                currentJob.setEndTime(logger.time);
                currentJob.decrementRemainingTime(1);

                if (currentJob.getRemainingTime() == 0) {
                        logger.incrementCount();
                        this.moveToResult();
                }
        }

        public void calculateResults() {
                for (Job job : this.queue) {
                        job.calculateTurnAroundTime();
                        job.calculateWaitingTime();
                }
        }

        public void sort(String mode) {
                switch (mode) {
                        case "AT":
                                this.queue.sort(Comparator.comparing(Job::getArrivalTime));
                                break;
                        default:
                                break;
                }
        }

        public void print() {
                System.out.println("ID\tAT\tBT\tRT\tET\tTaT\tWT");
                for (Job job : this.queue) {
                        System.out.println(job);
                }
        }
}
