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

public class PolicyObjectTest {
	@Test
	public void testConstructorOK() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("100.0"), RiskType.FIRE.FIRE);
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("8.0"), RiskType.THEFT);
		PolicyObject object = new PolicyObject("Two element object", Arrays.asList(subA, subB));

		Assert.isTrue(object.getName().equals("Two element object"), "Policy object name does not match.");
		Assert.isTrue(object.getSubObjects().size() == 2, "Policy object doesn't contain 2 sub-objects.");
		Assert.isTrue(object.getSubObjects().contains(subA), "Policy object doesn't contain the needed sub-object.");
		Assert.isTrue(object.getSubObjects().contains(subB), "Policy object doesn't contain the needed sub-object.");
	}
	
	@Test
	public void testConstructorThrowsNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicyObject(null, Collections.EMPTY_LIST));
	}
	
	@Test
	public void testConstructorThrowsSubObjectsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicyObject("Test policy object", null));
	}
	
	@Test
	public void testConstructorThrowsNameBlank() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PolicyObject("", Collections.EMPTY_LIST));
	}
}
