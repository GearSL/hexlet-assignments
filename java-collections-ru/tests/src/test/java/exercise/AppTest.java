package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AppTest {
    private static List<Integer> testedList = new ArrayList<>();

    @BeforeAll
    static void createTestData () {
        testedList.add(1);
        testedList.add(2);
        testedList.add(3);
        testedList.add(4);
        testedList.add(5);

    }

    @Test
    void testTake() {
        // BEGIN
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(1);
        expectedList.add(2);

        Assertions.assertThat(App.take(testedList, 2)).isEqualTo(expectedList);
        // END
    }
}
