package lv.proofit.techtask.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lv.proofit.techtask.Policy;
import lv.proofit.techtask.PolicyObject;
import lv.proofit.techtask.PolicyStatus;
import lv.proofit.techtask.PolicySubObject;
import lv.proofit.techtask.PremiumCalculator;
import lv.proofit.techtask.RiskType;
import lv.proofit.techtask.SubPremiumCalculator;
import lv.proofit.techtask.SubPremiumFireCalculator;
import lv.proofit.techtask.SubPremiumTheftCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Anatolijs Gorbunovs
 */
public class PremiumCalculatorTest {
	
	@Test
	public void testCalculate() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("100.0"), RiskType.FIRE);
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("8.0"), RiskType.THEFT);
		PolicySubObject subC = new PolicySubObject("Subobject C", new BigDecimal("500.0"), RiskType.FIRE);
		PolicySubObject subD = new PolicySubObject("Subobject D", new BigDecimal("102.51"), RiskType.THEFT);
		List<PolicyObject> objectsA = Arrays.asList(
				new PolicyObject("Single element object", Arrays.asList(subA, subB)));
		List<PolicyObject> objectsB = Arrays.asList(
				new PolicyObject("Three element object", Arrays.asList(subC, subD)));
		Policy policyA = new Policy(
				"LV20-02-100000-5",
				PolicyStatus.REGISTERED,
				objectsA
		);
		Policy policyB = new Policy(
				"LV20-02-100000-6",
				PolicyStatus.REGISTERED,
				objectsB
		);
		SubPremiumCalculator fireCalculator = new SubPremiumFireCalculator();
		SubPremiumCalculator theftCalculator = new SubPremiumTheftCalculator();
		PremiumCalculator calculator = new PremiumCalculator(
				Arrays.asList(fireCalculator, theftCalculator));
		
		Assertions.assertEquals(0, new BigDecimal("2.28").compareTo(calculator.calculate(policyA)));
		Assertions.assertEquals(0, new BigDecimal("17.13").compareTo(calculator.calculate(policyB)));
	}
}
