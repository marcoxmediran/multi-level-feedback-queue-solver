public class MLFQScheduler {
        private Queue timeline;
        private Queue inputQueue;
        private Queue queueA;
        private Queue queueB;
        private Queue queueC;

        public MLFQScheduler(Queue timeline) {
                this.initializeTimeline(timeline);
                this.initializeQueues();
        }

        public Queue run() {
                System.out.println("[inputQueue]");
                inputQueue.print();
                System.out.println("");

                Queue results = new Queue();

                int processedJobCount = 0;
                for (int time = 0; processedJobCount != timeline.size(); time++) {
                        System.out.printf("[Time %02d]\n", time);
                        while (!timeline.isEmpty() && inputQueue.front().getArrivalTime() == time) {
                                System.out.println("input -> queueA");
                                inputQueue.offer();
                        }

                        if (!queueA.isEmpty()) {
                                System.out.println("queueA -> queueB");
                                queueA.offer();
                        }
                        if (!queueB.isEmpty()) {
                                System.out.println("queueB -> queueC");
                                queueB.offer();
                        }
                        if (!queueC.isEmpty()) {
                                System.out.println("Proccessing queueC");
                        } else {
                                continue;
                        }

                        processedJobCount++;
                }
                return results;
        }

        public void initializeTimeline(Queue timeline) {
                this.timeline = new Queue(timeline);
                this.timeline.sort("AT");
        }

        public void initializeQueues() {
                this.inputQueue = new Queue(this.timeline);
                this.queueA = new Queue();
                this.queueB = new Queue();
                this.queueC = new Queue();

                this.inputQueue.setNextQueue(this.queueA);
                this.queueA.setNextQueue(this.queueB);
                this.queueB.setNextQueue(this.queueC);
        }

}
