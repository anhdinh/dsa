package tree.general;

public class AVLTree {

    private Node root;

    public AVLTree() {
        root = null;
    }

    Node getRoot() {
        return root;
    }

    private int getHeight(Node node){
        if (node == null) return 0;
        return node.getHeight();
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.getValue())
            node.setLeft(insert(node.getLeft(), value));
        else if (value > node.getValue())
            node.setRight(insert(node.getRight(), value));
        else
            throw new IllegalArgumentException("Giá trị " + value + " đã tồn tại");

        // TODO 1: cập nhật height của node hiện tại
         node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);

        // TODO 2: tính balance factor
         int balance = getHeight(node.getLeft()) - getHeight(node.getRight());

        // TODO 3: 4 trường hợp mất cân bằng
        // left-left    (balance > 1  && value < node.getLeft().getValue())  -> return rotateRight(node);
        if(balance>1 && node.getLeft().getValue()> value){
            node = rotateRight(node);
        }
        // right-right  (balance < -1 && value > node.getRight().getValue()) -> return rotateLeft(node);
        if(balance<-1 && node.getRight().getValue()< value){
            node =  rotateLeft(node);
        }

        // left-right   (balance > 1  && value > node.getLeft().getValue())  -> rotateLeft trên con trái rồi rotateRight(node);
        if(balance>1 && node.getLeft().getValue()<value){
            node.setLeft(rotateLeft(node.getLeft()));
            node = rotateRight(node);

        }
        // right-left   (balance < -1 && value < node.getRight().getValue()) -> rotateRight trên con phải rồi rotateLeft(node);

        if(balance<-1&& value<node.getRight().getValue()){
            node.setRight(rotateRight(node.getRight()));
            node = rotateLeft(node);
        }

        return node;
    }

    private int getBalance(Node node) {
        if (node == null) return 0;
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    public void remove(int value) {
        root = remove(root, value);
    }

    // ============ BƯỚC 1: ĐI XUỐNG — tìm node cần xóa ====================================
    // Đệ quy từ root đi xuống, dựa vào value để rẽ trái hoặc phải.
    //
    // ============ BƯỚC 2: XÓA — 3 trường hợp ===========================================
    //   - Node lá hoặc 1 con: trả về con còn lại (hoặc null nếu không có con).
    //   - Node 2 con: tìm successor (min bên phải), copy giá trị vào node hiện tại,
    //     rồi đệ quy xóa chính successor đó khỏi cây con phải.
    //
    // ============ BƯỚC 3: ĐI NGƯỢC LÊN — rebalance =====================================
    // Sau khi đệ quy trả về, tại MỖI node trên đường đi lên root:
    //   1. Cập nhật height
    //   2. Tính balance factor
    //   3. Nếu |balance| > 1 → xoay (LL / LR / RR / RL)
    // Điều kiện xoay dùng balance của CON (không dùng value như insert) để biết
    // cây con bên đó đang nghiêng về phía nào.
    // ===================================================================================
    private Node remove(Node node, int value) {
        // Chạm đáy — không tìm thấy value
        if (node == null) return null;

        // --- ĐI XUỐNG: value nhỏ hơn node hiện tại → rẽ trái ---
        if (value < node.getValue()) {
            // Gọi đệ quy xóa bên trái, gán kết quả vào node.left
            node.setLeft(remove(node.getLeft(), value));
        }
        // --- ĐI XUỐNG: value lớn hơn node hiện tại → rẽ phải ---
        else if (value > node.getValue()) {
            // Gọi đệ quy xóa bên phải, gán kết quả vào node.right
            node.setRight(remove(node.getRight(), value));
        }
        // --- TÌM THẤY: value == node.getValue() → thực hiện xóa ---
        else {
            // Trường hợp 1 & 2: node lá hoặc node 1 con
            if (node.getLeft() == null || node.getRight() == null) {
                // Lấy con không null (nếu có), hoặc null nếu là node lá
                Node temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                // Gán node = con đó (hoặc null) — node hiện tại bị "cắt" khỏi cây
                node = temp;
            }
            // Trường hợp 3: node có 2 con
            else {
                // Tìm successor = node nhỏ nhất bên phải
                Node successor = findMin(node.getRight());
                // Ghi đè value của node hiện tại = value của successor
                node.setValue(successor.getValue());
                // Đệ quy xóa successor khỏi cây con phải
                node.setRight(remove(node.getRight(), successor.getValue()));
            }
        }

        // --- ĐI NGƯỢC LÊN: nếu node đã bị xóa (thành null) thì dừng ---
        if (node == null) return null;

        // --- 1. Cập nhật height của node hiện tại ---
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);

        // --- 2. Tính balance factor của node hiện tại ---
        int balance = getBalance(node);

        // --- 3. Xác định loại mất cân bằng và xoay ---
        // LL: lệch trái và cây con trái không nghiêng phải (balance con trái >= 0)
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rotateRight(node);
        }
        // LR: lệch trái và cây con trái đang nghiêng phải (balance con trái < 0)
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }
        // RR: lệch phải và cây con phải không nghiêng trái (balance con phải <= 0)
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return rotateLeft(node);
        }
        // RL: lệch phải và cây con phải đang nghiêng trái (balance con phải > 0)
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        // Cân bằng — trả node về cho cha
        return node;
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private Node rotateRight(Node y){
        var x = y.getLeft();
        var temp = x.getRight();
        x.setRight(y);
        y.setLeft(temp);
        y.setHeight(Math.max(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);
        x.setHeight(Math.max(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        return x;
    }

    private Node rotateLeft(Node x){
        var y = x.getRight();
        var temp = y.getLeft();
        y.setLeft(x);
        x.setRight(temp);
        x.setHeight(Math.max(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        y.setHeight(Math.max(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);
        return y;
    }


}
