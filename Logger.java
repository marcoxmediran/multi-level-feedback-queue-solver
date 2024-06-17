import java.util.ArrayList;

public class Logger {
        private int time;
        private int count;
        private ArrayList<Log> logs;

        public Logger() {
                this.time = 0;
                this.count = 0;
                this.logs = new ArrayList<>();
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

        public void addLog(Log log) {
                this.logs.add(log);
        }

        public void printLogs() {
                System.out.println("ID\tLevel\tStart\tEnd");
                for (Log log : this.logs) {
                        System.out.println(log);
                }
        }
}
