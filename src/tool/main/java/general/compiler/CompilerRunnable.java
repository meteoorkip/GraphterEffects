package general.compiler;

import alice.tuprolog.InvalidTheoryException;
import graafvis.GraafvisCompiler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Compilation Runnable
 *
 * <P>This runnable is reponsible for executing the compilation.
 * When runned it will create a new (debug) {@link Compilation} and will
 * excecute the different compilation methods on the {@link Compilation}, e.g:{@link Compilation#compileGraafVis()}
 */

public class CompilerRunnable implements Runnable {

    private Path scriptFile;
    private Path graphFile;
    private Compilation compilation;
    private CompilationProgress maxCompilationProgress;

    /**
     * Constructor for a {@link CompilerRunnable} for a normal {@link Compilation}.
     *
     * @param scriptFile The path of where the Graafvis Script is stored
     * @param graphFile The path of where the Abstract Syntax Graph is stored
     *                  See //TODO{@link } which fileformats are suported.
     */
    public CompilerRunnable(Path scriptFile, Path graphFile){
        this.scriptFile = scriptFile;
        this.graphFile = graphFile;
        maxCompilationProgress = CompilationProgress.COMPILATIONFINISHED;
    }

    /**
     * Constructor for a {@link CompilerRunnable} for a debug {@link Compilation}. The compilation becomes a debug compilation
     * if the {@paramref maxProgress} is not {@link CompilationProgress#COMPILATIONFINISHED}.
     *
     * @param scriptFile The path of where the Graafvis Script is stored
     * @param graphFile The path of where the Abstract Syntax Graph is stored
     *                  See //TODO{@link } which fileformats are suported.
     * @param maxCompilationProgress The {@link CompilationProgress} until which the compilation is supposed to
     *                    continue.
     */
    public CompilerRunnable(Path scriptFile, Path graphFile, CompilationProgress maxCompilationProgress){
        this.scriptFile = scriptFile;
        this.graphFile = graphFile;
        this.maxCompilationProgress = maxCompilationProgress;

    }

    /**
     * Creates a new (debug) {@link Compilation}, and tries to excecute the compilation methods on it.
     * The compilation won't be started until {@link CompilationModel#allObserversAdded} has been signalled.
     */
    @Override
    public synchronized void run() {
        compilation = new Compilation(scriptFile, graphFile, maxCompilationProgress);
        CompilationModel.getInstance().setCompilation(compilation);
        try {
            while (CompilationModel.getInstance().countObservers() != compilation.countObservers()) {
                CompilationModel.getInstance().lock.lock();
                CompilationModel.getInstance().allObserversAdded.await();
                CompilationModel.getInstance().lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO: error resolving
        try {
            boolean hasSolution = false;
            if (maxCompilationProgress == CompilationProgress.COMPILATIONFINISHED) {
                compilation.compileGraafVis();
                compilation.addASCRLibrary();
                hasSolution = compilation.solve();
                if (hasSolution) {
                    compilation.generateSVG();
                }
                CompilerUtils.saveGeneratedSVG(graphFile.getFileName().toString().split("\\.")[0], compilation.getGeneratedSVG());
            } else {
                if (maxCompilationProgress.ordinal() >= CompilationProgress.GRAAFVISCOMPILED.ordinal()) {
                    compilation.compileGraafVis();
                }
                if (maxCompilationProgress.ordinal() >= CompilationProgress.GRAPHCONVERTED.ordinal()) {
                    compilation.addASCRLibrary();
                }
                if (maxCompilationProgress.ordinal() >= CompilationProgress.SOLVED.ordinal()) {
                    hasSolution = compilation.solve();
                }
                if (hasSolution && maxCompilationProgress.ordinal() >= CompilationProgress.SVGGENERATED.ordinal()) {
                    compilation.generateSVG();
                }
            }
        } catch (IOException e) {
            compilation.setException(e);
        } catch (InvalidTheoryException e) {
            compilation.setException(e);
        } catch (SAXException e) {
            compilation.setException(e);
        } catch (GraafvisCompiler.SyntaxException e) {
            compilation.setException(e);
        } catch (GraafvisCompiler.CheckerException e) {
            compilation.setException(e);
        }

    }


}