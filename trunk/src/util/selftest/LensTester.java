package src.util.selftest;

import src.util.handv1.HandFactoryImp;
import src.util.Hand;
import src.util.HandFactory;
import src.util.Lens;
import src.util.DataVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kagioglu
 */
class LensTester implements Test {
    public void test() {
        HandFactory handfactory = new HandFactoryImp();
        Hand<FruitImp> hand = handfactory.make(FruitImp.class);
        List<Fruit> fruitlist = new ArrayList<Fruit>();
        FruitImp a = new FruitImp("a");
        FruitImp b = new FruitImp("b"); fruitlist.add(b);
        FruitImp c = new FruitImp("c"); fruitlist.add(c);
        FruitImp d = new FruitImp("d"); fruitlist.add(d);
        FruitImp e = new FruitImp("e"); fruitlist.add(e);
        FruitImp f = new FruitImp("f"); fruitlist.add(f);
        fruitlist.add(a);
        hand.add(a);
        hand.add(e);
        hand.add(d);
        hand.add(c);
        hand.add(b);
        hand.add(f);
        Lens<FruitImp> lens = hand.spawnLens();
        for(Fruit fruit : fruitlist) { lens.next(new AddDataVisitor(fruit)); }
        hand.remove(a);
        hand.remove(b);
        hand.remove(c);
        hand.remove(d);
        hand.remove(e);
        hand.remove(f);
        for(int i = 0; i < fruitlist.size(); i++) { lens.next(new RemoveDataVisitor()); }
    }

    private static class AddDataVisitor<T> implements DataVisitor<T> {
        private T expected;
        AddDataVisitor(T value) { this.expected = value; }
        public void found(T item) {
            if(this.expected != item) { throw new RuntimeException("not same!"); }
        }
        public void missing() { throw new RuntimeException("fail!"); }
    }

    private static class RemoveDataVisitor<T> implements DataVisitor<T> {
        public void found(T item) {
            throw new RuntimeException("should not have found!");
        }
        public void missing() {}
    }
}
