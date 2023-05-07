public class Main {
    public static void main(String[] args) {

        // Перевірка факторіалу
        int n = 5;
        int factorialRecursive = Factorial.recursionExecute(n);
        int factorialCycle = Factorial.cycleExecute(n);
        assert factorialRecursive == factorialCycle : "Different values";
        System.out.println("Factorial of " + n + ": " + factorialRecursive);

        // Перевірка чисел Фібоначчі
        n = 10;
        int fibonacciRecursive = Fibonacci.recursionExecute(n);
        int fibonacciCycle = Fibonacci.cycleExecute(n);
        assert fibonacciRecursive == fibonacciCycle : "Different values";
        System.out.println("Fibonacci number " + n + ": " + fibonacciRecursive);

        // DigitSumRecursive
        int digitSum = DigitSumRecursive.execute(12345);
        assert digitSum == 15 : "Different values";
        System.out.println("Digit sum of 12345 (recursive): " + digitSum);

        // SumRecursive
        int sumRecursive = SumRecursive.execute(15, 30);
        assert sumRecursive == 45 : "Different values";
        System.out.println("Sum of 15 and 30 (recursive): " + sumRecursive);
    }
}