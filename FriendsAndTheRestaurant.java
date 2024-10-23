import java.util.*;
import java.io.*;

public class Main {

    private static final long MAX = (long) 1e9 + 7; // max number for MOD

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

            Comparator<Pair> com = (i, j) -> (int) ((i.val - j.val));
            //String al = "abcdefghijklmnopqrstuvwxyz";
            int testcases = 1;

            testcases = in.nextInt();

            while (testcases-- > 0) {

                /*
                    In this problem, you have to calculate the maximum number of days.
                    So, the team size should be minimum i.e. 2
                    So consider a two pointer approach.
                    Move the pointers from end to the middle!
                    with some conditions!
                    variable names are taken as per the question, except the size.
               */

                int siz = in.nextInt();
                int[] x = new int[siz];
                int[] y = new int[siz];


                for(int i = 0; i < siz; i++) x[i] = in.nextInt();
                for(int i = 0; i < siz; i++) y[i] = in.nextInt();

                long[] difference = new long[siz]; // store's the difference between yi-xi

                // we can form a pair iff yi-xi == 0 || yi-xi > 0 && there is a compensating value which can make the value positive inclusive 0;

                for(int i = 0; i < siz; i++) {
                    difference[i] = y[i]-x[i];
                }
                
                int left = 0; // initialising the left and right pointer
                int right = siz-1;

                int maxDays = 0;

                Arrays.sort(difference);

                while(left < right) {
                    if(difference[left]+difference[right] >= 0) {
                        left++; right--; maxDays++;
                    }
                    else left++;
                }

                out.println(maxDays);

            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

//<----------------------------Fast reader-------------------------------------->

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

//<-----------------------Fast writer------------------------------------------>

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

// <------------------------------Some helper methods----------------------------->

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

    public static boolean palindrome(long num) {
        String number = Long.toString(num);
        int siz = number.length();
        for (int i = 0; i < siz / 2; i++) if (number.charAt(i) != number.charAt((siz - i - 1))) return false;
        return true;
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

    private static long sumN(long num) {
        return (num * (num + 1)) >> 1;
    }

    private static boolean odd(long num) {
        return (num & 1) == 1;
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    private static boolean powOf2(long num) {
        return (num & (num - 1)) == 0;
    }

    private static List<Integer> primeFactors(int num) {

        List<Integer> nums = new ArrayList<>();
        int[] p = new int[num + 1];
        p = sieve(num);

        for (int i = 2; i <= num; i++) {
            while (num % i == 0 && p[i] == 1) {
                nums.add(i);
                num /= i;
            }
        }
        return nums;
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
