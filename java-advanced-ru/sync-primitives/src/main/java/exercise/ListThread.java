package exercise;

import java.util.List;
import java.util.Random;

// BEGIN
class ListThread extends Thread {
    SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        Random rn = new Random();
        for (int i = 0; i < 1000; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int randomInt = rn.nextInt(1, 100);
            safetyList.add(randomInt);
        }
    }
}
// END
