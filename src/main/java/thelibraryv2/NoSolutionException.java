package thelibraryv2;

public class NoSolutionException extends Exception {

    @Override
    public String getMessage() {
        return "No element that fits these parameters could be found.";
    }
}