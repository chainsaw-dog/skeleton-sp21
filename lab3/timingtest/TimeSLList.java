package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Integer> ops = new AList<>();
        AList<Double> times = new AList<>();
        int gl_times = 10000;
        for (int i=1000;i<=128000;i*=2){
            Ns.addLast(i);
            SLList<Integer> s=new SLList<>();
            while(s.size()<i){
                s.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for(int j=0;j<gl_times;j++){
                s.getLast();
            }
            double timeInSec = sw.elapsedTime();
            times.addLast(timeInSec);
            ops.addLast(gl_times);
        }
        printTimingTable(Ns,times,ops);
    }

}
