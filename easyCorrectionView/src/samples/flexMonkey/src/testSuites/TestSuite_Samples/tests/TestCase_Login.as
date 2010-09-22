package testSuites.TestSuite_Samples.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class TestCase_Login extends MonkeyFlexUnitTestCase{
        public function TestCase_Login(){
            super();
        }

        private var mtTestLogin_OK:MonkeyTest = new MonkeyTest('TestLogin_OK', 0,
            new ArrayCollection([
                new VerifyMonkeyCommand('New Verify', null, 'Easy Lab Correction', 'automationName', false,
                    new ArrayCollection([
                        new AttributeVO('className', null, 'property', 'Login'),
                        new AttributeVO('clipContent', null, 'property', 'true'),
                        new AttributeVO('input_login', null, 'property', '<TextInput id="input_login">'),
                        new AttributeVO('input_senha', null, 'property', '<TextInput id="input_senha">'),
                        new AttributeVO('isDocument', null, 'property', 'true'),
                        new AttributeVO('isPopUp', null, 'property', 'true'),
                        new AttributeVO('title', null, 'property', 'Easy Lab Correction'),
                        new AttributeVO('x', null, 'property', '362'),
                        new AttributeVO('y', null, 'property', '85.5')
                    ]), null, null, true, '500', '20', 0),
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demas']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new VerifyMonkeyCommand('New Verify', null, 'menuBar', 'automationName', false,
                    new ArrayCollection([
                        new AttributeVO('measuredHeight', null, 'property', '23'),
                        new AttributeVO('measuredMinHeight', null, 'property', '23'),
                        new AttributeVO('measuredMinWidth', null, 'property', '639'),
                        new AttributeVO('measuredWidth', null, 'property', '639'),
                        new AttributeVO('menuBarItemRenderer', null, 'property', '[object ClassFactory]'),
                        new AttributeVO('minHeight', null, 'property', '23'),
                        new AttributeVO('minWidth', null, 'property', '639'),
                        new AttributeVO('screen', null, 'property', 'Rectange(x=0, y=0, width=1280, height=685)'),
                        new AttributeVO('visible', null, 'property', 'true')
                    ]), null, null, true, '500', '20', 0)
            ]));

        private var mtTestLogin_OKTimeoutTime:int = 12000;

        [Test]
        public function TestLogin_OK():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestLogin_OK, mtTestLogin_OKComplete, mtTestLogin_OKTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLogin_OKComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestLogin_OK);
        }

        private var mtTestLogin_Fail1:MonkeyTest = new MonkeyTest('TestLogin_Fail1', 0,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demas']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new VerifyMonkeyCommand('New Verify', null, 'Senha ou login invalido', 'automationName', false,
                    new ArrayCollection([
                        new AttributeVO('automationName', null, 'property', 'Senha ou login invalido')
                    ]), null, null, true, '500', '20', 0)
            ]));

        private var mtTestLogin_Fail1TimeoutTime:int = 11000;

        [Test]
        public function TestLogin_Fail1():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestLogin_Fail1, mtTestLogin_Fail1Complete, mtTestLogin_Fail1TimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLogin_Fail1Complete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestLogin_Fail1);
        }

        private var mtTestLogin_AlterarSenha:MonkeyTest = new MonkeyTest('TestLogin_AlterarSenha', 0,
            new ArrayCollection([
                new UIEventMonkeyCommand('Click', 'Alterar Senha', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demas']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha_atual', 'automationName', ['123']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_senha_atual', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha_nova', 'automationName', ['1234']),
                new UIEventMonkeyCommand('Click', 'Alterar senha', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null])
            ]));

        private var mtTestLogin_AlterarSenhaTimeoutTime:int = 13000;

        [Test]
        public function TestLogin_AlterarSenha():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestLogin_AlterarSenha, mtTestLogin_AlterarSenhaComplete, mtTestLogin_AlterarSenhaTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLogin_AlterarSenhaComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestLogin_AlterarSenha);
        }

        private var mtTestLogin_AlterarSenha2:MonkeyTest = new MonkeyTest('TestLogin_AlterarSenha2', 0,
            new ArrayCollection([
                new UIEventMonkeyCommand('Click', 'Alterar Senha', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demetrio']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha_atual', 'automationName', ['1234']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_senha_atual', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha_nova', 'automationName', ['12345']),
                new UIEventMonkeyCommand('Click', 'Alterar senha', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null])
            ]));

        private var mtTestLogin_AlterarSenha2TimeoutTime:int = 13000;

        [Test]
        public function TestLogin_AlterarSenha2():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestLogin_AlterarSenha2, mtTestLogin_AlterarSenha2Complete, mtTestLogin_AlterarSenha2TimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLogin_AlterarSenha2Complete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestLogin_AlterarSenha2);
        }

        private var mtTestLogin_Fail2:MonkeyTest = new MonkeyTest('TestLogin_Fail2', 0,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '5']),
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demetrio']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null])
            ]));

        private var mtTestLogin_Fail2TimeoutTime:int = 12000;

        [Test]
        public function TestLogin_Fail2():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestLogin_Fail2, mtTestLogin_Fail2Complete, mtTestLogin_Fail2TimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLogin_Fail2Complete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestLogin_Fail2);
        }

        private var mtTestLogin_Fail_OK:MonkeyTest = new MonkeyTest('TestLogin_Fail_OK', 50,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demetrio']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Limpar', 'automationName', [null]),
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['demas']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null])
            ]));

        private var mtTestLogin_Fail_OKTimeoutTime:int = 17600;

        [Test]
        public function TestLogin_Fail_OK():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestLogin_Fail_OK, mtTestLogin_Fail_OKComplete, mtTestLogin_Fail_OKTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLogin_Fail_OKComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestLogin_Fail_OK);
        }


    }
}