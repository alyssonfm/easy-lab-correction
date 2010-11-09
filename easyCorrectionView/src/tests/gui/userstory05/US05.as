package testSuitesLogin.TestExecution.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class US05 extends MonkeyFlexUnitTestCase{
        public function US05(){
            super();
        }

        private var mtTestB:MonkeyTest = new MonkeyTest('TestB', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['teste']),
                new UIEventMonkeyCommand('Type', 'input_login', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste']),
                new UIEventMonkeyCommand('Click', '   Anexar Roteiro', 'automationName', [null]),
                new PauseMonkeyCommand(5000),
                new UIEventMonkeyCommand('Click', 'label{ string}className{main string}automationClassName{FlexApplication string}automationName{main string}automationIndex{index:-1 string}id{main string}|label{ string}className{modulos.Views.SubmissaoDeRoteiros string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}automationIndex{index:11 string}id{null object}|className{mx.controls.Button string}automationName{index:9 string}automationIndex{index:9 string}label{ string}automationClassName{FlexButton string}id{null object}', 'automationID', [null])
            ]));

        private var mtTestBTimeoutTime:int = 32000;

        [Test]
        public function TestB():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestB, mtTestBComplete, mtTestBTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestBComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestB);
        }


    }
}