package lv.proofit.techtask;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anatolijs Gorbunovs
 */
@Service
public class SubPremiumTheftCalculator implements SubPremiumCalculator {

	private static final BigDecimal THRESHOLD_SUM_THEFT = new BigDecimal("15.00");
	
	private static final BigDecimal COEFFICIENT_THEFT_LOW = new BigDecimal("0.11");
	
	private static final BigDecimal COEFFICIENT_THEFT_HIGH = new BigDecimal("0.05");
	
	@Override
	public BigDecimal calculate(BigDecimal sumInsuredTheft) {
		if (sumInsuredTheft.compareTo(THRESHOLD_SUM_THEFT) < 0) {
			return sumInsuredTheft.multiply(COEFFICIENT_THEFT_LOW);
		} else {
			return sumInsuredTheft.multiply(COEFFICIENT_THEFT_HIGH);
		}
	}

	@Override
	public RiskType getRiskType() {
		return RiskType.THEFT;
	}
	
}
