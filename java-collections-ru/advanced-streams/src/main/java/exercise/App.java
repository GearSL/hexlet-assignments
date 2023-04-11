package exercise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.CollationElementIterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {

    public static String getForwardedVariables(String config) {

        Pattern pattern = Pattern.compile("environment=\"(.*?)\"");
        Matcher matcher = pattern.matcher(config);

        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group(1)).append(",");
        }

        System.out.println(stringBuilder.toString());

        String splitedConfig = Arrays.stream(stringBuilder.toString().split(",")).toList()
                .stream().filter(string -> string.startsWith("X_FORWARDED_"))
                .map(string -> string.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
        return splitedConfig;
    }
}
//END
