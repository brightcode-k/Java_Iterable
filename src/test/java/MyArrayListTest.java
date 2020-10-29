import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {
    private final Map<Object, List<?>> testData;
    private MyIterator iterator;

    @BeforeEach
    public void setUp() {
        iterator = new MyIterator();
    }


    public MyArrayListTest() {
        testData = new HashMap<>();
        testData.put(Integer.class, Arrays.asList(1, 2, 3, 4, 5));
        testData.put(Boolean.class, Arrays.asList(true, false, true));
        testData.put(String.class, Arrays.asList("abc", "abb", "acc", "dab", "dbc"));
    }

    @Test
    public void test_filter_for_int() {
       Predicate<Integer> evenNumber = integer -> integer%2 == 0;
        List<Integer> expected = Arrays.asList(2, 4);
        List<Integer> actual = (List<Integer>)
                MyIterator.filter((List<Integer>) testData.get(Integer.class), evenNumber);

        compareTwoLists(expected, actual);
    }

    private <T> void compareTwoLists(List<T> first, List<T> second) {
        assertTrue(first.size() == second.size() && first.containsAll(second) && second.containsAll(first));
    }


    @Test
    void filter_string() {
        List<String> expected = new ArrayList<>();
        List<String> testdata = new ArrayList<>();
        expected.add("aaa");
        testdata.add("aaa");
        testdata.add("acacac");
        testdata.add("ccccc");
        testdata.add("gggggg");

        Iterable<String> actual = MyIterator.filter(testdata, string -> string.length() < 4);

        assertEquals(expected, actual);
    }

    @Test
    void test_filter_for_double() {
        List<Double> expected = new ArrayList<>();
        List<Double> testdata = new ArrayList<>();
        expected.add(2.3);
        expected.add(4.9);
        testdata.add(2.3);
        testdata.add(4.9);
        testdata.add(6.1);

        Iterable<Double> actual = MyIterator.filter(testdata, doublePrecision -> doublePrecision < 5);

        assertEquals(expected, actual);
    }

@Test
    public void test_transform_for_string() {
        Function<String, String> newStrings = s -> s + "b";
        List<String> expected = Arrays.asList("abcb", "abbb", "accb", "dabb", "dbcb");
        List<String> actual = (List<String>)
                MyIterator.transform((List<String>) testData.get(String.class), newStrings);

        compareTwoLists(expected, actual);
    }

    @Test
    void test_transform_for_double() {
        List<Double> expected = new ArrayList<>();
        List<Double> testdata = new ArrayList<>();
        expected.add(1.7);
        expected.add(2.4);
        expected.add(2.5);
        testdata.add(3.4);
        testdata.add(4.8);
        testdata.add(5.0);

        Iterable<Double> actual = MyIterator.transform(testdata, doubles -> doubles / 2);

        assertEquals(expected, actual);
    }

    @Test
    void test_transform_for_boolean() {
        Function<Boolean, Boolean> inverse = b -> !b;
        List<Boolean> expected = Arrays.asList(false, true, false);
        List<Boolean> actual = (List<Boolean>)
                MyIterator.transform((List<Boolean>) testData.get(Boolean.class), inverse);

        compareTwoLists(expected, actual);
    }
}
