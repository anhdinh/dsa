package dynamicProgramming;

public class ClimbingStairs {

    private ClimbingStairs(){
        throw new UnsupportedOperationException("Lớp tiện ích, không cho phép khởi tạo");
    }
    public static void main(String[] args) {
        System.out.println(bottomUp(47));
    }

    public static int bottomUp(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format("n không được là số âm n là %d",n));
        }
        if (n <= 1) {
            return 1;
        }
        int prev = 1;
        int current = 1;

        try {
            for (int i = 2; i <= n; i++) {
                int next = Math.addExact(prev, current);
                prev = current;
                current = next;
            }
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException(String.format("n:%d quá lớn, không vượt quá 45",n), ex);
        }
        return current;
    }
}
