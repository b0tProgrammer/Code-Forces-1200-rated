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

//            testcases = in.nextInt();

            while (testcases-- > 0) {

                /*
                    Credits : https://codeforces.com/contest/1679/submission/184642709
                    Thanks for your wonderful code @ Gaurish Baliga.
                 */

                int siz = in.nextInt(); // size of the array.
                int q = in.nextInt(); // number of queries
                int[] nums = new int[siz];
                long sum = 0;
                for(int i = 0; i < siz; i++) {
                    nums[i] = in.nextInt();
                    sum += nums[i];
                }
                // taking queries input...
                int[] timerArray = new int[siz];
                int resetValue = -1;
                int resetTime = -1;
                for(int i = 1; i <= q; i++) {
                    int type = in.nextInt();
                    if(type == 1) {
                        int pos = in.nextInt()-1;
                        int value = in.nextInt();
                        // which checks whether it appeared after the type 2.
                        if(resetTime > timerArray[pos]) nums[pos] = resetValue;
                        // then update the sum
                        sum += (value-nums[pos]);
                        // update the position, value at that index.
                        nums[pos] = value; timerArray[pos] = i;
                    } else {
                        int value = in.nextInt();
                        sum = (long) siz *value;
                        resetValue = value; resetTime = i;
                    }
                    out.println(sum);
                }
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