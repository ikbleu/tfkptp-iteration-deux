package src.control.fakemodel;

import src.model.control.KeyEventInterpreterBuilder;
import src.model.control.Device;
import src.util.Hand;
import src.util.HandFactory;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vType;

/**
 *
 * @author kagioglu
 */
class BasicDevices implements vType, Device {
    private final Hand<Device> behavs;
    private final int id;
    BasicDevices(HandFactory factory, Ousput ous, int id) {
        this.behavs = factory.make(Device.class);
        this.id = id;

        this.behavs.add(new BasicBehaviors(factory, ous, 1 + this.id * 10));
        this.behavs.add(new BasicBehaviors(factory, ous, 2 + this.id * 10));
        this.behavs.add(new BasicBehaviors(factory, ous, 3 + this.id * 10));
    }
    public String token() { return "DDtoken::" + this.id; }
    public void accept(ViewVisitor visitor) { visitor.visitType(this); }
    public String comparable() { return "DDcomp::" + this.id; }
    public String context() { return "DDcont::" + this.id; }
    public String meaning() { return "DDmean::" + this.id; }
    public void direct(KeyEventInterpreterBuilder builder) {
        builder.devices(this.behavs.spawnLens());
    }
}
