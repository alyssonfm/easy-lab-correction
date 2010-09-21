package testSuites.TestSuite_US1{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestSuite;

    import testSuites.TestSuite_US1.tests.TestCase_Sample;
    import testSuites.TestSuite_US1.tests.TestCase_Cadastro;

    public class TestSuite_US1 extends MonkeyFlexUnitTestSuite{
        public function TestSuite_US1(){
            addTestCase(new TestCase_Sample());
            addTestCase(new TestCase_Cadastro());
        }
    }
}