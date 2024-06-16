public class Logger {
        public int time;
        public int count;
        public int quantumCounter;
        
        public Logger() {
                this.time = 0;
                this.count = 0;
                this.quantumCounter = 0;
        }

        public void incrementTime(int t) {
                this.time += t;
        }

        public void incrementCount() {
                this.count++;
        }

        public void incrementQuantumCounter() {
                this.quantumCounter++;
        }

        public void resetQuantumCounter() {
                this.quantumCounter = 0;
        }
}
