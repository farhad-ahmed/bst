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
    }

    private static Node generateNode(int value) {

        return new Node(value, null, null);
    }
}
