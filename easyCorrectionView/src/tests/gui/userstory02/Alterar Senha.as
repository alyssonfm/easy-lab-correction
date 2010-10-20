package testSuitesLogin.TestLogin.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class Alterar Senha extends MonkeyFlexUnitTestCase{
        public function Alterar Senha(){
            super();
        }

        private var mtAlterar Senha:MonkeyTest = new MonkeyTest('Alterar Senha', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Click', 'Alterar Senha', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha_atual', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_senha_atual', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha_nova', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Click', 'Alterar senha', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null])
            ]));

        private var mtAlterar SenhaTimeoutTime:int = 19000;

        [Test]
        public function Alterar Senha():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtAlterar Senha, mtAlterar SenhaComplete, mtAlterar SenhaTimeoutTime, defaultTimeoutHandler);
        }

        public function mtAlterar SenhaComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtAlterar Senha);
        }


    }
}