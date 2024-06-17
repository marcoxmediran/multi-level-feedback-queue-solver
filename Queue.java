import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
        private String name;
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

        public String getName() {
                return this.name;
        }

        public void enqueue(Job job) {
                this.queue.add(job);
        }

        public Job dequeue() {
                return this.queue.removeFirst();
        }

        public void offer() {
                Job toOffer = this.dequeue();
                toOffer.incrementLevel();
                this.next.enqueue(toOffer);
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
                if (currentJob.getRemainingTime() <= quantum) {
                        logger.incrementTime(currentJob.getRemainingTime());
                        currentJob.setEndTime(logger.getTime());
                        currentJob.setRemainingTime(0);
                        logger.incrementCount();
                        this.moveToResult();
                } else {
                        logger.incrementTime(quantum);
                        currentJob.setEndTime(logger.getTime());
                        currentJob.setRemainingTime(currentJob.getRemainingTime() - quantum);
                        this.offer();
                }
        }

        public void FCFS(Logger logger) {
                Job currentJob = this.front();
                logger.incrementTime(currentJob.getRemainingTime());
                logger.incrementCount();
                currentJob.setEndTime(logger.getTime());
                currentJob.setRemainingTime(0);
                this.moveToResult();
        }

        public void SJF(Logger logger) {
                this.sort("SJF");
                Job currentJob = this.front();
                logger.incrementTime(currentJob.getRemainingTime());
                logger.incrementCount();
                currentJob.setEndTime(logger.getTime());
                currentJob.setRemainingTime(0);
                this.moveToResult();
        }

        // TODO
        public void NPP(Logger logger) {
                
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
                        case "SJF":
                                this.queue.sort(Comparator.comparing(Job::getRemainingTime));
                                break;
                        default:
                                break;
                }
        }

        public void print() {
                System.out.println("ID\tAT\tBT\tRT\tLevel\tET\tTaT\tWT");
                for (Job job : this.queue) {
                        System.out.println(job);
                }
        }
}
