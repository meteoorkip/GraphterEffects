package general.compiler;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Term;
import graphloader.Importer;
import solver.SolveResults;
import solver.Solver;
import svg.SvgDocumentGenerator;
import graafvis.GraafvisCompiler;
import graafvis.errors.VisError;
import graafvis.warnings.Warning;
import org.dom4j.Document;
import org.graphstream.graph.Graph;
import org.xml.sax.SAXException;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Compilation Object.
 *
 * <P>This is a data object for a compilation process.
 * It contains all the methods necessary to advance compilation as well as
 * all the data generated during the compilation process.
 */

public class Compilation extends Observable{

    private Path scriptFile;
    private Path graphFile;

    private CompilationProgress maxProgress;
    private Graph graph;
    private List<Term> scriptRules;
    private SolveResults solveResults;
    private Document generatedSVG;
    private Exception exception;
    private GraafvisCompiler compiler;

    /**
     * Constructor for a normal compilation.
     *
     * @param scriptFile The path of where the Graafvis Script is stored
     * @param graphFile The path of where the Abstract Syntax Graph is stored
     *                  See //TODO{@link } which fileformats are suported.
     */
    public Compilation(Path scriptFile, Path graphFile){
        this.scriptFile = scriptFile;
        this.graphFile = graphFile;
        this.maxProgress = CompilationProgress.COMPILATIONFINISHED;
        this.compiler = new GraafvisCompiler();
    }

    /**
     * Constructor for a debug compilation. The compilation becomes a debug compilation
     * if the {@paramref maxProgress} is not {@link CompilationProgress#COMPILATIONFINISHED}.
     *
     * @param scriptFile The path of where the Graafvis Script is stored
     * @param graphFile The path of where the Abstract Syntax Graph is stored
     *                  See //TODO{@link } which fileformats are suported.
     * @param maxProgress The {@link CompilationProgress} until which the compilation is supposed to
     *                    continue.
     */
    public Compilation(Path scriptFile, Path graphFile, CompilationProgress maxProgress){
        this.scriptFile = scriptFile;
        this.graphFile = graphFile;
        this.maxProgress = maxProgress;
        this.compiler = new GraafvisCompiler();
    }

    /**
     * Adds the ASCR Library to the Compilation
     * @throws IOException Thrown when an the {@link Graph} could not be loaded from disk.
     * @throws SAXException Thrown when the {@link Graph} file contains syntax errors.
     */
    public void addGraph() throws IOException, SAXException {
        graph = Importer.graphFromFile(graphFile.toFile());
        setChanged();
        notifyObservers(CompilationProgress.GRAPHCONVERTED);
    }


    /**
     * Compiles the Graafvis Script and notifies its observers about its progress.
     * @throws IOException
     * @throws GraafvisCompiler.SyntaxException
     * @throws GraafvisCompiler.CheckerException
     */
    public void compileGraafVis() throws IOException, GraafvisCompiler.SyntaxException, GraafvisCompiler.CheckerException {
        /* Get a string representation of the script */
        String script = FileUtils.readFromFile(scriptFile.toFile());
        scriptRules = compiler.compile(script);
        setChanged();
        notifyObservers(CompilationProgress.GRAAFVISCOMPILED);
    }

    /**
     * Solves the system of logical rules, given by {@link Compilation#scriptRules} and {@link Compilation#graph},
     * solves the constraints generated by these rules. //TODO: Betere benaming dan "generated by these rules", er wordt eigenlijk niks gegenereerd, er wordt wat opgelost.
     * and notifies its observers abouts its progress.
     * The resulting Visualization Elements are put into {@link Compilation#solveResults}
     * //TODO: User libraries?
     * @throws InvalidTheoryException
     */
    public boolean solve() throws InvalidTheoryException {
        List<Term> rules = new LinkedList<>();
        rules.addAll(scriptRules);
        Solver solver = new Solver();
        solveResults = solver.solve(graph, rules);
        setChanged();
        if (solveResults.isSucces()) {
            notifyObservers(CompilationProgress.SOLVED);
            return true;
        } else {
            notifyObservers(CompilationProgress.NOSOLUTION);
            return false;
        }
    }

    /**
     * Generates an SVG based upon the Visualization Elements in the {@link Compilation#solveResults}
     * and notifies its observers abouts its progress.
     */
    public void generateSVG() {
        generatedSVG = SvgDocumentGenerator.generate(solveResults.getVisMap().values());
        setChanged();
        notifyObservers(CompilationProgress.SVGGENERATED);
    }

    /** Sets the encountered exception and notifies its observers abouts the encountered exception*/
    public void setException(Exception exception){
        this.exception = exception;
        setChanged();
        notifyObservers(CompilationProgress.ERROROCCURED);
    }

    /** Returns if this compilation is a debug compilation. The compilation is a debug compilation
     * if the {@link Compilation#maxProgress} is not {@link CompilationProgress#COMPILATIONFINISHED}.
     * @return if this compilation is a debug compilation
     * */
    public boolean isDebug(){
        return !maxProgress.equals(CompilationProgress.COMPILATIONFINISHED);
    }

    /**
     * @return the {@link CompilationProgress} until which the compilation is supposed to continue.
     */
    public CompilationProgress getMaxProgress(){
        return maxProgress;
    }

    /**
     *  the exception encountered during the compilation which caused the compilation to be block
     * from continueing.
     * @return the encountered exception
     */
    public Exception getException(){
        return exception;
    }

    /**
     * @return the generated SVG
     */
    public Document getGeneratedSVG() {
        return generatedSVG;
    }

    /**
     * @return the map of the generated visualization elements.
     */
    public SolveResults getSolveResults(){
        return solveResults;
    }

    public List<VisError> getErrors() {
        return compiler.getErrors();
    }

    public List<Warning> getWarnings() {
        return compiler.getWarnings();
    }

    public Path getGraphFile() {
        return graphFile;
    }
}
