package services;
import model.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    //private static final String APPLICATION_FOLDER = "Hop-On";
    private static final String USER_FOLDER = System.getProperty("user.dir");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER);
    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get("", path));
    }
 public static void main(String[] argv)
 {
     System.out.println(getPathToFile("src/main/resources/jsonFileFlight.json"));
 }
}