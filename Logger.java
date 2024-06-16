public class Logger {
        public int time;
        public int count;

        public Logger() {
                this.time = 0;
                this.count = 0;
        }

        public void incrementTime(int t) {
                this.time += t;
        }

        public void incrementCount() {
                this.count++;
        }
}
