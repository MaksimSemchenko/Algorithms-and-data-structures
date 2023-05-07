public class Fibonacci {
    public static int recursionExecute(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return recursionExecute(n - 1) + recursionExecute(n - 2);
        }
    }

    public static int cycleExecute(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
