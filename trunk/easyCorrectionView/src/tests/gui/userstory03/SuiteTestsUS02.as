package SuiteUS02.SuiteTestsUS02{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestSuite;

    import SuiteUS02.SuiteTestsUS02.tests.T2 - AlteracaoRoteiro;
    import SuiteUS02.SuiteTestsUS02.tests.T1 - CriacaoRoteiro;

    public class SuiteTestsUS02 extends MonkeyFlexUnitTestSuite{
        public function SuiteTestsUS02(){
            addTestCase(new T2 - AlteracaoRoteiro());
            addTestCase(new T1 - CriacaoRoteiro());
        }
    }
}