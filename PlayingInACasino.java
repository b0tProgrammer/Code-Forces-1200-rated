import java.util.*;
import java.io.*;

public class PlayingInACasino {

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

                        In this, we are given the number of players as input and number of digits on each card.

                        The players are played in pairs, i.e. for example!

                        The first player is plays with 2nd, 3rd, 4th, e.t.c.

                        The second player plays with the 3rd, 4th , 5th, e.t.c.

                        The third player plays with the 4th, 5th, 6th, e.t.c.

                        The total amount of winnings is calculated such a way :

                        the total winning is the cumulated score of each winner

                        the winning of each player is calculated as the absolute difference between...
                        ... the value on the card!

                        The keen observation is each card is being used n-1 times in the game!
                        (n is the number of players)
                 */

                int players = in.nextInt();

                int digitsOnCard = in.nextInt();

                int[][] valuesOnCard = new int[players][digitsOnCard];

                for(int i = 0; i < players; i++) {
                    for (int j = 0; j < digitsOnCard; j++) {
                        valuesOnCard[i][j] = in.nextInt();
                    }
                }

                long ans = 0;

                for(int i = 0; i < digitsOnCard; i++) {

                    long[] pre = new long[players]; // counting the prefix.

                    long[] sorted = new long[players]; // arranging the cards on that place in a sorted order.

                    for(int j = 0; j < players; j++) {
                        sorted[j] = valuesOnCard[j][i]; // pushing the value of digit at the same position into a sorted arrey
                    }

                    Arrays.sort(sorted); // sorting that array why?
                    /*
                        Explanation :
                            let's consider the first test case given :
                            1,7,3 all are at 0 index.
                            the score at that position is :
                            |1-7|+|1-3|+|7-3| = 12
                            let's consider them in this way
                            1   3   7
                               -1  -1 ->  step 1
                            this is when 1st player played with both 2nd and 3rd players
                            1   3   7
                               -1  -1
                                   -3  -> step 2
                           this is when 2 nd player is played with the 3rd player.

                           In this way, we can cover all ways of game!

                           sorting is used to reduce the time complexity or else we have to go for every player for every game!

                           which is approx O(players*players)
                     */

                    pre[0] = sorted[0];

                    for(int j = 1; j < players; j++) {

                        pre[j] = (pre[j-1]+sorted[j]); // prefix sum but why?

                        /*
                            If you look the above example closer

                            we are removing 1 from 3.

                            we are removing 1 and 3 from 7.

                            i.e. the prefix sum before that element.
                         */

                    }
                    for(int j = 1; j < players; j++) {
                        ans += Math.abs(((sorted[j] * j) - pre[j - 1]));
                        // why am I multiplying the sorted[j] with j
                        /*
                            because if you observe :
                            we are subtracting both 1 and 3 from 7
                            This means |1-7|+|3-7|
                            i.e -> |(7-1)+(7-3)|
                                   |14-4|
                                   14 = 7*2 // sorted[j]*j
                                   4 = (1+3) // prefix sum
                         */
                    }
                }
                out.println(ans); // printing the ans.
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
