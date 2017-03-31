package figglewatts.jarson;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Sam Gibson
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JSONStringTest.class,
JSONBoolTest.class,
JSONNullTest.class,
JSONNumberTest.class,
JSONArrayTest.class,
JSONObjectTest.class,
JSONNodeTest.class})
public class JarSONTestSuite {
}
