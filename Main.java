public class Main {
        public static void main(String[] args) {
                Job a = new Job('a', 2, 5);
                Job b = new Job('b', 4, 4);
                Job c = new Job('c', 8, 3);
                Job d = new Job('d', 9, 6);

                Queue timeline = new Queue();
                timeline.enqueue(a);
                timeline.enqueue(b);
                timeline.enqueue(c);
                timeline.enqueue(d);
                MLFQScheduler mlfqScheduler = new MLFQScheduler(timeline);
                mlfqScheduler.run();
        }
}
