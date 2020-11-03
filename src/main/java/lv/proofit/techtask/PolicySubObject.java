package lv.proofit.techtask;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Policy sub-objects can be related only to one policy object.
 * @author Anatolijs Gorbunovs
 */
@Component
public class PolicySubObject {
	
	/**
	 * name. Sub-object name e.g. TV.
	 */
	private final String name;
	
	/**
	 * sumInsured. Cost that will be covered by insurance.
	 */
	private final BigDecimal sumInsured;
	
	/**
	 * riskType. Risk type e.g. FIRE, THEFT.
	 */
	private final RiskType riskType;
	
	/**
	 * parent. Policy object that holds this sub-object.
	 */
	private PolicyObject parent;
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getSumInsured() {
		return sumInsured;
	}
	
	public RiskType getRiskType() {
		return riskType;
	}
	
	void setParent(PolicyObject parent) {
		if (this.parent != null) {
			throw new IllegalArgumentException("Cannot parentize policy sub-object more than once.");
		}
		this.parent = parent;
	}
	
	@Autowired(required = false)
	public PolicySubObject(String name, BigDecimal sumInsured, RiskType riskType) {
		if (name == null) {
			throw new IllegalArgumentException("Policy sub-object must have name.");
		}
		if (sumInsured == null) {
			throw new IllegalArgumentException("Policy sub-object must have sum insured.");
		}
		if (riskType == null) {
			throw new IllegalArgumentException("Policy sub-object must have risk type.");
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException("Policy sub-object name must be non-blank.");
		}
		if (sumInsured.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Policy sub-object sum insured must be non-negative.");
		}
		
		this.name = name;
		this.sumInsured = sumInsured;
		this.riskType = riskType;
		this.parent = null;
	}
}
