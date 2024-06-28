package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Digraph
{
    protected static final String NEWLINE = System.getProperty("line.separator");
    private Map<Vertex, List<Vertex>> graph;

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
                int[] nums= new int[3];
                if (parts.length == 3) {
                    int x = Integer.parseInt(parts[0]);
                    nums[0]= x;
                    int y = Integer.parseInt(parts[1]);
                    nums[1]= y;
                    int z = Integer.parseInt(parts[2]);
                    nums[2]= z;
                    Arrays.sort(nums);
                    Vertex vertex = new Vertex(nums[2], nums[1], nums[0]);
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
                System.out.println("\nvertice: "+vertex+"\n\n"+getAdj(vertex)+"\n\n-----------------------------------------"); 
            }
            System.out.println("digraph {");
            System.out.println("rankdir = LR;");
            System.out.println("node [shape = circle];");
            for (Vertex vertex : vertices) {
                System.out.println(toDot(vertex));
            }
            System.out.println("}");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEdge(Vertex v, Vertex w) {
        addToList(v, w);
    }

    private void addToList(Vertex v, Vertex w) {
        List<Vertex> list = graph.get(v);
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

    public String toDot(Vertex v) {
		StringBuilder s = new StringBuilder();
        if(graph.get(v)==null){
            return "";
        }
		for (int i=0;i<graph.get(v).size();i++) {
            Vertex w = graph.get(v).get(i);
			s.append(v + " -> " + w + ";" + NEWLINE);
		}
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='[' || s.charAt(i)==']'){
                s.deleteCharAt(i);
                s.replace(i, i, "\"");
            }
        }
		return s.toString();
	}

}