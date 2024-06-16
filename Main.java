public class Main {
        public static void main(String[] args) {
                Job a = new Job('a', 0, 3);
                Job b = new Job('b', 0, 8);
                Job c = new Job('c', 2, 10);
                Job d = new Job('d', 2, 1);
                Job e = new Job('e', 14, 5);

                Queue timeline = new Queue("TIMELINE");
                timeline.enqueue(a);
                timeline.enqueue(b);
                timeline.enqueue(c);
                timeline.enqueue(d);
                timeline.enqueue(e);
                MLFQScheduler mlfqScheduler = new MLFQScheduler(timeline);
                mlfqScheduler.run();
        }
}
