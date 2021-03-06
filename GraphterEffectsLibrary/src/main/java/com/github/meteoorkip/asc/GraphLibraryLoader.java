package com.github.meteoorkip.asc;

import org.graphstream.graph.Graph;

public interface GraphLibraryLoader<T extends GraphLibrary> {
    T getInstance(Graph graph);
}
