public class Main {
        public static void main(String[] args) {
                Job a = new Job('a', 0, 3);
                Job b = new Job('b', 2, 4);
                Job c = new Job('c', 4, 5);
                Job d = new Job('d', 6, 6);

                Queue timeline = new Queue();
                timeline.enqueue(a);
                timeline.enqueue(b);
                timeline.enqueue(c);
                timeline.enqueue(d);
                MLFQScheduler mlfqScheduler = new MLFQScheduler(timeline);
                mlfqScheduler.run();
        }
}
