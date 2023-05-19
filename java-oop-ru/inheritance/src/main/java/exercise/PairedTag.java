package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {
    String tagName;
    Map<String, String> keysValues;
    String tagBody;
    List<Tag> children;

    public PairedTag(String tagName, Map<String, String> keysValues, String tagBody, List<Tag> children) {
        this.tagName = tagName;
        this.keysValues = keysValues;
        this.tagBody = tagBody;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder tagContent = new StringBuilder();
        tagContent.append("<").append(tagName);
        for (Map.Entry<String, String> entry : keysValues.entrySet()) {
            tagContent.append(" ").append(entry.getKey()).append("=")
                    .append("\"").append(entry.getValue()).append("\"");

        }
        tagContent.append(">");
        tagContent.append(tagBody);

        for (Tag tag : children) {
            tagContent.append(tag.toString());
        }
        tagContent.append("</").append(tagName).append(">");
        return tagContent.toString();
    }
}
// END
