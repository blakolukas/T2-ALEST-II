package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Digraph
{
    protected static final String NEWLINE = System.getProperty("line.separator");
    private Map<Vertex, LinkedList<Vertex>> graph;

    public Digraph(String file){
        graph = new HashMap<>();
        carrega(file);
    }

    private void carrega(String file){
        List<Vertex> vertices= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    int z = Integer.parseInt(parts[2]);
                    Vertex vertex = new Vertex(x, y, z);
                    System.out.println(vertex);
                    vertices.add(vertex);
                }
            }
            
            for (Vertex vertex : vertices) {
                for(int i=0; i<vertices.size();i++){
                    if(vertex.compareTo(vertices.get(i))==-1){
                        addEdge(vertex, vertices.get(i));
                    }
                }
            }
            
            for (Vertex vertex : vertices) {
                System.out.println("vertice: "+vertex+"\n\n"+getAdj(vertex)); 
            }
        
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEdge(Vertex v, Vertex w) {
        addToList(v, w);
    }

    private void addToList(Vertex v, Vertex w) {
        LinkedList<Vertex> list = graph.get(v);
        if(list == null){
            list = new LinkedList<>();
        }
        list.add(w);
        graph.put(v, list);
    }

    public Iterable<Vertex> getAdj(Vertex v){ 
        return graph.get(v); 
    }

    @Override
    public String toString() {
        return "Digraph [graph=" + graph + "]";
    }

}