package src.util.selftest;

import src.util.handv1.HandFactoryImp;
import src.util.Hand;
import src.util.HandFactory;

/**
 *
 * @author kagioglu
 */
class AddRemove implements Test {
    public void test() {
        HandFactory handfactory = new HandFactoryImp();
        Hand<FruitImp> hand = handfactory.make(FruitImp.class);
        AddRemove.assertNull(hand.add(new FruitImp("apple")));
        AddRemove.same("apple", hand.add(new FruitImp("apple")).comparable());
        AddRemove.same("apple", hand.remove(new FruitImp("apple")).comparable());
        AddRemove.assertNull(hand.remove(new FruitImp("apple")));
    }

    private static void same(String a, String b) {
        if(a.compareTo(b) != 0) { throw new RuntimeException("not same"); }
    }

    private static void assertNull(Object o) {
        if(o != null) { throw new RuntimeException("not same"); }
    }
}
