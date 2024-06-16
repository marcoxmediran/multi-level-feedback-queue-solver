public class MLFQScheduler {
        private Queue timeline;
        private Queue inputQueue;
        private Queue resultQueue;
        private Queue queueA;
        private Queue queueB;
        private Queue queueC;
        private Logger logger;

        public MLFQScheduler(Queue timeline) {
                this.initializeTimeline(timeline);
                this.initializeQueues();
                this.logger = new Logger();
        }

        public void run() {
                System.out.println("[inputQueue]");
                inputQueue.print();
                System.out.println("");

                for (; this.logger.count < timeline.size();) {

                        System.out.printf("[Time %02d]\n", logger.time);
                        while (!inputQueue.isEmpty() && inputQueue.front().getArrivalTime() <= logger.time) {
                                System.out.println("input -> queueA");
                                inputQueue.offer();
                        }

                        if (!queueA.isEmpty()) {
                                queueA.RR(4, this.logger);
                        }
                        else if (!queueB.isEmpty()) {
                                queueB.RR(2, this.logger);
                        }
                        else if (!queueC.isEmpty()) {
                                queueC.FCFS(this.logger);
                        } else {
                                continue;
                        }

                        if (this.logger.time == 18)
                                break;

                }
                resultQueue.print();
        }

        public void initializeTimeline(Queue timeline) {
                this.timeline = new Queue(timeline);
                this.timeline.sort("AT");
        }

        public void initializeQueues() {
                this.inputQueue = new Queue(this.timeline);
                this.resultQueue = new Queue();
                this.queueA = new Queue();
                this.queueB = new Queue();
                this.queueC = new Queue();

                this.inputQueue.setNextQueue(this.queueA);
                this.queueA.setNextQueue(this.queueB);
                this.queueB.setNextQueue(this.queueC);
                this.queueC.setNextQueue(this.resultQueue);

                this.queueA.setResultQueue(this.resultQueue);
                this.queueB.setResultQueue(this.resultQueue);
                this.queueC.setResultQueue(this.resultQueue);
        }

        public void setTime(int time) {
                time = 25;
        }

}
