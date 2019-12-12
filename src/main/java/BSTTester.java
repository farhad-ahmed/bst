public class BSTTester {

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTreeImpl();
        bst.add(generateNode(3));
        bst.add(generateNode(2));
        bst.add(generateNode(1));
        bst.add(generateNode(8));
        bst.add(generateNode(7));
        bst.add(generateNode(5));
        bst.add(generateNode(3));
        bst.search(generateNode(3));
        bst.search(generateNode(9));
        bst.search(generateNode(5));
        bst.search(generateNode(1));
    }

    private static Node generateNode(int value) {

        return new Node(value, null, null);
    }
}
