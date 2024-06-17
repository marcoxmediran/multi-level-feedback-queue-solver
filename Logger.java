public class Logger {
        private int time;
        private int count;

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

        public int getTime() {
                return this.time;
        }

        public int getCount() {
                return this.count;
        }
}
