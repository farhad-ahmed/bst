public class BinarySearchTreeImpl implements BinarySearchTree {

    private Node root;

    public BinarySearchTreeImpl() {
        root = null;
    }

    public boolean add(Node newNode) {
        return add(this.root, newNode);
    }

    private boolean add(Node currentNode, Node newNode) {

        if (root == null) {
            this.root = newNode;
            System.out.println(String.format("%s is root", newNode.getValue()));
            return true;
        }

        //add to left branch
        if (newNode.getValue() < currentNode.getValue()) {
            if (currentNode.getLeft() != null) {
                return add(currentNode.getLeft(), newNode);
            } else {
                currentNode.setLeft(newNode);
                System.out.println(String.format("%s node is added to as left branch of current node %s", newNode.getValue(), currentNode.getValue()));
                return true;
            }
        }

        //add to right branch
        else if (newNode.getValue() > currentNode.getValue()) {
            if (currentNode.getRight() != null) {
                return add(currentNode.getRight(), newNode);
            } else {
                currentNode.setRight(newNode);
                System.out.println(String.format("%s node is added to as right branch of current node %s", newNode.getValue(), currentNode.getValue()));
                return true;
            }
        }
        System.out.println("Can't add duplicate value " + newNode.getValue());
        return false;
    }
}