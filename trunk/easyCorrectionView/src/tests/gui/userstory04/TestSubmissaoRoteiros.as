package testSuitesLogin.TestSubmissaoRoteiros{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestSuite;

    import testSuitesLogin.TestSubmissaoRoteiros.tests.MudancasEquipes;

    public class TestSubmissaoRoteiros extends MonkeyFlexUnitTestSuite{
        public function TestSubmissaoRoteiros(){
            addTestCase(new MudancasEquipes());
        }
    }
}