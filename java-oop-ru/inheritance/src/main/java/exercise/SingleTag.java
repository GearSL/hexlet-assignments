package exercise;

import java.util.List;
import java.util.Map;

// BEGIN
class SingleTag extends Tag{
    String tagName;
    Map<String, String> keysValues;
    SingleTag(String tagName, Map<String, String> keysValues) {
        this.tagName = tagName;
        this.keysValues = keysValues;
    }

    @Override
    public String toString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("<").append(tagName);
        for (Map.Entry<String, String> entry : keysValues.entrySet()) {
            attributes.append(" ").append(entry.getKey()).append("=")
                    .append("\"").append(entry.getValue()).append("\"");

        }
        attributes.append(">");
        return attributes.toString();
    }
}
// END
