package lv.proofit.techtask.test;

import java.math.BigDecimal;
import lv.proofit.techtask.RiskType;
import lv.proofit.techtask.SubPremiumTheftCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Anatolijs Gorbunovs
 */
public class SubPremiumTheftCalculatorTest {
	@Test
	public void testGetRiskType() {
		SubPremiumTheftCalculator calculator = new SubPremiumTheftCalculator();
		Assertions.assertEquals(RiskType.THEFT, calculator.getRiskType(), "Risk type mismatch.");
	}
	
	@Test
	public void testCalculate() {
		SubPremiumTheftCalculator calculator = new SubPremiumTheftCalculator();
		Assertions.assertEquals(0, calculator.calculate(BigDecimal.ONE).compareTo(new BigDecimal("0.11")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("14")).compareTo(new BigDecimal("1.54")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("15")).compareTo(new BigDecimal("0.75")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("16")).compareTo(new BigDecimal("0.8")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("99")).compareTo(new BigDecimal("4.95")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("100")).compareTo(new BigDecimal("5")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("100.01")).compareTo(new BigDecimal("5.0005")));
		Assertions.assertEquals(0, calculator.calculate(new BigDecimal("200")).compareTo(new BigDecimal("10")));
	}
}
