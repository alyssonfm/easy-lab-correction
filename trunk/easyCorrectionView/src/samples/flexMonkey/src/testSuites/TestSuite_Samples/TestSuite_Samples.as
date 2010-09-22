package testSuites.TestSuite_Samples{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestSuite;

    import testSuites.TestSuite_Samples.tests.TestCase_Login;

    public class TestSuite_Samples extends MonkeyFlexUnitTestSuite{
        public function TestSuite_Samples(){
            addTestCase(new TestCase_Login());
        }
    }
}