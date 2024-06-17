public class Main {
        public static void main(String[] args) {
                /*
                Job a = new Job('a', 0, 3);
                Job b = new Job('b', 0, 8);
                Job c = new Job('c', 2, 10);
                Job d = new Job('d', 2, 1);
                Job e = new Job('e', 14, 5);
                */
                Job a = new Job('a', 0, 3);
                Job b = new Job('b', 5, 6);
                Job c = new Job('c', 7, 5);
                Job d = new Job('d', 10, 7);
                Job e = new Job('e', 8, 12);
                Job f = new Job('f', 15, 3);
                Job g = new Job('g', 18, 7);

                Queue timeline = new Queue("TIMELINE");
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
