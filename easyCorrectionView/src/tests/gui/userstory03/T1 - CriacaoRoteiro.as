package SuiteUS02.SuiteTestsUS02.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class T1 - CriacaoRoteiro extends MonkeyFlexUnitTestCase{
        public function T1 - CriacaoRoteiro(){
            super();
        }

        private var mtRoteiroMinimoDados:MonkeyTest = new MonkeyTest('RoteiroMinimoDados', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação']),
                new UIEventMonkeyCommand('Click', '   Novo Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('SelectText', 'inputTitulo', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'inputTitulo', 'automationName', ['Roteiro Teste Minimo Dd']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Input', 'inputTitulo', 'automationName', ['ados']),
                new UIEventMonkeyCommand('Open', 'dfDataLiberacao', 'automationName', [null]),
                new UIEventMonkeyCommand('Change', 'dfDataLiberacao', 'automationName', ['Mon Oct 25 2010']),
                new UIEventMonkeyCommand('Click', '   Cadastrar Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationName{main string}automationClassName{FlexApplication string}label{ string}id{main string}|className{modulos.Views.CadastrarRoteiro string}automationIndex{index:12 string}automationName{Cadastro%20de%20Roteiro string}automationClassName{FlexTitleWindow string}label{ string}id{null object}|automationIndex{index:33 string}className{mx.controls.Button string}automationClassName{FlexButton string}id{null object}automationName{index:33 string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationName{main string}automationClassName{FlexApplication string}label{ string}id{main string}|className{modulos.Views.AgendamentoRoteiros string}automationIndex{index:11 string}automationName{Agendamento%20de%20Roteiros string}automationClassName{FlexTitleWindow string}label{ string}id{null object}|automationIndex{index:2 string}className{mx.controls.Button string}automationClassName{FlexButton string}id{null object}automationName{index:2 string}label{ string}', 'automationID', [null])
            ]));

        private var mtRoteiroMinimoDadosTimeoutTime:int = 36500;

        [Test]
        public function RoteiroMinimoDados():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtRoteiroMinimoDados, mtRoteiroMinimoDadosComplete, mtRoteiroMinimoDadosTimeoutTime, defaultTimeoutHandler);
        }

        public function mtRoteiroMinimoDadosComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtRoteiroMinimoDados);
        }

        private var mtPorcentagemInvalida:MonkeyTest = new MonkeyTest('PorcentagemInvalida', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação']),
                new UIEventMonkeyCommand('Click', '   Novo Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('SelectText', 'inputTitulo', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('SelectText', 'inputTitulo', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('Input', 'inputTitulo', 'automationName', ['T']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Input', 'inputTitulo', 'automationName', ['Roteiro Teste com Penali']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Type', 'inputTitulo', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Input', 'inputTitulo', 'automationName', ['orcentagem Invalida']),
                new UIEventMonkeyCommand('SelectText', 'inputDescricao', 'automationName', ['0', '0']),
                new UIEventMonkeyCommand('SelectText', 'inputPorcentagem', 'automationName', ['0', '3']),
                new UIEventMonkeyCommand('Input', 'inputPorcentagem', 'automationName', ['12000']),
                new UIEventMonkeyCommand('Click', '   Cadastrar Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'automationName{main string}automationClassName{FlexApplication string}automationIndex{index:-1 string}label{ string}className{main string}id{main string}|automationName{Cadastro%20de%20Roteiro string}automationClassName{FlexTitleWindow string}automationIndex{index:12 string}label{ string}className{modulos.Views.CadastrarRoteiro string}id{null object}|automationName{index:33 string}automationClassName{FlexButton string}label{ string}id{null object}className{mx.controls.Button string}automationIndex{index:33 string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'automationName{main string}automationClassName{FlexApplication string}automationIndex{index:-1 string}label{ string}className{main string}id{main string}|automationName{Agendamento%20de%20Roteiros string}automationClassName{FlexTitleWindow string}automationIndex{index:11 string}label{ string}className{modulos.Views.AgendamentoRoteiros string}id{null object}|automationName{index:2 string}automationClassName{FlexButton string}label{ string}id{null object}className{mx.controls.Button string}automationIndex{index:2 string}', 'automationID', [null])
            ]));

        private var mtPorcentagemInvalidaTimeoutTime:int = 48750;

        [Test]
        public function PorcentagemInvalida():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtPorcentagemInvalida, mtPorcentagemInvalidaComplete, mtPorcentagemInvalidaTimeoutTime, defaultTimeoutHandler);
        }

        public function mtPorcentagemInvalidaComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtPorcentagemInvalida);
        }


    }
}