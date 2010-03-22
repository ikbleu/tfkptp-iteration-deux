package src.control.fakemodel;

import src.model.control.KeyEventInterpreterBuilder;
import src.model.control.Device;
import src.model.control.Behavior;
import src.util.Hand;
import src.util.HandFactory;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vArgument;
import src.model.interfaces.vGroup;

/**
 *
 * @author kagioglu
 */
class BasicBehaviors implements Device, vGroup {
    private final Hand<Behavior> behavs;
    private final int id;
    BasicBehaviors(HandFactory factory, Ousput ous, int id) {
        this.behavs = factory.make(Behavior.class);
        this.id = id;

        class Behe implements vArgument, Behavior {
            private String put;
            private Ousput ous;
            Behe(String put, Ousput ous) { this.put = put; this.ous = ous; }
            public String token() { return "token:" + this.put; }
            public void accept(ViewVisitor visitor) {
                visitor.visitArgument(this);
            }
            public String comparable() { return "comp:" + this.put; }
            public String meaning() { return "mean:" + this.put; }
            public void execute() {
                this.ous.predict(put);
            }
        }

        this.behavs.add(new Behe(this.id + ":a", ous));
        this.behavs.add(new Behe(this.id + ":e", ous));
        this.behavs.add(new Behe(this.id + ":d", ous));
        this.behavs.add(new Behe(this.id + ":c", ous));
        this.behavs.add(new Behe(this.id + ":b", ous));
        this.behavs.add(new Behe(this.id + ":f", ous));
    }
    public String token() { return "BBtoken:" + this.id; }
    public void accept(ViewVisitor visitor) { visitor.visitGroup(this); }
    public String comparable() { return "BBcomp:" + this.id; }
    public String context() { return "BBcont:" + this.id; }
    public String meaning() { return "BBmean:" + this.id; }
    public void direct(KeyEventInterpreterBuilder builder) {
        builder.behaviors(this.behavs.spawnLens());
    }
}
