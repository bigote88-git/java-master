import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("con arrays");
        System.out.println("---------------------------------------");
        List<Integer> numbers = Arrays.asList(1,2,3,5,6,8,9,7,8,9);
        numbers
                .sort(new DescendingOrder());

        numbers.subList(0,5).forEach(System.out::println);

        System.out.println("con stream");
        System.out.println("---------------------------------------");
        Arrays.asList(1,2,3,5,6,8,9,7,8,9)
                .stream()
                .distinct()
                .sorted((a, b) -> -a.compareTo(b))
                .limit(5)
                .forEach(System.out::println);

    }
}

class AscdendingOrder implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}

class DescendingOrder implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return -o1.compareTo(o2);
    }
}