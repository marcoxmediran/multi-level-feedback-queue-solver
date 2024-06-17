public class Log {
        private char id;
        private int level;
        private int startTime;
        private int endTime;

        public Log(char id, int level, int startTime) {
                this.id = id;
                this.level = level;
                this.startTime = startTime;
        }

        public char getId() {
                return this.id;
        }

        public int getLevel() {
                return this.level;
        }

        public int getStartTime() {
                return this.startTime;
        }

        public int getEndTime() {
                return this.endTime;
        }

        public void setEndTime(int time) {
                this.endTime = time;
        }

        public String toString() {
                return this.id + "\t" + this.level + "\t" + this.startTime + "\t" + this.endTime;
        }

}
