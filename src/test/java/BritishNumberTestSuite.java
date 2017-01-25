import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.convert.towords.impl.NumberToBritishWordsTest;
import com.convert.towords.vo.BritishNumericGroupTest;
import com.convert.towords.vo.NumberToProcessTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  NumberToBritishWordsTest.class,
  BritishNumericGroupTest.class,
  NumberToProcessTest.class
})
/**
 * Test suite to run all test classes
 * @author Anupam
 *
 */
public class BritishNumberTestSuite {

}
