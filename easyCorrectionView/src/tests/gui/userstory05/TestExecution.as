package testSuitesLogin.TestExecution{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestSuite;

    import testSuitesLogin.TestExecution.tests.US05;

    public class TestExecution extends MonkeyFlexUnitTestSuite{
        public function TestExecution(){
            addTestCase(new US05());
        }
    }
}