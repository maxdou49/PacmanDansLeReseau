/*
 * L'aléatoire de base de Java
 */

package model.Random;
import java.util.Random;

public class BasicRandom extends RandomGenerator {

    protected Random rand;
    public BasicRandom(long seed)
    {
        rand = new Random(seed);
    }

    public int next()
    {
        return rand.nextInt();
    }
}
