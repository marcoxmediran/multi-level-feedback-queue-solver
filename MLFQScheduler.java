public class MLFQScheduler {
        private Queue timeline;
        private Queue inputQueue;
        private Queue resultQueue;
        private Queue queueA;
        private Queue queueB;
        private Queue queueC;
        private int[] quantums;
        private int finalMode;
        private Logger logger;

        public MLFQScheduler(Queue timeline, int quantumA, int quantumB, int finalMode) {
                this.initializeTimeline(timeline);
                this.initializeQueues();
                this.quantums = new int[]{quantumA, quantumB};
                this.logger = new Logger();
                this.finalMode = finalMode;
        }

        public void run() {
                System.out.println("[TIMELINE]");
                this.timeline.print();
                System.out.println("");

                while (true) {
                        while (!inputQueue.isEmpty() && inputQueue.front().getArrivalTime() <= logger.getTime()) {
                                inputQueue.front().setEndTime(logger.getTime());
                                queueA.enqueue(inputQueue.dequeue());
                        }

                        if (!queueA.isEmpty()) {
                                queueA.RR(this.quantums[0], this.logger);
                        }
                        else if (!queueB.isEmpty()) {
                                queueB.RR(this.quantums[1], this.logger);
                        }
                        else if (!queueC.isEmpty()) {
                                switch (this.finalMode) {
                                        case 1:
                                                queueC.FCFS(this.logger);
                                                break;
                                        case 2:
                                                queueC.SJF(this.logger);
                                                break;
                                        case 3:
                                                queueC.NPP(this.logger);
                                                break;
                                        default:
                                                break;
                                }
                        } else {
                                this.logger.incrementTime(1);
                        }

                        if (this.logger.getCount() == this.timeline.size())
                                break;

                }
                resultQueue.calculateResults();
                System.out.println("[RESULTS]");
                resultQueue.print();
                System.out.println("\n[GANTT CHART]");
                this.logger.printLogs();
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
