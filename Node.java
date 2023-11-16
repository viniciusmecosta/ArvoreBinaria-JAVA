package binarytree;

public class Node {
    int key; // Valor dentro do no
    Node left, right, parent; // filho esquerdo, filho direito e pai

    public Node(int item) {
        key = item;
        left = right = parent = null;
    }
}
