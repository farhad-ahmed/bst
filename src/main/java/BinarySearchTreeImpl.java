import java.util.HashMap;
import java.util.Map;

public class BinarySearchTreeImpl implements BinarySearchTree {

    private Node root;

    public BinarySearchTreeImpl() {
        root = null;
    }

    public boolean add(Node newNode) {
        return add(this.root, newNode);
    }

    public boolean search(Node searchingNode) {
        return search(this.root, searchingNode);
    }

    public boolean delete(int value) {
        Map<String, Node> map = getParentAndChild(null, this.root, value);
        if (map == null) {
            return false;
        }

        Node nodeToBeDeleted = map.get("child");
        Node parentOfTheLostChild = map.get("parent");

        //leaf node
        if (nodeToBeDeleted.getLeft() == null && nodeToBeDeleted.getRight() == null) {
            swapNodeReference(nodeToBeDeleted, parentOfTheLostChild, null);
        }

        //removed node has only left child
        else if (nodeToBeDeleted.getLeft() != null && nodeToBeDeleted.getRight() == null) {
            swapNodeReference(nodeToBeDeleted, parentOfTheLostChild, nodeToBeDeleted.getLeft());
        }
        // removed node has only right child
        else if (nodeToBeDeleted.getRight() != null && nodeToBeDeleted.getLeft() == null) {
            swapNodeReference(nodeToBeDeleted, parentOfTheLostChild, nodeToBeDeleted.getRight());
        }

        //node has two child
        else if (nodeToBeDeleted.getRight() != null && nodeToBeDeleted.getLeft() != null) {

            Map<String, Node> inOrderMap = findInOrderPredecessor(nodeToBeDeleted, nodeToBeDeleted.getLeft());
            Node predecessorParent = inOrderMap.get("parent");
            Node inOrderPredecessor = inOrderMap.get("child");

            // prefer to have a leaf node, so we don't have to reorganized the tree a lot
            if (inOrderPredecessor.getLeft() == null && inOrderPredecessor.getRight() == null) {
                nodeToBeDeleted.setValue(inOrderPredecessor.getValue());
                predecessorParent.setLeft(null);
                return true;
            }
            //can only have left child
            else if (inOrderPredecessor.getLeft() != null) {
                predecessorParent.setLeft(inOrderPredecessor.getLeft());
                nodeToBeDeleted.setValue(inOrderPredecessor.getValue());
                return true;
            }

        }

        return true;
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

    private boolean search(Node currentNode, Node searchingNode) {

        if (currentNode == null) {
            System.out.println(String.format("Node %s not found", searchingNode.getValue()));
            return false;
        } else if (currentNode.getValue() == searchingNode.getValue()) {
            System.out.println(String.format("Node %s found", searchingNode.getValue()));
            return true;
        } else if (searchingNode.getValue() > currentNode.getValue()) {
            return search(currentNode.getRight(), searchingNode);
        } else if (searchingNode.getValue() < currentNode.getValue()) {
            return search(currentNode.getLeft(), searchingNode);
        }
        return false;
    }

    private Map<String, Node> getParentAndChild(Node parentNode, Node childNode, int value) {

        if (childNode.getValue() == value) {
            Map<String, Node> parentChildMap = new HashMap<String, Node>();
            parentChildMap.put("parent", parentNode);
            parentChildMap.put("child", childNode);
            return parentChildMap;
        } else if (value > childNode.getValue()) {

            if (childNode.getRight() == null) {
                System.out.println("Node not found " + value);
                return null;
            }
            return getParentAndChild(childNode, childNode.getRight(), value);
        } else {

            if (childNode.getLeft() == null) {
                System.out.println("Node not found " + value);
            }

            return getParentAndChild(childNode, childNode.getLeft(), value);
        }
    }

    private void swapNodeReference(Node nodeToBeDeleted, Node parentOfTheLostChild, Node replaceNode) {

        //
        if (parentOfTheLostChild == null) {
            this.root = replaceNode;
            return;
        }

        if (nodeToBeDeleted.getValue() > parentOfTheLostChild.getValue()) {
            parentOfTheLostChild.setRight(replaceNode);
        } else {
            parentOfTheLostChild.setLeft(replaceNode);
        }
    }

    private Map<String, Node> findInOrderPredecessor(Node nodeToBeRemoved, Node subRootNode) {


        if (subRootNode.getRight() == null) {
            Map<String, Node> map = new HashMap<String, Node>();
            map.put("parent", nodeToBeRemoved);
            map.put("child", subRootNode);
            return map;
        } else {
            return findInOrderPredecessor(subRootNode, subRootNode.getRight());
        }
    }
}
