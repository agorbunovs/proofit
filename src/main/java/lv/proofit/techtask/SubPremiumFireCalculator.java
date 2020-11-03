package lv.proofit.techtask;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anatolijs Gorbunovs
 */
@Service
public class SubPremiumFireCalculator implements SubPremiumCalculator {

	private static final BigDecimal THRESHOLD_SUM_FIRE = new BigDecimal("100.00");
	
	private static final BigDecimal COEFFICIENT_FIRE_LOW = new BigDecimal("0.014");
	
	private static final BigDecimal COEFFICIENT_FIRE_HIGH = new BigDecimal("0.024");
	
	@Override
	public BigDecimal calculate(BigDecimal sumInsuredFire) {
		if (sumInsuredFire.compareTo(THRESHOLD_SUM_FIRE) > 0) {
			return sumInsuredFire.multiply(COEFFICIENT_FIRE_HIGH);
		} else {
			return sumInsuredFire.multiply(COEFFICIENT_FIRE_LOW);
		}
	}

	@Override
	public RiskType getRiskType() {
		return RiskType.FIRE;
	}
	
}
