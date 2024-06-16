public class Job {
        private char id;
        private int arrivalTime;
        private int burstTime;
        private int remainingTime;
        private int level;
        private int endTime;
        private int turnAroundTime;
        private int waitingTime;

        public Job(char id, int arrivalTime, int burstTime) {
                this.id = id;
                this.arrivalTime = arrivalTime;
                this.burstTime = burstTime;
                this.remainingTime = burstTime;
                this.level = 0;
                this.endTime = arrivalTime;
        }

        public Job(char id, int arrivalTime, int burstTime, int remainingTime, int level, int endTime, int turnAroundTime, int waitingTime) {
                this.id = id;
                this.arrivalTime = arrivalTime;
                this.burstTime = burstTime;
                this.remainingTime = remainingTime;
                this.level = level;
                this.endTime = endTime;
                this.turnAroundTime = turnAroundTime;
                this.waitingTime = waitingTime;
        }

        public int getArrivalTime() {
                return this.arrivalTime;
        }

        public int getRemainingTime() {
                return this.remainingTime;
        }

        public void setRemainingTime(int time) {
                this.remainingTime = time;
        }

        public int getTurnAroundTime() {
                return this.turnAroundTime;
        }

        public int getWaitingTime() {
                return this.waitingTime;
        }

        public void decrementRemainingTime() {
                this.remainingTime--;
        }

        public boolean isDone() {
                return this.remainingTime == 0;
        }

        public void incrementEndTime() {
                this.endTime++;
        }

        public void calculateTurnAroundTime() {
                this.turnAroundTime = this.endTime - this.arrivalTime;
        }

        public void calculateWaitingTime() {
                this.waitingTime = this.turnAroundTime - this.burstTime;
        }

        public Job copy() {
                return new Job(this.id, this.arrivalTime, this.burstTime, this.remainingTime, this.level, this.endTime, this.turnAroundTime, this.waitingTime);
        }

        public String toString() {
                return this.id + "\t" + this.arrivalTime + "\t" + this.burstTime + "\t" + this.remainingTime + "\t" + this.endTime + "\t" + this.turnAroundTime + "\t" + this.waitingTime;
        }
}
