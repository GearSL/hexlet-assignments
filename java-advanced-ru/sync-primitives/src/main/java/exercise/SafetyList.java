package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private final List<Integer> numberList = new ArrayList<>();

    public synchronized void add(Integer number) {
        numberList.add(number);
    }

    public Integer get(Integer index) {
        return numberList.get(index);
    }

    public Integer getSize() {
        return numberList.size();
    }
    // END
}
