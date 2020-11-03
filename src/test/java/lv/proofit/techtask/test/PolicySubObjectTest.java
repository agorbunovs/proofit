package lv.proofit.techtask.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import lv.proofit.techtask.PolicyObject;
import lv.proofit.techtask.PolicySubObject;
import lv.proofit.techtask.RiskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 *
 * @author Anatolijs Gorbunovs
 */
public class PolicySubObjectTest {
	@Test
	public void testConstructorOK() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("0.0"), RiskType.FIRE);
		
		Assert.isTrue(subA.getName().equals("Subobject A"), "Policy sub-objectg name mismatch.");
		Assert.isTrue(subA.getRiskType().equals(RiskType.FIRE), "Policy sub-object risk type mismatch.");
		Assert.isTrue(subA.getSumInsured().compareTo(BigDecimal.ZERO) == 0, "Policy sub-object sum insured mismatch.");
		
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("10.0"), RiskType.THEFT);
		Assert.isTrue(subB.getName().equals("Subobject B"), "Policy sub-objectg name mismatch.");
		Assert.isTrue(subB.getRiskType().equals(RiskType.THEFT), "Policy sub-object risk type mismatch.");
		Assert.isTrue(subB.getSumInsured().compareTo(new BigDecimal("10.00")) == 0, "Policy sub-object sum insured mismatch.");
		
		PolicySubObject subC = new PolicySubObject("Subobject C", new BigDecimal("10000000000000000000.99"), RiskType.THEFT);
		
		Assert.isTrue(subC.getName().equals("Subobject C"), "Policy sub-objectg name mismatch.");
		Assert.isTrue(subC.getRiskType().equals(RiskType.THEFT), "Policy sub-object risk type mismatch.");
		Assert.isTrue(subC.getSumInsured().compareTo(new BigDecimal("10000000000000000000.99")) == 0, "Policy sub-object sum insured mismatch.");
	}
	
	@Test
	public void testConstructorThrowsNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicySubObject(null, BigDecimal.ZERO, RiskType.THEFT));
	}
	
	@Test
	public void testConstructorThrowsNameBlank() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicyObject("", Collections.EMPTY_LIST));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicyObject("  \n\t", Collections.EMPTY_LIST));
	}
	
	@Test
	public void testConstructorThrowsSumInsuredNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicySubObject("Test sub object", null, RiskType.THEFT));
	}
	
	@Test
	public void testConstructorThrowsSumInsuredNegative() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicySubObject("Test sub object", BigDecimal.ONE.negate(), RiskType.THEFT));
	}
	
	@Test
	public void testConstructorThrowsRiskTypeNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicySubObject("Test sub object", BigDecimal.ONE, null));
	}
	
	@Test
	public void testConstructorThrowsParentAlreadySet() {
		PolicySubObject sub = new PolicySubObject("Test sub object", BigDecimal.ONE, RiskType.THEFT);
		Assertions.assertDoesNotThrow(() -> new PolicyObject("Test policy", Arrays.asList(sub)));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicyObject("Test wrong policy", Arrays.asList(sub)));
	}
}
