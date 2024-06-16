public class Main {
        public static void main(String[] args) {
                Job a = new Job('a', 0, 3);
                Job b = new Job('b', 0, 8);
                Job c = new Job('c', 2, 3);
                Job d = new Job('d', 2, 1);

                Queue timeline = new Queue("TIMELINE");
                timeline.enqueue(a);
                timeline.enqueue(b);
                timeline.enqueue(c);
                timeline.enqueue(d);
                MLFQScheduler mlfqScheduler = new MLFQScheduler(timeline);
                mlfqScheduler.run();
        }
}
