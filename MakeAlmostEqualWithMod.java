import java.util.*;
import java.io.*;

public class MakeAlmostEqualWithMod {


    private static final long MAX = (long) 1e9 + 7; // max number for MOD

    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            FastWriter out = new FastWriter();

            Comparator<Pair> com = (i,j) -> (int) ((i.val-j.val));
            //String al = "abcdefghijklmnopqrstuvwxyz";
            int testcases = 1;

            testcases = in.nextInt();

            while(testcases-- > 0) {

                /*
                    The reason : there is wonderful concept behind it please read till the end to ensure this concept gets into your head
                    let's first divide the elements in the with 2
                    Then the remainder is either 0 or 1
                    this remainder is based on the number's LSB(least significant bit)
                    if the number is odd, then LSB = 1 // which means the remainder is 1
                    else if number is even, the LSB = 0 // which means the remainder is 0
                    so, if you observe, the remainder contains of only 2 distinct elements, they are 0,1
                    if in case, there are only (even elements or only odd numbers) in the array, when we divide the elements with 2, it gives remainder either 0 or 1
                    so, in that case, you are having only one distinct remainder, which fails the condition of 2 distinct elements
                    so, to make sure that there are only 2 distinct elements
                    you have to increase that 'x' by 2
                    with bit representation
                    lets suppose, there are 4 elements in the array :
                    case 1 : (2 even, 2 odd) {2,4,7,9}
                    then remainders after dividing with 2   | bit representation (only LSB)
                    array elements ->   2 4 7 9             | 2 4 7 9
                    their remainders -> 0 0 1 1             | 0 0 1 1 -> these are the remainders
                    the number of distinct remainders are 2, so the answer is 2 in this case
                    case 2 : (all are even) {2,4,6,8}
                    array elements ->   2 4 6 8
                    their remainders -> 0 0 0 0
                    but the distinct remainders are 1 in this case, i.e
                    so, the LSB when divided by 2 was 0, contains only 1 distinct binary representation of 2 : (00010)
                    so, if you shift that bit by one position, we get : 4 : (00100) // there is change in one bit so, the remainder will also change and make this process until you find the set with size 2
                    so if you now divide it
                    array elements ->   2 4 6 8
                    their remainders -> 2 0 2 0 now, 2 distinct remainders, condition is satisfied
                   */

                int siz = in.nextInt(); // taking size as input

                long[] nums = new long[siz]; // long array is preferred because ai <= 10^17

                for(int i = 0; i < siz; i++) nums[i] = in.nextLong(); // take array elements input

                long x = 2; // initialising the value of x such that the remainder of all elements can have at most 2 distinct elements

                while(true) {

                    Set<Long> set = new HashSet<>(); // to ensure there are 2 distinct remainders

                    for(int i = 0; i < siz; i++) {

                        set.add(nums[i]%x); // adding every remainder no TLE reason : n <= 100 (n = size of array)

                    }

                    if(set.size() == 2) {break;} // the answer is found

                    else x <<= 1; // reason is mentioned above
                }
                out.println(x);
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

    public static long fastPow(long b,long e) {
        long res = 1;
        while(e > 0)
        {
            if(odd(e)) res = (res*b)%MAX;
            b = (b*b)%MAX;
            e = e >>1;
        }
        return res;
    }

    public static int charToInt(char c) {return (c-'0');}

    public static char intToChar(int n) {return (char)(n+48);}

    public static boolean palindrome(long num) {
        String number = Long.toString(num);
        int siz = number.length();
        for(int i = 0; i < siz/2; i++) if(number.charAt(i) != number.charAt((siz-i-1))) return false;
        return true;
    }

    public static int[] sieve(int upto) {
        int[] primes = new int[upto + 1];
        Arrays.fill(primes, 1);
        for (int i = 2; i*i <= upto; i++) {
            if (primes[i] == 1)
                for (int j = i * i; j <= upto; j += i) {
                    primes[j] = 0;
                }
        }

        return primes;
    }

    private static long gcd(long a, long b) {if(b != 0) return gcd(b, (a%b)); return a;}

    private static int gcd(int a, int b)    {if(b != 0) return gcd(b,(a%b)); return a;}

    private static long sumN(long num)      {return (num * (num + 1)) / 2;}

    private static boolean odd(long num)    {return (num & 1) == 1;}

    public static int max(int a, int b)     {return Math.max(a, b);}

    public static long max(long a, long b)  {return Math.max(a, b);}

    private static boolean powOf2(long num) {return (num & (num - 1)) == 0;}

    private static List<Integer> primeFactors(int num) {
        List<Integer> nums = new ArrayList<>();
        int[] p = new int[num+1];
        p = sieve(num);

        for(int i = 2; i <= num; i++) {
            while(num%i == 0 && p[i] == 1) {nums.add(i); num /= i;}
        }
        return nums;
    }
}
class Pair2 {
    long v1;
    long v2;
    public Pair2(long v1,long v2) {
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