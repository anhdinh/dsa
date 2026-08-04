import java.util.List;

public class Main {
    public static void main(String[] args) {
       List.of(1,2,3,4,5,6,7,8).parallelStream()
               .peek(n -> System.out.println(Thread.currentThread().getName() + " → " + n))
               .map(n -> n * 2)
               .toList();
    }
}
