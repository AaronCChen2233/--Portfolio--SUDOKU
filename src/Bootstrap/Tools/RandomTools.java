package Bootstrap.Tools;

import java.util.Random;

public class RandomTools {
    static Random r = new Random();

    public static int getRandomIntbetween (int max, int min){
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean getRandomBooleanByPercent(int percent) {
        Random r = new Random();
        return ((r.nextInt(100) + 1) <= percent);
    }
}
