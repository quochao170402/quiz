import java.util.List;

import com.group2.models.Category;

/**
 * Hello world!
 */

public class App {
    public static void main(String[] args) {
        List.of(
                new Category("name"),
                new Category("name"),
                new Category("name"),
                new Category("name"),
                new Category("name"),
                new Category("name")).forEach(System.out::println);

    }
}
