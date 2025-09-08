package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        //Make size to 1000
        for (int i = 0; i < 1000; i++){
            Ns.addLast(1);
        }

        while(Ns.size() <= 128000) {
            Stopwatch sw = new Stopwatch();
            //Resize to double
            int size = Ns.size();
            for(int i = size; i < size * 2; i++) {
                Ns.addLast(1);
            }
            Double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        AList<Integer> N = new AList<>();
        AList<Integer> ops = new AList<>();
        for (int i = 1000; i <= 128000; i *= 2){
            N.addLast(i);
            ops.addLast(i);
        }

        printTimingTable(N,times,ops);
    }
}
