package utils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public final class FileUtilsTest {

    @Test
    public void testBase64() throws IOException {
        String res = FileUtils.ImageToBase64(FileUtils.fromResources("testimage.jpg"));
        assertEquals("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", res);
    }

    @Test
    public void testReadStringFromFile() throws IOException {
        assertEquals(FileUtils.readFromFile(FileUtils.fromResources("asrc_testgraphs/graph1.dot")),
                "//A simple undirected graph\r\n" +
                "graph graphname {\r\n" +
                "    a -- b -- c;\r\n" +
                "    b -- d;\r\n" +
                "}\r\n");
    }
}
