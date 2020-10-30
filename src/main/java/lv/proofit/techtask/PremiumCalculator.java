package lv.proofit.techtask;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculator that performs the main task.
 * @author Anatolijs Gorbunovs
 */
public class PremiumCalculator {
	
	private Map<RiskType, SubPremiumCalculator> subCalculators;
	
	public PremiumCalculator(List<SubPremiumCalculator> subCalculators) {
		Map<RiskType, SubPremiumCalculator> calculators = new HashMap<>();
		for (SubPremiumCalculator subCalculator : subCalculators) {
			if (subCalculator.getRiskType() == null) {
				throw new IllegalArgumentException("Sub-calculator risk type must be set.");
			}
			calculators.put(subCalculator.getRiskType(), subCalculator);
		}
		
		this.subCalculators = Collections.unmodifiableMap(calculators);
	}
	
	public BigDecimal calculate(Policy policy) {
		// End result.
		BigDecimal result = BigDecimal.ZERO;
		
		// Stores insured sub-sums grouped by risk type.
		Map<RiskType, BigDecimal> sumInsuredSub = new HashMap<>();
		
		for (RiskType riskType : subCalculators.keySet()) {
			sumInsuredSub.put(riskType, BigDecimal.ZERO);
		}
		
		// Calculate insured sub-sums.
		for (PolicyObject object : policy.getObjects()) {
			for (PolicySubObject subObject : object.getSubObjects()) {
				if (!sumInsuredSub.containsKey(subObject.getRiskType())) {
					throw new IllegalArgumentException(String.format(
							"Risk type %s is not supported.",
							subObject.getRiskType().toString()));
				}
				BigDecimal subSum = sumInsuredSub.get(subObject.getRiskType());
				sumInsuredSub.put(subObject.getRiskType(), subSum.add(subObject.getSumInsured()));
			}
		}
		
		// Apply individual rules for each risk type and sum up the result.
		for (RiskType riskType : subCalculators.keySet()) {
			BigDecimal subSum = sumInsuredSub.get(riskType);
			result = result.add(subCalculators.get(riskType).calculate(subSum));
		}
		
		return result;
	}
}
