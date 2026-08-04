package tree.general;

import java.util.*;

public class SimpleGraph {

    private final Map<String, List<String>> ajList = new LinkedHashMap<>();

    public void addVertex(String vertex){
        ajList.putIfAbsent(vertex,new ArrayList<>());
    }

    public void addEdge(String source,String destination,boolean isDirected){
        addVertex(source);
        addVertex(destination);
        putEdge(source,destination);
        if(!isDirected){
            putEdge(destination,source);
        }
    }

    public void putEdge(String from,String to){
        ajList.get(from).add(to);
    }

    public void bfs(String startNode){
        if(!ajList.containsKey(startNode)) return;
        Set<String> visited =  new HashSet<>();
        Queue<String> queue =  new ArrayDeque<>();
        queue.add(startNode);
        visited.add(startNode);
        while (!queue.isEmpty()){
            String current = queue.poll();
            System.out.print(current+ " ");
            List<String> children = ajList.getOrDefault(current,Collections.emptyList());
            for(String child : children){
                if(!visited.contains(child)){
                    visited.add(child);
                    queue.add(child);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SimpleGraph graph = new SimpleGraph();
        graph.addEdge("A", "B", false);
        graph.addEdge("A", "C", false);
        graph.addEdge("B", "D", false);
        graph.addEdge("B", "E", false);
        graph.addEdge("C", "F", false);

        // 2. Chạy test BFS từ đỉnh "A"
        System.out.println("--- Kết quả BFS bắt đầu từ A ---");
        graph.bfs("B");
    }
}
