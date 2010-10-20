package testSuitesLogin.TestLogin.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class Realizar Login extends MonkeyFlexUnitTestCase{
        public function Realizar Login(){
            super();
        }

        private var mtLogin e Senha Ok:MonkeyTest = new MonkeyTest('Login e Senha Ok', 500,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null])
            ]));

        private var mtLogin e Senha OkTimeoutTime:int = 12500;

        [Test]
        public function Login e Senha Ok():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtLogin e Senha Ok, mtLogin e Senha OkComplete, mtLogin e Senha OkTimeoutTime, defaultTimeoutHandler);
        }

        public function mtLogin e Senha OkComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtLogin e Senha Ok);
        }

        private var mtLogin ou senha inválido:MonkeyTest = new MonkeyTest('Login ou senha inválido', 500,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alyssonfm']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456789']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Limpar', 'automationName', [null])
            ]));

        private var mtLogin ou senha inválidoTimeoutTime:int = 15500;

        [Test]
        public function Login ou senha inválido():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtLogin ou senha inválido, mtLogin ou senha inválidoComplete, mtLogin ou senha inválidoTimeoutTime, defaultTimeoutHandler);
        }

        public function mtLogin ou senha inválidoComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtLogin ou senha inválido);
        }


    }
}