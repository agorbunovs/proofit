package lv.proofit.techtask;

import java.math.BigDecimal;

/**
 * Policy sub-objects can be related only to one policy object.
 * @author Anatolijs Gorbunovs
 */
public class PolicySubObject {
	
	/**
	 * name. Sub-object name e.g. TV.
	 */
	private String name;
	
	/**
	 * sumInsured. Cost that will be covered by insurance.
	 */
	private BigDecimal sumInsured;
	
	/**
	 * riskType. Risk type e.g. FIRE, THEFT.
	 */
	private RiskType riskType;
	
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
			throw new IllegalStateException("Cannot parentize policy sub-object more than once.");
		}
		this.parent = parent;
	}
	
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
