package tree.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

    /** Mot canh: di toi {@code target}, mang nhan {@code label} va trong so {@code weight}. */
    public record Edge<T>(T target, String label, int weight) {}

    public static final int DEFAULT_WEIGHT = 1;
    public static final String DEFAULT_LABEL = "";

    /**
     * source -> (target -> (label -> canh)).
     * Giua 1 cap dinh co the co NHIEU canh, moi canh mot nhan khac nhau.
     */
    private final Map<T, Map<T, Map<String, Edge<T>>>> ajList;
    private final boolean directed;

    public Graph(){
        this(false);
    }

    public Graph(boolean directed){
        this.ajList = new HashMap<>();
        this.directed = directed;
    }

    public boolean isDirected(){
        return directed;
    }

    public void addVertex(T vertex){
        ajList.putIfAbsent(vertex,new LinkedHashMap<>());
    }

    public void addEdge(T source,T destination){
        addEdge(source,destination,DEFAULT_LABEL,DEFAULT_WEIGHT);
    }

    public void addEdge(T source,T destination,int weight){
        addEdge(source,destination,DEFAULT_LABEL,weight);
    }

    /**
     * Them quan he {@code label} giua hai dinh. Cac nhan khac nhau ton tai song song;
     * them lai cung mot nhan thi chi cap nhat trong so.
     */
    public void addEdge(T source,T destination,String label,int weight){
        addVertex(source);
        addVertex(destination);

        putEdge(source,destination,label,weight);

        if(!directed){
            putEdge(destination,source,label,weight);
        }
    }

    private void putEdge(T from,T to,String label,int weight){
        ajList.get(from)
                .computeIfAbsent(to,k -> new LinkedHashMap<>())
                .put(label,new Edge<>(to,label,weight));
    }

    /** Xoa dung mot quan he. Tra ve true neu co canh bi xoa. */
    public boolean removeEdge(T source,T destination,String label){
        boolean removed = deleteEdge(source,destination,label);
        if(!directed){
            removed |= deleteEdge(destination,source,label);
        }
        return removed;
    }

    private boolean deleteEdge(T from,T to,String label){
        Map<T, Map<String, Edge<T>>> edges = ajList.get(from);
        if(edges == null){
            return false;
        }
        Map<String, Edge<T>> byLabel = edges.get(to);
        if(byLabel == null || byLabel.remove(label) == null){
            return false;
        }
        if(byLabel.isEmpty()){
            edges.remove(to); // het quan he -> khong con la hang xom nua
        }
        return true;
    }

    /** Co bat ky quan he nao giua source va destination khong? */
    public boolean hasEdge(T source,T destination){
        return !edgesBetween(source,destination).isEmpty();
    }

    public boolean hasEdge(T source,T destination,String label){
        return getEdge(source,destination,label) != null;
    }

    public Edge<T> getEdge(T source,T destination,String label){
        return edgesBetween(source,destination).get(label);
    }

    /** Tat ca quan he giua hai dinh: ban, dong nghiep, nguoi yeu... */
    public Collection<Edge<T>> getEdges(T source,T destination){
        return Collections.unmodifiableCollection(edgesBetween(source,destination).values());
    }

    /** Cac nhan cua quan he giua hai dinh. */
    public Set<String> getLabels(T source,T destination){
        return Collections.unmodifiableSet(edgesBetween(source,destination).keySet());
    }

    public int getWeight(T source,T destination,String label,int defaultValue){
        Edge<T> edge = getEdge(source,destination,label);
        return edge == null ? defaultValue : edge.weight();
    }

    /** Chi lay cac dinh ke - dung cho BFS/DFS khi khong quan tam nhan/trong so. */
    public Set<T> getNeighbors(T vertex){
        return Collections.unmodifiableSet(ajList.getOrDefault(vertex,Collections.emptyMap()).keySet());
    }

    /** Toan bo canh di ra tu vertex (mot hang xom co the xuat hien nhieu lan, moi lan mot nhan). */
    public List<Edge<T>> getEdges(T vertex){
        List<Edge<T>> result = new ArrayList<>();
        for (Map<String, Edge<T>> byLabel: ajList.getOrDefault(vertex,Collections.emptyMap()).values()){
            result.addAll(byLabel.values());
        }
        return Collections.unmodifiableList(result);
    }

    public Set<T> getVertices(){
        return Collections.unmodifiableSet(ajList.keySet());
    }

    private Map<String, Edge<T>> edgesBetween(T source,T destination){
        return ajList.getOrDefault(source,Collections.emptyMap())
                .getOrDefault(destination,Collections.emptyMap());
    }

    /**
     * Sinh so do Mermaid de dan vao mermaid.live, README tren GitHub hay Markdown preview cua IDE.
     * Do thi co huong dung mui ten, vo huong dung duong noi va moi canh chi ve mot lan.
     */
    public String toMermaid(){
        StringBuilder sb = new StringBuilder("flowchart LR\n");

        Map<T, String> ids = new LinkedHashMap<>();
        for (T vertex: ajList.keySet()){
            String id = "n" + ids.size();
            ids.put(vertex,id);
            sb.append("    ").append(id).append("[\"").append(escapeMermaid(vertex)).append("\"]\n");
        }

        String connector = directed ? "-->" : "---";
        Set<String> drawn = new HashSet<>();

        for (Map.Entry<T, Map<T, Map<String, Edge<T>>>> source: ajList.entrySet()){
            String from = ids.get(source.getKey());
            for (Map.Entry<T, Map<String, Edge<T>>> target: source.getValue().entrySet()){
                String to = ids.get(target.getKey());
                for (Edge<T> edge: target.getValue().values()){
                    // do thi vo huong luu ca hai chieu -> chi ve chieu gap dau tien
                    if(!directed && !drawn.add(undirectedKey(from,to,edge.label()))){
                        continue;
                    }
                    sb.append("    ").append(from).append(" ").append(connector);
                    String text = edgeText(edge);
                    if(!text.isEmpty()){
                        sb.append("|\"").append(text).append("\"|");
                    }
                    sb.append(" ").append(to).append("\n");
                }
            }
        }
        return sb.toString();
    }

    private String edgeText(Edge<T> edge){
        if(edge.label().isEmpty()){
            return edge.weight() == DEFAULT_WEIGHT ? "" : String.valueOf(edge.weight());
        }
        return edge.label() + " (" + edge.weight() + ")";
    }

    private String undirectedKey(String from,String to,String label){
        String a = from.compareTo(to) <= 0 ? from : to;
        String b = from.compareTo(to) <= 0 ? to : from;
        return a + "|" + b + "|" + label;
    }

    private String escapeMermaid(Object value){
        return String.valueOf(value).replace("\"","#quot;");
    }

    public void printGraph(){
        for (T vertex: ajList.keySet()){
            StringBuilder sb = new StringBuilder("vertex " + vertex + " connected to [");
            String sep = "";
            for (Map.Entry<T, Map<String, Edge<T>>> entry: ajList.get(vertex).entrySet()){
                sb.append(sep).append(entry.getKey()).append("{");
                String innerSep = "";
                for (Edge<T> edge: entry.getValue().values()){
                    sb.append(innerSep).append(edge.label()).append("=").append(edge.weight());
                    innerSep = ", ";
                }
                sb.append("}");
                sep = ", ";
            }
            System.out.println(sb.append("]"));
        }
    }

    public static void main(String[] args) {
        Graph<String> social = new Graph<>();
        social.addEdge("Andi","Ha","ban",5);
        social.addEdge("Andi","Ha","dong nghiep",3);
        social.addEdge("Andi","Ha","nguoi yeu",10);
        social.addEdge("Andi","Ha","ban",8);
        social.addEdge("Andi","Hien","dong nghiep",2);
        System.out.println("=== undirected ===");
        social.printGraph();
        System.out.println("Andi-Ha: " + social.getLabels("Andi","Ha"));
        System.out.println("trong so 'ban' = " + social.getWeight("Andi","Ha","ban",-1));
        System.out.println("hang xom cua Andi = " + social.getNeighbors("Andi"));
        System.out.println("--- mermaid ---");
        System.out.println(social.toMermaid());

        social.removeEdge("Andi","Ha","dong nghiep"); // chia tay nhung van con la ban
        System.out.println("sau khi xoa 'nguoi yeu': " + social.getLabels("Ha","Andi"));

        Graph<String> directed = new Graph<>(true);
        directed.addEdge("Andi","Ha","theo doi",1);
        System.out.println("=== directed ===");
        directed.printGraph();
        System.out.println("Ha -> Andi ? " + directed.hasEdge("Ha","Andi"));
        System.out.println("--- mermaid ---");
        System.out.println(directed.toMermaid());
    }
}
