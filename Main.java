public class Main {
        public static void main(String[] args) {
                Job a = new Job('a', 1, 4, 6);
                Job b = new Job('b', 5, 12, 1);
                Job c = new Job('c', 5, 9, 4);
                Job d = new Job('d', 11, 2, 3);
                Job e = new Job('e', 16, 10, 1);
                Job f = new Job('f', 20, 11, 5);
                Job g = new Job('g', 35, 3, 0);

                //Scheduler scheduler = new Scheduler();
                //scheduler.addJob(a);
                //scheduler.addJob(b);
                //scheduler.addJob(c);
                //scheduler.addJob(d);
                //scheduler.addJob(e);
                //scheduler.addJob(f);
                //scheduler.addJob(g);
                //scheduler.printTimeline();
                //System.out.println();
                //scheduler.FCCS();
                //System.out.println();
                //scheduler.RR(5);

                Queue timeline = new Queue();
                timeline.enqueue(a);
                timeline.enqueue(b);
                timeline.enqueue(c);
                timeline.enqueue(d);
                timeline.enqueue(e);
                timeline.enqueue(f);
                timeline.enqueue(g);
                MLFQScheduler mlfqScheduler = new MLFQScheduler(timeline);
                mlfqScheduler.run();
        }
}
