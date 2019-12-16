public class BSTTester {

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTreeImpl();
        /*bst.add(generateNode(3));
        bst.add(generateNode(2));
        bst.add(generateNode(1));
        bst.add(generateNode(8));
        bst.add(generateNode(7));
        bst.add(generateNode(5));*//*
        bst.add(generateNode(3));
        bst.add(generateNode(1));
        bst.add(generateNode(4));
        *//*bst.search(generateNode(3));
        bst.search(generateNode(9));
        bst.search(generateNode(5));
        bst.search(generateNode(1));*//*
        bst.delete(3);
        System.out.println(bst.search(generateNode(3)));*/

        bst.add(generateNode(30));
        bst.add(generateNode(20));
        bst.add(generateNode(40));
        bst.add(generateNode(10));
        bst.add(generateNode(25));
        bst.add(generateNode(35));
        bst.add(generateNode(45));
        bst.add(generateNode(42));
        bst.add(generateNode(43));
        bst.add(generateNode(32));
        bst.delete(40);
    }

    private static Node generateNode(int value) {

        return new Node(value, null, null);
    }
}
