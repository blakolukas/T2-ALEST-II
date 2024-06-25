package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Digraph
{
    private Map<Vertex, List<Vertex>> graph;

    public Digraph(String file){
        graph = new HashMap<>();
        carrega(file);
    }

    private void carrega(String file){
        int values[]= new int[3];
        List<Vertex> vertices= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line=reader.readLine()) != null){
                int x=0;
                int y=0;
                int z=0;
                int prev=0;

                for(int i=0; i<line.length();i++){
                    if(line.charAt(i)==' '){
                        prev=i+1;
                        values[0]=x;
                        break;
                    }
                    x = x * 10 + Character.getNumericValue(line.charAt(i));
                }

                for(int i=prev; i<line.length();i++){
                    if(line.charAt(i)==' '){
                        prev=i+1;
                        values[1]=y;
                        break;
                    }
                    y = y * 10 + Character.getNumericValue(line.charAt(i));
                }

                for(int i=prev; i<line.length();i++){
                    z = z * 10 + Character.getNumericValue(line.charAt(i));
                    if(i==line.length()-1){
                        values[2]=z;
                        break;
                    }
                }
                Arrays.sort(values);
                Vertex vertice= new Vertex(values[0], values[1], values[2]);
                System.out.println(vertice); 
                vertices.add(vertice);
            }
            
            for (Vertex vertex : vertices) {
                for(int i=0; i<vertices.size()-1;i++){
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
        List<Vertex> list = graph.get(v);
        if(list == null) list = new LinkedList<>();
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