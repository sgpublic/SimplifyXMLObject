import com.sgpublic.xml.helper.StringMatcher;

import java.util.concurrent.atomic.AtomicInteger;

public class LocalTest {
    public static void main(String[] args) {
        String xml = Test.getString();
        StringMatcher matcher = new StringMatcher("<", xml);

        AtomicInteger ends = new AtomicInteger(0);
        matcher.matches((start, end, group, index) -> {
            System.out.println(ends.get() + ", " + start + ", " + xml.substring(ends.get(), start));
            ends.set(end - 1);
        });

    }
}
