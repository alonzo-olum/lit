import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.OptionalDouble;

public class Average {
    private static void average(int[] nums) {
        IntStream
            .of(nums)
            .average()
            .ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        int[] nums = Arrays
            .stream(args)
            .mapToInt(Integer::parseInt)
	    .toArray();
	Average.average(nums);
    }
}
