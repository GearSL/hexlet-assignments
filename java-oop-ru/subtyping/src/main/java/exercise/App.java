package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void main(String[] args) {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        // Получение значения по ключу
        storage.get("key", "default");
        storage.set("key2", "value2");
        System.out.println(storage.toMap());
    }

    public static KeyValueStorage swapKeyValue(KeyValueStorage storage) {
        Map<String, String> storageMap = storage.toMap();
        for (Entry<String, String> entry : storageMap.entrySet()) {
            storage.unset(entry.getKey());
            storage.set(entry.getValue(), entry.getKey());
        }
        return storage;
    }
}
// END
