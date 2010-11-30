package testSuitesLogin.TestExecution.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class US07 extends MonkeyFlexUnitTestCase{
        public function US07(){
            super();
        }

        private var mtTestGH:MonkeyTest = new MonkeyTest('TestGH', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['aluno']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123123']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste Alysson']),
                new UIEventMonkeyCommand('Click', 'Equipe 1', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestGHTimeoutTime:int = 27750;

        [Test]
        public function TestGH():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestGH, mtTestGHComplete, mtTestGHTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestGHComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestGH);
        }

        private var mtTestAB:MonkeyTest = new MonkeyTest('TestAB', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123123']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação de Roteiros']),
                new UIEventMonkeyCommand('Select', 'dg_agenda', 'automationName', ['Roteiro de Teste em Grupo | *30/11/2010* | 07/12/2010']),
                new UIEventMonkeyCommand('Click', '   Anexar Testes Automáticos', 'automationName', [null]),
                new PauseMonkeyCommand(800),
                new UIEventMonkeyCommand('Click', '   Anexar Interface', 'automationName', [null]),
                new PauseMonkeyCommand(800),
                new UIEventMonkeyCommand('Click', 'Cadastro de Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Atualizar Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.CadastrarRoteiro string}automationIndex{index:12 string}automationClassName{FlexTitleWindow string}automationName{Cadastro%20de%20Roteiro string}label{ string}id{null object}|automationName{index:33 string}className{mx.controls.Button string}id{null object}automationIndex{index:33 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.AgendamentoRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Agendamento%20de%20Roteiros string}label{ string}id{null object}|automationName{index:2 string}className{mx.controls.Button string}id{null object}automationIndex{index:2 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestABTimeoutTime:int = 33100;

        [Test]
        public function TestAB():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestAB, mtTestABComplete, mtTestABTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestABComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestAB);
        }

        private var mtTestD:MonkeyTest = new MonkeyTest('TestD', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123123']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação de Roteiros']),
                new UIEventMonkeyCommand('Select', 'dg_agenda', 'automationName', ['*Roteiro de Teste em Grupo* | 30/11/2010 | 07/12/2010']),
                new UIEventMonkeyCommand('SelectText', 'inputMaximoEquipe', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('SelectText', 'inputPenalidade', 'automationName', [1, '1']),
                new UIEventMonkeyCommand('SelectText', 'inputPenalidade', 'automationName', [1, '1']),
                new UIEventMonkeyCommand('SelectText', 'inputPenalidade', 'automationName', [0, '1']),
                new UIEventMonkeyCommand('Input', 'inputPenalidade', 'automationName', ['100']),
                new UIEventMonkeyCommand('Click', 'Atualizar Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Roteiro Atualizado com sucesso.', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Roteiro Atualizado com sucesso.', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.CadastrarRoteiro string}automationIndex{index:12 string}automationClassName{FlexTitleWindow string}automationName{Cadastro%20de%20Roteiro string}label{ string}id{null object}|automationName{index:33 string}className{mx.controls.Button string}id{null object}automationIndex{index:33 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', '   Novo Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('SelectText', 'inputTitulo', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'inputTitulo', 'automationName', ['Roteiro 0']),
                new UIEventMonkeyCommand('SelectText', 'inputPenalidade', 'automationName', [3, '3']),
                new UIEventMonkeyCommand('Click', '   Cadastrar Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Roteiro Cadastrado com sucesso.', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Sucesso', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.CadastrarRoteiro string}automationIndex{index:12 string}automationClassName{FlexTitleWindow string}automationName{Cadastro%20de%20Roteiro string}label{ string}id{null object}|automationName{index:33 string}className{mx.controls.Button string}id{null object}automationIndex{index:33 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.AgendamentoRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Agendamento%20de%20Roteiros string}label{ string}id{null object}|automationName{index:2 string}className{mx.controls.Button string}id{null object}automationIndex{index:2 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestDTimeoutTime:int = 52250;

        [Test]
        public function TestD():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestD, mtTestDComplete, mtTestDTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestDComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestD);
        }

        private var mtTestIJ:MonkeyTest = new MonkeyTest('TestIJ', 0,
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
                new PauseMonkeyCommand(1500),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'inputDescricao', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}automationName{main string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}automationName{Submiss%C3%A3o%20de%20Roteiro string}label{ string}id{null object}|automationName{index:15 string}className{mx.controls.Button string}id{null object}automationIndex{index:15 string}automationClassName{FlexButton string}label{ string}', 'automationID', [null])
            ]));

        private var mtTestIJTimeoutTime:int = 25500;

        [Test]
        public function TestIJ():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtTestIJ, mtTestIJComplete, mtTestIJTimeoutTime, defaultTimeoutHandler);
        }

        public function mtTestIJComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtTestIJ);
        }


    }
}