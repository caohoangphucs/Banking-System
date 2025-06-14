package Utils;
import java.util.Random;
public class math {
    public static Random rand = new Random();
    public static int getRandomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}