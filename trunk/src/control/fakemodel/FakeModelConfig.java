package src.control.fakemodel;

import src.model.control.Device;
import src.util.Lens;
import src.util.Hand;
import src.util.HandFactory;
import src.util.handv1.HandFactoryImp;

/**
 *
 * @author kagioglu
 */
public class FakeModelConfig {
    public static Lens<Device> make(Ousput ous) {
        HandFactory factory = new HandFactoryImp();
        Hand<Device> handy = factory.make(Device.class);
        handy.add(new BasicDevices(factory, ous, 4));
        handy.add(new BasicDevices(factory, ous, 5));
        handy.add(new BasicDevices(factory, ous, 6));
        return handy.spawnLens();
    }
}
