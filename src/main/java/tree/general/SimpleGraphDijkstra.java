package tree.general;


import java.util.*;

public class SimpleGraphDijkstra {

    // 1. Cấu trúc lưu một Cạnh có trọng số
    private static class Edge {
        String destination;
        int weight;

        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // 2. Cấu trúc hỗ trợ cho PriorityQueue (Lưu đỉnh và tổng chi phí từ startNode)
    private static class NodeState {
        String vertex;
        int currentDistance;

        public NodeState(String vertex, int currentDistance) {
            this.vertex = vertex;
            this.currentDistance = currentDistance;
        }
    }

    // Danh sách kề: Đỉnh -> Danh sách các Cạnh (Edge)
    private final Map<String, List<Edge>> ajList = new LinkedHashMap<>();

    public void addVertex(String vertex) {
        ajList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Thêm cạnh có TRỌNG SỐ (weight)
    public void addEdge(String source, String destination, int weight, boolean isDirected) {
        addVertex(source);
        addVertex(destination);
        putEdge(source, destination, weight);
        if (!isDirected) {
            putEdge(destination, source, weight);
        }
    }

    public void putEdge(String from, String to, int weight) {
        ajList.get(from).add(new Edge(to, weight));
    }

    /**
     * Thuật toán Dijkstra: Tìm đường đi ngắn nhất từ startNode tới targetNode
     */
    public void dijkstra(String startNode, String targetNode) {
        if (!ajList.containsKey(startNode) || !ajList.containsKey(targetNode)) {
//            System.out.println("Đỉnh không tồn tại trong đồ thị!");
            return;
        }

        // Map lưu khoảng cách ngắn nhất từ startNode đến từng đỉnh
        Map<String, Integer> dist = new HashMap<>();
        // Map lưu vết (Con -> Cha) để phục vụ truy vết đường đi
        Map<String, String> parentMap = new HashMap<>();

        // Khởi tạo khoảng cách ban đầu = Vô cùng (Infinity)
        for (String node : ajList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(startNode, 0); // Khoảng cách từ A đến chính nó = 0

        // PriorityQueue: Ưu tiên đỉnh có currentDistance nhỏ nhất lên đầu
        PriorityQueue<NodeState> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.currentDistance));
        pq.add(new NodeState(startNode, 0));

        while (!pq.isEmpty()) {
            NodeState current = pq.poll();
            String u = current.vertex;
            int currentDist = current.currentDistance;

            // Nếu đã tìm thấy đỉnh đích -> Dừng sớm (vì chi phí từ PQ lấy ra đã là tối ưu)
            if (u.equals(targetNode)) {
                break;
            }

            // Nếu chi phí này lớn hơn chi phí tối ưu đã biết -> Bỏ qua
            if (currentDist > dist.get(u)) {
                continue;
            }

            // Duyệt tất cả các cạnh lân cận của u
            List<Edge> neighbors = ajList.getOrDefault(u, Collections.emptyList());
            for (Edge edge : neighbors) {
                String v = edge.destination;
                int weight = edge.weight;

                // NẾU: Khoảng cách qua u đến v TỐT HƠN khoảng cách cũ tới v
                if (dist.get(u) + weight < dist.get(v)) {
                    // Cập nhật lại khoảng cách ngắn nhất mới cho v
                    dist.put(v, dist.get(u) + weight);
                    // Lưu lại vết
                    parentMap.put(v, u);
                    // Thêm vào PriorityQueue để tiếp tục loang
                    pq.add(new NodeState(v, dist.get(v)));
                }
            }
        }

        // --- BƯỚC IN KẾT QUẢ ---
        if (dist.get(targetNode) == Integer.MAX_VALUE) {
            System.out.println("Không có đường đi từ " + startNode + " tới " + targetNode);
            return;
        }

        System.out.println("Tổng chi phí ngắn nhất từ " + startNode + " -> " + targetNode + " là: " + dist.get(targetNode));

        // Truy vết ngược từ targetNode về startNode
        List<String> path = new ArrayList<>();
        String curr = targetNode;
        while (curr != null) {
            path.add(curr);
            curr = parentMap.get(curr);
        }
        Collections.reverse(path);

        System.out.println("Chi tiết đường đi: " + String.join(" -> ", path));
    }

    public static void main(String[] args) {
        SimpleGraphDijkstra graph = new SimpleGraphDijkstra();

        // Tạo đồ thị có trọng số (isDirected = false là đồ thị vô hướng)
        // Cạnh (A, B, 4) nghĩa là đi giữa A và B tốn chi phí/khoảng cách = 4
        graph.addEdge("A", "B", 4, false);
        graph.addEdge("A", "C", 2, false);
        graph.addEdge("B", "D", 5, false);
        graph.addEdge("B", "C", 1, false);
        graph.addEdge("C", "E", 10, false);
        graph.addEdge("B", "E", 3, false);
        graph.addEdge("D", "F", 2, false);
        graph.addEdge("E", "F", 1, false);

        System.out.println("--- Kết quả Dijkstra từ A tới F ---");
        graph.dijkstra("A", "F");
    }
}