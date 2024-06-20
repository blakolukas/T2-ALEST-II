package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Digraph
{
    private Map<String, List<String>> graph;

    public Digraph(){
        graph = new HashMap<>();
    }

    public void addEdge(String v, String w) {
        addToList(v, w);
    }

    private void addToList(String v, String w) {
        List<String> list = graph.get(v);
        if(list == null) list = new LinkedList<>();
        list.add(w);
        graph.put(v, list);
    }

    public Iterable<String> getAdj(String v){ 
        return graph.get(v); 
    }

    
    
}