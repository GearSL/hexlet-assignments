package exercise;

import jdk.jshell.execution.Util;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage{
    String path;
    HashMap<String, String> storage;
    FileKV(String path, Map<String, String> storage) {
        this.path = path;
        this.storage = new HashMap<>(storage);

        Utils.writeFile(path, Utils.serialize(this.storage));
    }
    @Override
    public void set(String key, String value) {
        HashMap<String, String> unserializedStorage = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        unserializedStorage.put(key, value);
        Utils.writeFile(path, Utils.serialize(unserializedStorage));
    }

    @Override
    public void unset(String key) {
        HashMap<String, String> unserializedStorage = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        unserializedStorage.remove(key);
        Utils.writeFile(path, Utils.serialize(unserializedStorage));
    }

    @Override
    public String get(String key, String defaultValue) {
        HashMap<String, String> unserializedStorage = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        if(unserializedStorage.get(key) == null) {
            return defaultValue;
        }
        return unserializedStorage.get(key);
    }

    @Override
    public Map<String, String> toMap() {
        HashMap<String, String> unserializedStorage = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        return new HashMap<>(unserializedStorage);
    }
}
// END
