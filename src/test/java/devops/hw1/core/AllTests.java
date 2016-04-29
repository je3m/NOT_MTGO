package devops.hw1.core;


import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({AbilityTest.class, AbstractDataTest.class, BackendTest.class, 
	CanTargetTest.class, DispGUICardTest.class, ItemOnStackTest.class, ManaPoolTest.class,
	MTGComponentTest.class, NumericDataTest.class, TestAbilityType.class, TestCard.class,
	ZoneTest.class})
public class AllTests {
	public static void main (String args[]) {
        org.junit.runner.JUnitCore.main("devops.hw1.core.AllTests");
    }
}

