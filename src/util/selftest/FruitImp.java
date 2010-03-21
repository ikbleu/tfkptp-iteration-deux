package src.util.selftest;

import src.util.HasComparable;

/**
 *
 * @author kagioglu
 */
public class FruitImp implements Fruit, HasComparable {
    private final String name;
    FruitImp(String name) { this.name = name; }
    public String comparable() { return this.name; }
}
