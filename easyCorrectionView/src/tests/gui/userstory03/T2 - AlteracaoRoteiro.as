package SuiteUS02.SuiteTestsUS02.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class T2 - AlteracaoRoteiro extends MonkeyFlexUnitTestCase{
        public function T2 - AlteracaoRoteiro(){
            super();
        }

        private var mtAnexarArquivoNaoJavaInterface:MonkeyTest = new MonkeyTest('AnexarArquivoNaoJavaInterface', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação']),
                new UIEventMonkeyCommand('Select', 'dg_agenda', 'automationName', ['*Roteiro Teste Minimo Dados* | 25/10/2010 | 01/11/2010']),
                new UIEventMonkeyCommand('Click', '   Anexar Interface', 'automationName', [null]),
                new PauseMonkeyCommand(750),
                new UIEventMonkeyCommand('Click', 'Cadastro de Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'label{ string}automationName{main string}automationClassName{FlexApplication string}className{main string}automationIndex{index:-1 string}id{main string}|label{ string}automationName{Cadastro%20de%20Roteiro string}automationClassName{FlexTitleWindow string}className{modulos.Views.CadastrarRoteiro string}automationIndex{index:12 string}id{null object}|automationName{index:33 string}automationClassName{FlexButton string}id{null object}label{ string}className{mx.controls.Button string}automationIndex{index:33 string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'label{ string}automationName{main string}automationClassName{FlexApplication string}className{main string}automationIndex{index:-1 string}id{main string}|label{ string}automationName{Agendamento%20de%20Roteiros string}automationClassName{FlexTitleWindow string}className{modulos.Views.AgendamentoRoteiros string}automationIndex{index:11 string}id{null object}|automationName{index:2 string}automationClassName{FlexButton string}id{null object}label{ string}className{mx.controls.Button string}automationIndex{index:2 string}', 'automationID', [null])
            ]));

        private var mtAnexarArquivoNaoJavaInterfaceTimeoutTime:int = 27750;

        [Test]
        public function AnexarArquivoNaoJavaInterface():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtAnexarArquivoNaoJavaInterface, mtAnexarArquivoNaoJavaInterfaceComplete, mtAnexarArquivoNaoJavaInterfaceTimeoutTime, defaultTimeoutHandler);
        }

        public function mtAnexarArquivoNaoJavaInterfaceComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtAnexarArquivoNaoJavaInterface);
        }

        private var mtAnexarArquivoDiferenteZip:MonkeyTest = new MonkeyTest('AnexarArquivoDiferenteZip', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação']),
                new UIEventMonkeyCommand('Select', 'dg_agenda', 'automationName', ['Roteiro Teste Minimo Dados | *25/10/2010* | 01/11/2010']),
                new UIEventMonkeyCommand('Click', '   Anexar Testes Automáticos', 'automationName', [null])
            ]));

        private var mtAnexarArquivoDiferenteZipTimeoutTime:int = 20750;

        [Test]
        public function AnexarArquivoDiferenteZip():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtAnexarArquivoDiferenteZip, mtAnexarArquivoDiferenteZipComplete, mtAnexarArquivoDiferenteZipTimeoutTime, defaultTimeoutHandler);
        }

        public function mtAnexarArquivoDiferenteZipComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtAnexarArquivoDiferenteZip);
        }

        private var mtLiberarRoteiro:MonkeyTest = new MonkeyTest('LiberarRoteiro', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['no']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['BACKSPACE']),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['alysson']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação']),
                new UIEventMonkeyCommand('Select', 'dg_agenda', 'automationName', ['Roteiro Teste Minimo Dados | *25/10/2010* | 01/11/2010']),
                new UIEventMonkeyCommand('Open', 'dfDataLiberacao', 'automationName', [null]),
                new UIEventMonkeyCommand('Change', 'dfDataLiberacao', 'automationName', ['Sat Oct 23 2010']),
                new UIEventMonkeyCommand('Click', 'Atualizar Roteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationName{main string}automationClassName{FlexApplication string}label{ string}id{main string}|className{modulos.Views.CadastrarRoteiro string}automationIndex{index:12 string}automationName{Cadastro%20de%20Roteiro string}automationClassName{FlexTitleWindow string}label{ string}id{null object}|automationIndex{index:33 string}className{mx.controls.Button string}automationClassName{FlexButton string}id{null object}automationName{index:33 string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationName{main string}automationClassName{FlexApplication string}label{ string}id{main string}|className{modulos.Views.AgendamentoRoteiros string}automationIndex{index:11 string}automationName{Agendamento%20de%20Roteiros string}automationClassName{FlexTitleWindow string}label{ string}id{null object}|automationIndex{index:2 string}className{mx.controls.Button string}automationClassName{FlexButton string}id{null object}automationName{index:2 string}label{ string}', 'automationID', [null]),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Submissão']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new VerifyMonkeyCommand('New Verify', null, 'comboRoteiro', 'automationName', false, null, null, null, true, '500', '20', 0),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro Teste Minimo Dados']),
                new UIEventMonkeyCommand('Click', 'className{main string}automationIndex{index:-1 string}automationName{main string}automationClassName{FlexApplication string}label{ string}id{main string}|className{modulos.Views.SubmissaoDeRoteiros string}automationIndex{index:11 string}automationName{Submiss%C3%A3o%20de%20Roteiro string}automationClassName{FlexTitleWindow string}label{ string}id{null object}|automationIndex{index:9 string}className{mx.controls.Button string}automationClassName{FlexButton string}id{null object}automationName{index:9 string}label{ string}', 'automationID', [null])
            ]));

        private var mtLiberarRoteiroTimeoutTime:int = 45250;

        [Test]
        public function LiberarRoteiro():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtLiberarRoteiro, mtLiberarRoteiroComplete, mtLiberarRoteiroTimeoutTime, defaultTimeoutHandler);
        }

        public function mtLiberarRoteiroComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtLiberarRoteiro);
        }


    }
}