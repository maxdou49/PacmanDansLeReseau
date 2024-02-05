/*
 * Un générateur d'aléatoire
 */
package model.Random;
public abstract class RandomGenerator {
    public abstract int next();

    public int next(int bound)
    {
        return Math.abs(next()) % bound;
    }
}
