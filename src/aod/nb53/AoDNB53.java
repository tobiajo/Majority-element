package aod.nb53;

/**
 *
 * @author Tobias Johansson <tobias@johansson.xyz>
 */
public class AoDNB53 {

    private static final int MILLION = 1000000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] a = new int[MILLION];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (2 * Math.random());
        }
        int min = minDaC(a);
        int max = maxDaC(a);
        long start = System.currentTimeMillis();
        int maj = majorityDaC(a); // get majority with Divide and Conquer
        long elapsed = System.currentTimeMillis() - start;

        //System.out.println(java.util.Arrays.toString(a));
        System.out.println("Min: " + (char)('A'+min) );
        System.out.println("Max: " + (char)('A'+max) );
        System.out.println("Majority: " + (char)('A'+maj) 
                + " (" + elapsed + " ms)");
    }

    /**
     * Find max int Divide and Conquer.
     */
    public static int minDaC(int[] a) {
        return minDaC(a, 0, a.length - 1);
    }

    private static int minDaC(int[] a, int left, int right) {
        int size = right - left + 1;
        /* The base case */
        if (size == 1) return a[left];

        int mid = (left + right) / 2;
        int aLeft = minDaC(a, left, mid);
        int aRight = minDaC(a, mid + 1, right);

        return Math.min(aLeft, aRight);
    }

    /**
     * Find max int Divide and Conquer.
     */
    public static int maxDaC(int[] a) {
        return maxDaC(a, 0, a.length - 1);
    }

    private static int maxDaC(int[] a, int left, int right) {
        int size = right - left + 1;
        /* The base case */
        if (size == 1) return a[left];

        int mid = (left + right) / 2;
        int aLeft = maxDaC(a, left, mid);
        int aRight = maxDaC(a, mid + 1, right);

        return Math.max(aLeft, aRight);
    }

    /**
     * Get majority with Divide and Conquer.
     */
    public static int majorityDaC(int[] a) {
        return majorityDaC(a, 0, a.length - 1);
    }

    private static int majorityDaC(int[] a, int left, int right) {
        int size = right - left + 1;
        /* The base case */
        if (size == 1) return a[left];

        int mid = (left + right) / 2;
        int aLeft = majorityDaC(a, left, mid);
        int aRight = majorityDaC(a, mid + 1, right);
        
        if (aLeft == aRight) return aLeft; // optimering

        int leftCount = frequency(a, left, right, aLeft);
        int rightCount = frequency(a, left, right, aRight);

        if (leftCount > size / 2) return aLeft;
        if (rightCount > size / 2) return aRight;

        return -1;
    }

    private static int frequency(int[] a, int left, int right, int comp) {
        int counter = 0;
        for (int i = left; i <= right; i++) if (a[i] == comp) counter++;
        return counter;
    }
}
