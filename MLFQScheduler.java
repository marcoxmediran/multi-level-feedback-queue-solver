public class MLFQScheduler {
        private Queue timeline;
        private Queue inputQueue;
        private Queue resultQueue;
        private Queue queueA;
        private Queue queueB;
        private Queue queueC;

        public MLFQScheduler(Queue timeline) {
                this.initializeTimeline(timeline);
                this.initializeQueues();
        }

        public void run() {
                System.out.println("[inputQueue]");
                inputQueue.print();
                System.out.println("");

                int processedJobCount = 0;
                for (int time = 0; processedJobCount < timeline.size(); time++) {

                        if (time > 80)
                                break;

                        System.out.printf("[Time %02d]\n", time);
                        while (!inputQueue.isEmpty() && inputQueue.front().getArrivalTime() == time) {
                                System.out.println("input -> queueA");
                                inputQueue.offer();
                        }

                        if (!queueA.isEmpty()) {
                                queueA.RR(4);
                        }
                        else if (!queueB.isEmpty()) {
                                queueB.RR(2);
                        }
                        else if (!queueC.isEmpty()) {
                                queueC.FCFS(processedJobCount);
                        } else {
                                continue;
                        }

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

}
