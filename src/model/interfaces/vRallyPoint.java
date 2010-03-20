package src.model.interfaces;

import java.util.List;

public interface vRallyPoint {
	List< vUnit > units();
	int id();
	String token();
	int workers();
}
