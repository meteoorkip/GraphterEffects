package graafvis.checkers;

import graafvis.ErrorListener;
import graafvis.errors.VisError;
import graafvis.grammar.GraafvisLexer;
import graafvis.grammar.GraafvisParser;
import graafvis.warnings.Warning;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Test;

/**
 *
 */
public class GraafvisCheckerTest {

    private static final String PROGRAM_1 =
            "node labels: \"Wolf\".\n" +
            "edge labels: \"Boat\" as boat.\n" +
            "node(X), boat(X) -> predicate(X).\n" +
            "node(X) -> edge(X,Y).\n" +
            "predicate(X) -> boat(_).\n" +
            "predicate(X, Y) -> goat(Y)."
    ;

    @Test
    public void test() {
        GraafvisParser.ProgramContext program = parse(PROGRAM_1);
        CheckerResult checkerResult = new GraafvisChecker().check(program);
        for (VisError error : checkerResult.getErrors()) {
            System.err.println(error);
        }
        for (Warning warning : checkerResult.getWarnings()) {
            System.out.println(warning);
        }
    }

    private static GraafvisParser.ProgramContext parse(String program) {
        CharStream stream = new ANTLRInputStream(program);
        GraafvisLexer lexer = new GraafvisLexer(stream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        GraafvisParser parser = new GraafvisParser(tokenStream);
        ErrorListener errorListener = new ErrorListener();
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        return parser.program();
    }


}