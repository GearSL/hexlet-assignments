package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    HashMap<String, String> storage;
    InMemoryKV(Map<String, String> storage) {
        this.storage = new HashMap<>(storage);
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        if(storage.get(key) == null) {
            return defaultValue;
        }
        return storage.get(key);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }
}
// END
