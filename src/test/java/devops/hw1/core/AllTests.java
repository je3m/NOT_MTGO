package devops.hw1.core;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({AbstractDataTest.class,
				BackendTest.class,
				DispGUICardTest.class,
				ItemOnStackTest.class,
				ManaPoolTest.class,
				MTGComponentTest.class,
				NumericDataTest.class,
				TestCard.class,
				ZoneTest.class,
				SMELParserTest.class})
public class AllTests {

}
