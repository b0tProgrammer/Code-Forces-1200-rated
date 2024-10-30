import java.util.*;
import java.io.*;

public class Main {

    private static final long MAX = (long) 1e9 + 7; // max sizber for MOD

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

            Comparator<Pair> com = (i, j) -> (int) ((i.val - j.val));

            int testcases = 1;

            testcases = in.nextInt();

            while (testcases-- > 0) {
                /*
                    Dolce vita!
                    In this, we have to count maximum number of packets we can buy!
                    this can be done up to any packet costs <= given budget.
                    let's take this example : After sorting!
                    1 2 2 -> step 0 can buy all 3
                    2 3 3 -> step 1 can buy 2
                    3 4 4 -> step 2 can buy 2
                    4 5 5 -> step 3 can buy 1
                    5 6 6 -> step 4 can buy 1
                    6 7 7 -> step 5 can buy 1
                    7 8 8 -> step 6 can buy 1
                    so total = 3+2+2+1+1+1+1 = 11
                    this can be shown that using prefix sum and math!
                */

                int siz = in.nextInt();
                int max = in.nextInt();
                int[] nums = new int[siz];
                for(int i = 0; i < siz; i++) nums[i] = in.nextInt();
                Arrays.sort(nums);
                long preSum = 0;
                long brought = 0;
                for(int i = 0; i < siz; i++) {
                    preSum += nums[i];
                    if(preSum > max) break;
                    brought += ((max-preSum))/(i+1); // by dividing it with (i+1), we can ensure that we can buy
                    brought++;
                }
                out.println(brought);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

//<--------------------------------------Fast reader----------------------------------------->

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

//<---------------------------------------Fast writer--------------------------------------------->

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("" + object);
        }

        public void println() throws IOException {
            bw.append("\n");
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

// <-------------------------------------------------Some helper methods---------------------------------------->

    public static long fastPow(long b, long e) {
        long res = 1;
        while (e > 0) {
            if (odd(e)) res = (res * b) % MAX;
            b = (b * b) % MAX;
            e = e >> 1;
        }
        return res;
    }

    public static int charToInt(char c) {
        return (c - '0');
    }

    public static char intToChar(int n) {
        return (char) (n + 48);
    }

    public static StringBuffer[] rotate(StringBuffer[] first, StringBuffer[] second) {

        int siz = first.length;

        for(int i = 0; i < siz; i++) second[i].append(first[i]);

        for(int i = 0; i < siz/2; i++) {
            for(int j = 0; j < siz; j++) {
                char t = second[i].charAt(j);
                second[i].setCharAt(j,second[i].charAt(siz-j-1));
                second[i].setCharAt(siz-j-1,t);
            }
        }

        for(int  i = 0; i < siz; i++) {
            for(int j = i; j < siz; j++) {
                char t = second[i].charAt(j);
                second[i].setCharAt(j,second[j].charAt(i));
                second[j].setCharAt(i,t);
            }
        }
        return second;
    }

    public static int[] sieve(int upto) {
        int[] primes = new int[upto + 1];
        Arrays.fill(primes, 1);
        for (int i = 2; i * i <= upto; i++) {
            if (primes[i] == 1)
                for (int j = i * i; j <= upto; j += i) {
                    primes[j] = 0;
                }
        }

        return primes;
    }

    private static long gcd(long a, long b) {
        if (b != 0) return gcd(b, (a % b));
        return a;
    }

    private static int gcd(int a, int b) {
        if (b != 0) return gcd(b, (a % b));
        return a;
    }

    private static long sumN(long siz) {
        return (siz * (siz + 1)) >> 1;
    }

    private static boolean odd(long siz) {
        return (siz & 1) == 1;
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    private static boolean powOf2(long siz) {
        return (siz & (siz - 1)) == 0;
    }

    private static List<Integer> primeFactors(int siz) {

        List<Integer> sizs = new ArrayList<>();
        int[] p = new int[siz + 1];
        p = sieve(siz);

        for (int i = 2; i <= siz; i++) {
            while (siz % i == 0 && p[i] == 1) {
                sizs.add(i);
                siz /= i;
            }
        }
        return sizs;
    }
}

class Pair2 {
    long v1;
    long v2;

    public Pair2(long v1, long v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}

class Pair {
    long val;
    int idx;

    public Pair(long val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}