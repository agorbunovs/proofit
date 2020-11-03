package lv.proofit.techtask;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Policy can have multiple policy objects and each policy object can have multiple sub-objects.
 * @author Anatolijs Gorbunovs
 */
@Component
public class Policy {
	
	/**
	 * number. Policy number e.g. LV20-02-100000-5.
	 */
	private final String number;
	
	/**
	 * status. Policy status e.g. REGISTERED, APPROVED.
	 */
	private final PolicyStatus status;
	
	/**
	 * objects. Policy objects, immutable collection of one or multiple objects.
	 */
	private final List<PolicyObject> objects;
	
	public String getNumber() {
		return number;
	}
	
	public PolicyStatus getStatus() {
		return status;
	}
	
	public List<PolicyObject> getObjects() {
		return objects;
	}
	
	@Autowired(required = false)
	public Policy(String number, PolicyStatus status, List<PolicyObject> objects) {
		if (number == null) {
			throw new IllegalArgumentException("Policy number must be specified.");
		}
		if (status == null) {
			throw new IllegalArgumentException("Policy status must be specified.");
		}
		if (objects == null) {
			throw new IllegalArgumentException("Policy objects must be specified.");
		}
		if (number.isEmpty()) {
			throw new IllegalArgumentException("Policy number cannot be empty.");
		}
		if (objects.isEmpty()) {
			throw new IllegalArgumentException("Policy objects cannot be empty");
		}
		
		this.number = number;
		this.status = status;
		this.objects = Collections.unmodifiableList(objects);
	}
}
