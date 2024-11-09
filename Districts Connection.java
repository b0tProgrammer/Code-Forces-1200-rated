import java.util.*;
import java.io.*;

public class Main {

    private static final long MAX = (long) 1e9 + 7; // max size for MOD

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

//            Comparator<Pair> com = (i, j) -> (int) ((i.val - j.val));

            int testcases = 1;

            testcases = in.nextInt();

            while (testcases-- > 0) {
                /*
                    District's connections..
                    The construction of road fails iff there is only one type of gang or else we can lay the road
                 */

                Set<Integer> set = new HashSet<>();
                int siz = in.nextInt();
                Pair[] ans = new Pair[siz];
                Map<Integer,Integer> map = new HashMap<>();

                for(int i = 0; i < siz; i++) {
                    int x = in.nextInt();
                    set.add(x);
                    ans[i] = new Pair(x,(i+1));
                    map.put(x,map.getOrDefault(x,0)+1);
                }
                if(set.size() == 1) {
                    out.println("NO");
                    continue;
                }
                out.println("YES");
                Comparator<Pair> com = (o1, o2) -> {
                    int freq1 = map.get(o1.val);
                    int freq2 = map.get(o2.val);
                    return Integer.compare(freq1, freq2); // Ascending order based on frequency
                };

                Arrays.sort(ans,com); // sort according to the freq
                int i = 1;
                int num = ans[0].val;
                int idx = -1;

                for(int j = 1; j < siz; j++) {
                    if(ans[0].val != ans[j].val) {
                        idx = ans[j].idx;
                        out.println(ans[0].idx + " " + ans[j].idx);
                    }
                }
                for(int j = 1; j < siz; j++) {
                    if(ans[j].val == ans[0].val)
                        out.println(idx+" "+ans[j].idx);
                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static int makePalindrome(String s, char c) {
        int count = 0;
        int l = 0;
        int r = s.length()-1;
        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) {
                if(s.charAt(l) == c) {
                    l++; count++;
                }
                else if (s.charAt(r) == c) {
                    r--; count++;
                }
                else return -1;
            }
            else {
                l++; r--;
            }
        }
        return count;
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
    int val;
    int idx;

    public Pair(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}