/*
 * file: RandomChooser.java
 */

package src.util;

import java.util.TreeMap;
import java.util.Random;

/**
 * Chooses a semi-random element placed inside with specified relative occurances.
 *
 * @author Christopher Dudley
 */
public class RandomChooser<E>
{
    private TreeMap<Integer, E> map;
    private int maxVal;

    private Random rand;

    /**
     * Creates a new random chooser with no elements.
     */
    public RandomChooser()
    {
        map = new TreeMap<Integer, E>();
        maxVal = 0;

        rand = new Random(System.currentTimeMillis());
    }

    /**
     * Adds the specified element to the chooser with the specified relative
     * occurance. Elements with the same occurance have the same chance of
     * being choosen, those with higher or lower occurances have a proprotionally
     * higher or lower chance of being chosen.
     *
     * @param newElement the element being added.
     * @param occurance the relative occurance of the element.
     */
    public void add(E newElement, int occurance)
    {
        int nextKey = maxVal + occurance;
        map.put(nextKey, newElement);
        maxVal = nextKey;
    }

    /**
     * Chooses a semi-random element, with elements with a higher occurance
     * having a higher chance of being chosen.
     *
     * @return the chosen element.
     */
    public E get()
    {
        if(!map.isEmpty())
        {
            int key = rand.nextInt(maxVal);
            return map.higherEntry(key).getValue();
        }
        else
        {
            return null;
        }
    }
}
