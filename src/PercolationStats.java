import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private static final double CONFIDENCE_THRESHOLD = 1.96;
    private final int n;
    private final double[] scores;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.scores = new double[trials];

        for (int trialIndex = 0; trialIndex < trials; trialIndex++) {
            Percolation percolation = new Percolation(n);
            int[] sequence = makeSequence(0, n * n - 1);
            StdRandom.shuffle(sequence);
            int i = 0;
            while (!percolation.percolates()) {
                percolation.open(indexToRow(sequence[i]), indexToCol(sequence[i]));
                i++;
            }
            scores[trialIndex] = i / (double) (n * n);
        }
    }

    private int indexToCol(int j) {
        return j / n + 1;
    }

    private int indexToRow(int j) {
        return j % n + 1;
    }

    public double mean() {
        return StdStats.mean(scores);
    }

    public double stddev() {
        return StdStats.stddev(scores);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_THRESHOLD * stddev() / Math.sqrt(scores.length);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_THRESHOLD * stddev() / Math.sqrt(scores.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.print("\nmean                    = " + stats.mean());
        StdOut.print("\nstddev                  = " + stats.stddev());
        StdOut.print("\n95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

    private int[] makeSequence(int begin, int end) {
        int[] ret = new int[end - begin + 1];
        for (int i = begin; i <= end; i++) {
            ret[i] = i;
        }
        return ret;
    }
}