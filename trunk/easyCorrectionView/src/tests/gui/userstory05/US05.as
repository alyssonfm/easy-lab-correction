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

        private var mtTestL:MonkeyTest = new MonkeyTest('TestL', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['aluno']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação de Equipe']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste em Grupo']),
                new UIEventMonkeyCommand('Open', 'comboEquipe', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboEquipe', 'automationName', ['Equipe 2']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.CriaEquipe string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Gerenciamento%20de%20Equipes string}label{ string}id{null object}|automationName{index:6 string}className{mx.controls.Button string}id{null object}automationIndex{index:6 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste em Grupo']),
                new UIEventMonkeyCommand('Click', '   Anexar Solução', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação de Equipe']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste em Grupo']),
                new UIEventMonkeyCommand('Open', 'comboEquipe', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboEquipe', 'automationName', ['Equipe 3']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.sistema.MensagemStatus string}automationIndex{index:12 string}automationClassName{FlexTitleWindow string}automationName{Sucesso string}label{ string}id{null object}|automationName{index:4 string}className{mx.controls.Button string}id{null object}automationIndex{index:4 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.CriaEquipe string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Gerenciamento%20de%20Equipes string}label{ string}id{null object}|automationName{index:6 string}className{mx.controls.Button string}id{null object}automationIndex{index:6 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestLTimeoutTime:int = 57500;

        [Test]
        public function TestL():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestL, mtTestLComplete, mtTestLTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestLComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestL);
        }

        private var mtTestJ:MonkeyTest = new MonkeyTest('TestJ', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['aluno']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste Alysson']),
                new UIEventMonkeyCommand('Click', '   Anexar Solução', 'automationName', [null]),
                new PauseMonkeyCommand(15000),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestJTimeoutTime:int = 45500;

        [Test]
        public function TestJ():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestJ, mtTestJComplete, mtTestJTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestJComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestJ);
        }

        private var mtTestI:MonkeyTest = new MonkeyTest('TestI', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['aluno']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste Alysson']),
                new UIEventMonkeyCommand('Click', '   Anexar Solução', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestITimeoutTime:int = 26000;

        [Test]
        public function TestI():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestI, mtTestIComplete, mtTestITimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestIComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestI);
        }

        private var mtTestH:MonkeyTest = new MonkeyTest('TestH', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['aluno']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste Alysson']),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', '   Anexar Solução', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Scroll', 'inputDescricao', 'automationName', [0, '2', '13']),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestHTimeoutTime:int = 31250;

        [Test]
        public function TestH():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestH, mtTestHComplete, mtTestHTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestHComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestH);
        }

        private var mtTestG:MonkeyTest = new MonkeyTest('TestG', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Click', 'listaRoteiros', 'automationName', [null]),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste Alysson']),
                new UIEventMonkeyCommand('Click', '   Anexar Solução', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestGTimeoutTime:int = 26000;

        [Test]
        public function TestG():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestG, mtTestGComplete, mtTestGTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestGComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestG);
        }

        private var mtTestC:MonkeyTest = new MonkeyTest('TestC', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['aluno']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste Alysson']),
                new UIEventMonkeyCommand('Click', '   Anexar Solução', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', '   Submeter Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Scroll', 'inputDescricao', 'automationName', [5, '2', '13']),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestCTimeoutTime:int = 29500;

        [Test]
        public function TestC():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestC, mtTestCComplete, mtTestCTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestCComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestC);
        }

        private var mtTestB:MonkeyTest = new MonkeyTest('TestB', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
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