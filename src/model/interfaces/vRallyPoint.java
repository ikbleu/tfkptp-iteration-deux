package src.model.interfaces;

import java.util.List;

import src.model.control.Device;

public interface vRallyPoint extends vInstance {
	void units( List< vUnit > l );
	int id();
	String token();
	int workers();
	Device armyDevice();
}
