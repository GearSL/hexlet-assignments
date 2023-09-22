package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String firstFilePath, String secondFilePath,
                                                       String resultFilePath) {
        CompletableFuture<String> firstFileContent = CompletableFuture.supplyAsync(() -> {
            Path path = Paths.get(firstFilePath).toAbsolutePath().normalize();

            try {
                return Files.readString(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });

        CompletableFuture<String> secondFileContent = CompletableFuture.supplyAsync(() -> {
            Path path = Paths.get(secondFilePath).toAbsolutePath().normalize();

            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> resultContent = firstFileContent
                .thenCombine(secondFileContent, (firstContent, secondContent) -> {

                    Path path = Paths.get(resultFilePath).toAbsolutePath().normalize();
                    if (!Files.exists(path)) {
                        try {
                            Files.createFile(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Files.writeString(path, firstContent, StandardOpenOption.APPEND);
                        Files.writeString(path, secondContent, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return firstContent + secondContent;
                }).exceptionally(ex -> {
                    System.out.println(ex.getMessage());
                    return null;
                });;
        return resultContent;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles(
                "src/main/resources/file10.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/file3.txt");
        System.out.println(result.get());
        // END
    }
}

