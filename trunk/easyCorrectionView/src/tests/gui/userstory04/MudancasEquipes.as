package testSuitesLogin.TestSubmissaoRoteiros.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class MudancasEquipes extends MonkeyFlexUnitTestCase{
        public function MudancasEquipes(){
            super();
        }

        private var mtImpossivelMudarEquipe:MonkeyTest = new MonkeyTest('ImpossivelMudarEquipe', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('SelectText', 'input_login', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['test']),
                new UIEventMonkeyCommand('SelectText', 'input_senha', 'automationName', [0, '0']),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação de Equipe']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste']),
                new UIEventMonkeyCommand('Open', 'comboEquipe', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboEquipe', 'automationName', ['Equipe 1']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'label{ string}automationName{main string}id{main string}className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}|label{ string}automationName{Gerenciamento%20de%20Equipes string}id{null object}className{modulos.Views.CriaEquipe string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}|automationName{index:6 string}className{mx.controls.Button string}automationIndex{index:6 string}id{null object}label{ string}automationClassName{FlexButton string}', 'automationID', [null])
            ]));

        private var mtImpossivelMudarEquipeTimeoutTime:int = 29500;

        [Test]
        public function ImpossivelMudarEquipe():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtImpossivelMudarEquipe, mtImpossivelMudarEquipeComplete, mtImpossivelMudarEquipeTimeoutTime, defaultTimeoutHandler);
        }

        public function mtImpossivelMudarEquipeComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtImpossivelMudarEquipe);
        }

        private var mtMudarParaEquipeComMaximo:MonkeyTest = new MonkeyTest('MudarParaEquipeComMaximo', 750,
            new ArrayCollection([
                new UIEventMonkeyCommand('Input', 'input_login', 'automationName', ['test']),
                new UIEventMonkeyCommand('ChangeFocus', 'input_login', 'automationName', [null]),
                new UIEventMonkeyCommand('Input', 'input_senha', 'automationName', ['123456']),
                new UIEventMonkeyCommand('Type', 'input_senha', 'automationName', ['ENTER']),
                new UIEventMonkeyCommand('Show', 'menuBar', 'automationName', ['Roteiros']),
                new UIEventMonkeyCommand('Select', 'Roteiros', 'automationName', ['Criação de Equipe']),
                new UIEventMonkeyCommand('Open', 'comboRoteiro', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboRoteiro', 'automationName', ['Roteiro de Teste']),
                new UIEventMonkeyCommand('Open', 'comboEquipe', 'automationName', [null]),
                new UIEventMonkeyCommand('Select', 'comboEquipe', 'automationName', ['Equipe 1']),
                new UIEventMonkeyCommand('Click', 'Entrar', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Erro', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Não foi possível mudar de equipe! Limite de integrantes da Equipe 1 já alcançado (máximo de 2 integrante(s) por equipe).', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'Não foi possível mudar de equipe! Limite de integrantes da Equipe 1 já alcançado (máximo de 2 integrante(s) por equipe).', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'OK', 'automationName', [null]),
                new UIEventMonkeyCommand('Click', 'label{ string}automationName{main string}id{main string}className{main string}automationIndex{index:-1 string}automationClassName{FlexApplication string}|label{ string}automationName{Gerenciamento%20de%20Equipes string}id{null object}className{modulos.Views.CriaEquipe string}automationIndex{index:11 string}automationClassName{FlexTitleWindow string}|automationName{index:6 string}className{mx.controls.Button string}automationIndex{index:6 string}id{null object}label{ string}automationClassName{FlexButton string}', 'automationID', [null])
            ]));

        private var mtMudarParaEquipeComMaximoTimeoutTime:int = 33000;

        [Test]
        public function MudarParaEquipeComMaximo():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtMudarParaEquipeComMaximo, mtMudarParaEquipeComMaximoComplete, mtMudarParaEquipeComMaximoTimeoutTime, defaultTimeoutHandler);
        }

        public function mtMudarParaEquipeComMaximoComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtMudarParaEquipeComMaximo);
        }


    }
}