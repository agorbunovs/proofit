package lv.proofit.techtask;

import java.math.BigDecimal;

/**
 *
 * @author Anatolijs Gorbunovs
 */
public interface SubPremiumCalculator {
	
	public BigDecimal calculate(BigDecimal sumInsuredSub);
	
	public RiskType getRiskType();
}
