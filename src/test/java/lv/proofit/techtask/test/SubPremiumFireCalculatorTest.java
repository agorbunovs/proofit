package lv.proofit.techtask.test;

import java.math.BigDecimal;
import lv.proofit.techtask.RiskType;
import lv.proofit.techtask.SubPremiumFireCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Anatolijs Gorbunovs
 */
public class SubPremiumFireCalculatorTest {
	@Test
	public void testGetRiskType() {
		SubPremiumFireCalculator calculator = new SubPremiumFireCalculator();
		Assertions.assertEquals(RiskType.FIRE, calculator.getRiskType(), "Risk type mismatch.");
	}
	
	@Test
	public void testCalculate() {
		SubPremiumFireCalculator calculator = new SubPremiumFireCalculator();
		Assertions.assertEquals(0, calculator.calculate(BigDecimal.ONE).compareTo(new BigDecimal("0.014")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("14")).compareTo(new BigDecimal("0.196")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("15")).compareTo(new BigDecimal("0.21")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("16")).compareTo(new BigDecimal("0.224")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("99")).compareTo(new BigDecimal("1.386")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("100")).compareTo(new BigDecimal("1.4")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("100.01")).compareTo(new BigDecimal("2.40024")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("200")).compareTo(new BigDecimal("4.8")));
	}
}
