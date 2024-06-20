import src.Digraph;

public class Main {
    public static void main(String[] args) {
        Digraph a= new Digraph();
        a.addEdge("lucas", "isa");
        a.addEdge("isa", "me");
        a.addEdge("lucas", "eu");
        a.getAdj("lucas");
        System.err.println(a.getAdj("lucas"));
    }
}