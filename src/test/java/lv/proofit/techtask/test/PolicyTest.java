package lv.proofit.techtask.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lv.proofit.techtask.Policy;
import lv.proofit.techtask.PolicyObject;
import lv.proofit.techtask.PolicyStatus;
import lv.proofit.techtask.PolicySubObject;
import lv.proofit.techtask.RiskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PolicyTest {

	@Test
	public void testConstructorOK() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("100.0"), RiskType.FIRE);
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("8.0"), RiskType.THEFT);
		PolicySubObject subC = new PolicySubObject("Subobject C", new BigDecimal("500.0"), RiskType.FIRE);
		PolicySubObject subD = new PolicySubObject("Subobject D", new BigDecimal("102.51"), RiskType.THEFT);
		List<PolicyObject> objects = Arrays.asList(
				new PolicyObject("Empty object", Collections.EMPTY_LIST),
				new PolicyObject("Single element object", Arrays.asList(subA)),
				new PolicyObject("Three element object", Arrays.asList(subB, subC, subD)));
		Policy policy = new Policy(
				"LV20-02-100000-5",
				PolicyStatus.REGISTERED,
				objects
		);
		Assert.isTrue(policy.getNumber().equals("LV20-02-100000-5"), "Policy number does not match.");
		Assert.isTrue(policy.getStatus().equals(PolicyStatus.REGISTERED), "Policy status does not match.");
		Assert.isTrue(policy.getObjects().containsAll(objects) && objects.containsAll(policy.getObjects()), "Policy objects do not match.");
	}
	
	@Test
	public void testConstructorThrowsNumberNull() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("100.0"), RiskType.FIRE);
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("8.0"), RiskType.THEFT);
		PolicySubObject subC = new PolicySubObject("Subobject C", new BigDecimal("500.0"), RiskType.FIRE);
		PolicySubObject subD = new PolicySubObject("Subobject D", new BigDecimal("102.51"), RiskType.THEFT);
		List<PolicyObject> objects = Arrays.asList(
				new PolicyObject("Empty object", Collections.EMPTY_LIST),
				new PolicyObject("Single element object", Arrays.asList(subA)),
				new PolicyObject("Three element object", Arrays.asList(subB, subC, subD)));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Policy(
				null,
				PolicyStatus.REGISTERED,
				objects
		));
	}
	
	@Test
	public void testConstructorThrowsStatusNull() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("100.0"), RiskType.FIRE);
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("8.0"), RiskType.THEFT);
		PolicySubObject subC = new PolicySubObject("Subobject C", new BigDecimal("500.0"), RiskType.FIRE);
		PolicySubObject subD = new PolicySubObject("Subobject D", new BigDecimal("102.51"), RiskType.THEFT);
		List<PolicyObject> objects = Arrays.asList(
				new PolicyObject("Empty object", Collections.EMPTY_LIST),
				new PolicyObject("Single element object", Arrays.asList(subA)),
				new PolicyObject("Three element object", Arrays.asList(subB, subC, subD)));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Policy(
				"LV20-02-100000-5",
				null,
				objects
		));
	}
	
	@Test
	public void testConstructorThrowsObjectsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Policy(
				"LV20-02-100000-5",
				PolicyStatus.REGISTERED,
				null
		));
	}
	
	@Test
	public void testConstructorThrowsNumberBlank() {
		PolicySubObject subA = new PolicySubObject("Subobject A", new BigDecimal("100.0"), RiskType.FIRE);
		PolicySubObject subB = new PolicySubObject("Subobject B", new BigDecimal("8.0"), RiskType.THEFT);
		PolicySubObject subC = new PolicySubObject("Subobject C", new BigDecimal("500.0"), RiskType.FIRE);
		PolicySubObject subD = new PolicySubObject("Subobject D", new BigDecimal("102.51"), RiskType.THEFT);
		List<PolicyObject> objects = Arrays.asList(
				new PolicyObject("Empty object", Collections.EMPTY_LIST),
				new PolicyObject("Single element object", Arrays.asList(subA)),
				new PolicyObject("Three element object", Arrays.asList(subB, subC, subD)));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Policy(
				" \t\n  ",
				null,
				objects
		));
	}
	
	@Test
	public void testConstructorThrowsObjectsEmpty() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Policy(
				"LV20-02-100000-5",
				PolicyStatus.REGISTERED,
				Collections.EMPTY_LIST
		));
	}
}
