package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        try {
            maxThread.start();
            maxThread.join();
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        MinThread minThread = new MinThread(numbers);
        try {
            minThread.start();
            minThread.join();
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }


        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("max", maxThread.getResult());
        hashMap.put("min", minThread.getResult());

        return hashMap;
    }
    // END
}
