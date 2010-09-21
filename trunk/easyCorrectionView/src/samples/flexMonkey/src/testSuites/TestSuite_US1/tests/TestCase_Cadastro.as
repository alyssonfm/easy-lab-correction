package testSuites.TestSuite_US1.tests{
    import com.gorillalogic.flexmonkey.flexunit.tests.MonkeyFlexUnitTestCase;

    import com.gorillalogic.flexmonkey.core.MonkeyTest;
    import com.gorillalogic.flexmonkey.monkeyCommands.*;
    import com.gorillalogic.flexmonkey.application.VOs.AttributeVO;
    import com.gorillalogic.flexmonkey.events.MonkeyCommandRunnerEvent;

    import mx.collections.ArrayCollection;

    public class TestCase_Cadastro extends MonkeyFlexUnitTestCase{
        public function TestCase_Cadastro(){
            super();
        }

        private var mtNewTest:MonkeyTest = new MonkeyTest('NewTest', 0,
            new ArrayCollection([
            ]));

        private var mtNewTestTimeoutTime:int = 5000;

        [Test]
        public function NewTest():void{
            // startTest(<MonkeyTest>, <Complete method>, <Async timeout value>, <Timeout method>);
            startTest(mtNewTest, mtNewTestComplete, mtNewTestTimeoutTime, defaultTimeoutHandler);
        }

        public function mtNewTestComplete(event:MonkeyCommandRunnerEvent, passThroughData:Object):void{
            checkCommandResults(mtNewTest);
        }


    }
}