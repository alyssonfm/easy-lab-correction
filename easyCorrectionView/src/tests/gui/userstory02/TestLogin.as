package testSuitesLogin.TestLogin{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestSuite;

    import testSuitesLogin.TestLogin.tests.Realizar Login;
    import testSuitesLogin.TestLogin.tests.Alterar Senha;

    public class TestLogin extends MonkeyFlexUnitTestSuite{
        public function TestLogin(){
            addTestCase(new Realizar Login());
            addTestCase(new Alterar Senha());
        }
    }
}