import java.util.*;
import java.io.*;

public class Main {

    private static final long MAX = (long) 1e9 + 7; // max sizber for MOD

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

//            Comparator<Pair> com = (i, j) -> (int) ((i.val - j.val));

            int testcases = 1;

            testcases = in.nextInt();

            while (testcases-- > 0) {
                /*


                credits :  https://codeforces.com/blog/entry/95525

                If the whole string is equal to c then you don't need to make any operations.

                 In order to find if it is possible with exactly 1 operation, we can pass through every x
                 and count all the letters c that are divisible by x.This takes O(|s|log|s|)
                 time complexity.

                If for some x all its multiples are c then the answer is 1 operation with that x.


                If all the above conditions don't hold you can always make 2
                 operations and make all the elements equal.

                One possible way is with x=|s| and x=|s|−1.


                After the first operation only the last element of s is not c thus if we use x=|s|−1
                since gcd(|s|,|s|−1)=1 then |s| is not divisible by |s|−1,
                and it will become equal to c.


                Time complexity: O(|s|log|s|)
                 */

                int siz = in.nextInt();
                char all = in.next().charAt(0);

                String s = in.next();

                Set<Integer> ans = new HashSet<>();

                boolean allSame = true;

                for(char c : s.toCharArray()) allSame &= (c == all);

                if(!allSame) {  // then we check whether the multiple of any number's index is already equal
                    for(int i = 2; i <= siz; i++) {
                        allSame = true;
                        for(int j = i; j <= siz; j += i) {
                            allSame &= (s.charAt(j-1) == all);
                        }
                        if(allSame) {
                            ans.clear();
                            ans.add(i);
                            break;
                        } else {
                            ans.add(siz);
                            ans.add(siz-1);
                        }
                    }
                }
                out.println(ans.size());
                for (int num : ans) out.print(num+" ");
                if(!ans.isEmpty()) out.println();


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