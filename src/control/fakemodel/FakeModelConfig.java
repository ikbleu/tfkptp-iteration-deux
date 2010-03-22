package src.control.fakemodel;

import src.model.control.Device;
import src.util.Lens;
import src.util.Hand;
import src.util.HandFactory;
import src.util.handv1.HandFactoryImp;
import java.util.List;

/**
 *
 * @author kagioglu
 */
public class FakeModelConfig {
    public static Lens<Device> make(Ousput ous, List<Device> devis) {
        HandFactory factory = new HandFactoryImp();
        Hand handy = factory.make(Device.class);
        devis.add(new BasicDevices(factory, ous, 4));
        handy.add(devis.get(0));
        devis.add(new BasicDevices(factory, ous, 5));
        handy.add(devis.get(1));
        devis.add(new BasicDevices(factory, ous, 6));
        handy.add(devis.get(2));
        return handy.spawnLens();
    }
}
