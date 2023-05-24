package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void main(String[] args) {

    }

    public static void save(Path path, Car car) throws IOException {
        String serializedCar = car.serialize();
        Files.writeString(path.toAbsolutePath().normalize(), serializedCar, StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws IOException {
        String carContent = Files.readString(path);
        return Car.unserialize(carContent);
    }
}
// END
