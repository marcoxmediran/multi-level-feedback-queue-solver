public class MLFQScheduler {
        private Queue timeline;
        private Queue inputQueue;
        private Queue resultQueue;
        private Queue queueA;
        private Queue queueB;
        private Queue queueC;
        private int[] quantums;
        private Logger logger;

        public MLFQScheduler(Queue timeline) {
                this.initializeTimeline(timeline);
                this.initializeQueues();
                this.quantums = new int[]{4, 2};
                this.logger = new Logger();
        }

        public void run() {
                System.out.println("[inputQueue]");
                inputQueue.print();
                System.out.println("");

                while (true) {
                        System.out.printf("[Time %02d]\n", logger.time);
                        while (!inputQueue.isEmpty() && inputQueue.front().getArrivalTime() <= logger.time) {
                                inputQueue.front().setEndTime(logger.time);
                                System.out.println(inputQueue.front());
                                inputQueue.offer();
                        }

                        if (!queueA.isEmpty()) {
                                queueA.RR(2, this.logger);
                        }
                        else if (!queueB.isEmpty()) {
                                queueB.RR(2, this.logger);
                        }
                        else if (!queueC.isEmpty()) {
                                queueC.SJF(this.logger);
                        } else {
                                this.logger.incrementTime(1);
                        }

                        if (this.logger.count == this.timeline.size())
                                break;

                }
                resultQueue.calculateResults();
                resultQueue.print();
        }

        public void initializeTimeline(Queue timeline) {
                this.timeline = new Queue(timeline, "TIMELINE");
                this.timeline.sort("AT");
        }

        public void initializeQueues() {
                this.inputQueue = new Queue(this.timeline, "input");
                this.resultQueue = new Queue("result");
                this.queueA = new Queue("queueA");
                this.queueB = new Queue("queueB");
                this.queueC = new Queue("queueC");

                this.inputQueue.setNextQueue(this.queueA);
                this.queueA.setNextQueue(this.queueB);
                this.queueB.setNextQueue(this.queueC);
                this.queueC.setNextQueue(this.resultQueue);

                this.queueA.setResultQueue(this.resultQueue);
                this.queueB.setResultQueue(this.resultQueue);
                this.queueC.setResultQueue(this.resultQueue);
        }
}
