import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
        private ArrayList<Job> queue;
        private Queue next;
        private Queue result;

        public Queue(ArrayList<Job> timeline) {
                this.queue = new ArrayList<>();
                for (Job job : timeline) {
                        this.queue.add(job);
                }
        }

        public Queue(Queue input) {
                this.queue = new ArrayList<>();
                for (Job job : input.queue) {
                        this.queue.add(job);
                }
        }

        public Queue() {
                this.queue = new ArrayList<>();
        }

        public void enqueue(Job job) {
                this.queue.add(job);
        }

        public Job dequeue() {
                return this.queue.removeFirst();
        }

        public void offer() {
                this.next.enqueue(this.dequeue());
        }

        public void moveToResult() {
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

        public void incrementAllEndTime() {
                for (Job job : this.queue) {
                        job.incrementEndTime();
                }
        }

        public void setNextQueue(Queue next) {
                this.next = next;
        }

        public void setResultQueue(Queue result) {
                this.result = result;
        }

        public void RR(int quantum) {
                while (!this.isEmpty()) {
                        Job currentJob = this.front();
                        if (currentJob.getRemainingTime() <= quantum) {
                                currentJob.setRemainingTime(0);
                                System.out.println("Job in range of quantum");
                                System.out.println(currentJob);
                                this.moveToResult();
                        } else {
                                currentJob.setRemainingTime(currentJob.getRemainingTime() - quantum);
                                System.out.println("queueA -> queueB");
                                System.out.println(currentJob);
                                this.offer();
                        }
                }
                System.out.println("RR Done");
        }

        public void FCFS(int processedJobCount) {
                processedJobCount++;
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
