package graafvis.grammar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 *
 */
public class GraafvisGrammarTest {

    @Test
    public void testGrammar() {
        JUnitCore core = new JUnitCore();
        Result res;
        /* Variables */
        res = core.run(VariableTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Term */
        res = core.run(TermTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Predicate */
        res = core.run(PredicateTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Atom */
        res = core.run(AtomTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Multi atom */
        res = core.run(MultiAtomTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Literal */
        res = core.run(LiteralTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Propositional formula */
        res = core.run(PropositionalFormulaTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Consequence */
        res = core.run(ConsequenceTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Antecedent */
        res = core.run(AntecedentTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Clause */
        res = core.run(ClauseTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Label */
        res = core.run(LabelTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Node label gen */
        res = core.run(NodeLabelGenTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Edge label gen */
        res = core.run(EdgeLabelGenTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Import vis */
        res = core.run(ImportVisTest.class);
        Assert.assertEquals(0, res.getFailureCount());
        /* Program */
        res = core.run(ProgramTest.class);
        Assert.assertEquals(0, res.getFailureCount());

    }


}