package test;
//Project pack
import Utils.Logger;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
public class TestLoader {
    private static Logger logger = new Logger("Tester");
    private static String getProjectFolder() {
        return System.getProperty("user.dir");
    }
    public static List<String> loadCommand(String testFileName) {
        List<String> lines = null;
        String testFolderName = "test";
        String projectSrc = "src";
        String absolutePath = getProjectFolder()+ "/" + projectSrc +  "/" + testFileName;
        try {

            lines = Files.readAllLines(Paths.get(absolutePath));
        } catch (IOException e) {
            logger.Log(Logger.status.ERROR, "Cant open file: " + absolutePath);
            throw new RuntimeException(e);
        }
        return lines;
    }
}