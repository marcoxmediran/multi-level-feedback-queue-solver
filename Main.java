public class Main {
        public static void main(String[] args) {

                // instantiate jobs
                Job a = new Job('a', 0, 3, 0);
                Job b = new Job('b', 5, 6, 0);
                Job c = new Job('c', 7, 5, 0);
                Job d = new Job('d', 10, 7, 0);
                Job e = new Job('e', 8, 12, 0);
                Job f = new Job('f', 15, 3, 0);
                Job g = new Job('g', 18, 7, 0);

                // set timeline
                Queue timeline = new Queue("TIMELINE");
                timeline.enqueue(a);
                timeline.enqueue(b);
                timeline.enqueue(c);
                timeline.enqueue(d);
                timeline.enqueue(e);
                timeline.enqueue(f);
                timeline.enqueue(g);

                // schedule jobs
                MLFQScheduler mlfqScheduler = new MLFQScheduler(timeline, 2, 2, 2);
                mlfqScheduler.run();
        }
}
