package lv.proofit.techtask;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Policy objects can have multiple sub-objects and can be related only to one policy.
 * @author Anatolijs Gorbunovs
 */
@Component
public class PolicyObject {
	
	/**
	 * name. Object name e.g. A House.
	 */
	private final String name;
	
	/**
	 * subObjects. Collection of none or multiple sub-objects.
	 */
	private final List<PolicySubObject> subObjects;
	
	public String getName() {
		return name;
	}
	
	public List<PolicySubObject> getSubObjects() {
		return subObjects;
	}
	
	@Autowired(required = false)
	public PolicyObject(String name, List<PolicySubObject> subObjects) {
		if (name == null) {
			throw new IllegalArgumentException("Policy object must have name.");
		}
		if (subObjects == null) {
			throw new IllegalArgumentException("Policy object must have none or multiple sub-objects.");
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException("Policy object name must be non-blank.");
		}
			
		this.name = name;
		this.subObjects = Collections.unmodifiableList(subObjects);
		for (PolicySubObject subObject : subObjects) {
			subObject.setParent(this);
		}
	}
}
