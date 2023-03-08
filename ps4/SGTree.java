/**
 * ScapeGoat Tree class
 *
 * This class contains some of the basic code for implementing a ScapeGoat tree.
 * This version does not include any of the functionality for choosing which node
 * to scapegoat.  It includes only code for inserting a node, and the code for rebuilding
 * a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}

    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        // TODO: Implement this
        if (child == Child.LEFT) {
            return countNodes(node.left);
        }
        else {
            return countNodes(node.right);
        }
    }

    private int countNodes(TreeNode node) {
        // TODO: Implement this
        if (node == null) {
            return 0;
        }
        else {
            return 1 + countNodes(node.left) + countNodes(node.right);
        }
    }

    /**
     * Builds an array of nodes in the specified subtree
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */

    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        // TODO: Implement this
        TreeNode[] inorder = new TreeNode[countNodes(node, child)];
        if (child == Child.LEFT) {
            addNodes(node.left, inorder, 0);
        }
        else {
            addNodes(node.right, inorder, 0);
        }
        return inorder;
    }

    private int addNodes(TreeNode node, TreeNode[] inorder, int idx) { //returns the next idx to be used
        if (node == null) {
            return idx;
        }
        else {
            idx = addNodes(node.left, inorder, idx); //updates idx value
            inorder[idx] = node;
            return addNodes(node.right, inorder, idx + 1);
        }
    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */

    public TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this
        return buildTreeHelper(nodeList, 0, nodeList.length);
    }

    private TreeNode buildTreeHelper(TreeNode[] nodeList, int start, int end) {
        // TODO: Implement this
        if (end - start != 0) {
            int mid = (start + end) / 2;
            TreeNode node = nodeList[mid];
            node.left = buildTreeHelper(nodeList, start, mid);
            node.right = buildTreeHelper(nodeList, mid + 1, end);
            return node;
        }
        else {
            return null;
        }
    }

    /**
    * Rebuilds the specified subtree of a node
    * 
    * @param node the part of the subtree to rebuild
    * @param child specifies which child is the root of the subtree to rebuild
    */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
        } else if (child == Child.RIGHT) {
            node.right = newChild;
        }
    }

    /**
    * Inserts a key into the tree
    *
    * @param key the key to insert
    */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) break;
                node = node.left;
            } else {
                if (node.right == null) break;
                node = node.right;
            }
        }

        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
    }


    public static String printEnumNode(TreeNode[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += " " + arr[i].key;
        }
        return str;
    }

    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        tree.insert(40);
        for (int i = 0; i < 39; i++) {
            tree.insert(i);
        }
        for (int i = 41; i < 100; i++) {
            tree.insert(i);
        }
//        tree.rebuild(tree.root, Child.RIGHT);
        System.out.println(tree.printEnumNode(tree.enumerateNodes(tree.root, Child.LEFT)));
    }
}
