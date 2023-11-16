package binarytree;

public class BinaryTree {
    Node root; // A raiz da nossa árvore

    // Método para encontrar um nó na árvore de forma recursiva
    public Node recursiveTreeSearch(Node x, int key) {
        if (x == null || key == x.key) {
            return x; // Se encontrarmos o nó, retornamos ele
        }

        if (key < x.key) {
            return recursiveTreeSearch(x.left, key); // Vamos para a esquerda se a chave for menor
        } else {
            return recursiveTreeSearch(x.right, key); // Vamos para a direita se a chave for maior
        }
    }

    // Método para encontrar um nó na árvore de forma iterativa
    public Node iterativeTreeSearch(int key) {
        Node x = root;
        while (x != null && key != x.key) {
            if (key < x.key) {
                x = x.left; // Indo para a esquerda se a chave for menor
            } else {
                x = x.right; // Indo para a direita se a chave for maior
            }
        }
        return x; // Retornamos o nó se o encontrarmos
    }

    // Método para adicionar um novo nó à árvore
    public void bstInsert(int key) {
        Node novo = new Node(key);
        Node aux = null;// nó auxiliar
        Node x = root;
        while (x != null) {
            aux = x;
            if (novo.key < x.key) {
                x = x.left; // Indo para a esquerda se a chave for menor
            } else {
                x = x.right; // Indo para a direita se a chave for maior
            }
        }
        novo.parent = aux; // Definindo o pai do novo nó como y
        if (aux == null) {
            root = novo; // Se a árvore estiver vazia, definimos o novo nó como raiz
        } else if (novo.key < aux.key) {
            aux.left = novo; // Se a chave for menor que a de y, definimos z como o filho esquerdo de y
        } else {
            aux.right = novo; // Se a chave for maior que a de y, definimos z como o filho direito de y
        }
    }

    // Método para encontrar o nó com o maior valor na árvore
    public Node bstMaximum(Node x) {
        while (x.right != null) {
            x = x.right; // Continua indo para a direita até encontrar o nó máximo
        }
        return x; // Retorna o nó com o maior valor
    }

    // Método para encontrar o nó com o menor valor na árvore
    public Node bstMinimum(Node x) {
        while (x.left != null) {
            x = x.left; // Continua indo para a esquerda até encontrar o nó mínimo
        }
        return x; // Retorna o nó com o menor valor
    }

// Método para encontrar o sucessor de um nó na árvore binária de busca
    public Node bstSuccessor(Node x) {
        if (x.right != null) {
            // Se o nó tem um filho direito, o sucessor é o nó mínimo na subárvore direita
            return bstMinimum(x.right);
        }

        Node y = x.parent; // Inicializa y como o pai do nó x

        // Enquanto o nó x não é raiz e é um filho direito, movemos para cima na árvore
        while (y != null && x == y.right) {
            x = y; // Move para cima na árvore, tornando x igual ao pai
            y = y.parent; // Atualiza y para o pai do nó x
        }

        return y; // Retorna o sucessor encontrado durante a busca na árvore
    }


    // Método para encontrar o antecessor de um nó
// Método para encontrar o antecessor de um nó na árvore binária de busca
    public Node bstPredecessor(Node x) {
        if (x.left != null) {
            // Se o nó tem um filho esquerdo, o antecessor é o nó máximo na subárvore esquerda
            return bstMaximum(x.left);
        }

        Node y = x.parent; // Inicializa y como o pai do nó x

        // Enquanto o nó x não é raiz e é um filho esquerdo, movemos para cima na árvore
        while (y != null && x == y.left) {
            x = y; // Move para cima na árvore, tornando x igual ao pai
            y = y.parent; // Atualiza y para o pai do nó x
        }

        return y; // Retorna o antecessor encontrado durante a busca na árvore
    }


// Método para excluir um nó da árvore
public void bstDelete(int key) {
    Node D = iterativeTreeSearch(key);
    if (D.left == null) {
        shiftNodes(D, D.right); // Se o nó não tem filho esquerdo, movemos o filho direito para o seu lugar
    } else if (D.right == null) {
        shiftNodes(D, D.left); // Se o nó não tem filho direito, movemos o filho esquerdo para o seu lugar
    } else {
        Node E = bstSuccessor(D);
        // Verificamos se o pai do sucessor não é o próprio nó a ser removido
        if (E.parent != D) {
            shiftNodes(E, E.right); // Movemos o sucessor e seu filho direito para a posição do sucessor
            E.right = D.right; // Atribuímos a subárvore direita de D como a subárvore direita do sucessor
            E.right.parent = E; // Atualizamos o pai do filho direito do sucessor para o próprio sucessor
        }
        shiftNodes(D, E); // Movemos o nó a ser removido e o seu sucessor para suas posições finais
        E.left = D.left; // Atribuímos a subárvore esquerda de D como a subárvore esquerda do sucessor
        E.left.parent = E; // Atualizamos o pai do filho esquerdo do sucessor para o próprio sucessor
    }
}

// Método auxiliar para trocar nós na árvore
private void shiftNodes(Node u, Node v) {
    if (u.parent == null) {
        root = v; // Se o nó a ser movido é a raiz, atualizamos a raiz
    } else if (u == u.parent.left) {
        u.parent.left = v; // Se o nó a ser movido é o filho esquerdo do pai, atualizamos o filho esquerdo do pai
    } else {
        u.parent.right = v; // Caso contrário, atualizamos o filho direito do pai
    }
    if (v != null) {
        v.parent = u.parent; // Atualizamos o pai do nó movido
    }
}


    // Método para percorrer a árvore em ordem
    public void inorderTreeWalk(Node x) {
        if (x != null) {
            inorderTreeWalk(x.left); // Primeiro visitamos a subárvore esquerda
            visitNode(x); // Em seguida, visitamos o próprio nó
            inorderTreeWalk(x.right); // Por fim, visitamos a subárvore direita
        }
    }

    // Método para percorrer a árvore em pré-ordem
    public void preorderTreeWalk(Node x) {
        if (x != null) {
            visitNode(x); // Primeiro visitamos o nó
            preorderTreeWalk(x.left); // Em seguida, visitamos a subárvore esquerda
            preorderTreeWalk(x.right); // Por fim, visitamos a subárvore direita
        }
    }

    // Método para percorrer a árvore em pós-ordem
    public void postorderTreeWalk(Node x) {
        if (x != null) {
            postorderTreeWalk(x.left); // Primeiro visitamos a subárvore esquerda
            postorderTreeWalk(x.right); // Em seguida, visitamos a subárvore direita
            visitNode(x); // visita o próprio nó
        }
    }

    // Método para visitar e imprimir o valor do nó
    private void visitNode(Node x) {
        System.out.print(x.key + " ");
    }

    public static void main(String[] args) {
        BinaryTree arvore = new BinaryTree();
        arvore.bstInsert(50);
        arvore.bstInsert(30);
        arvore.bstInsert(70);
        arvore.bstInsert(90);
        arvore.bstInsert(20);
        arvore.bstInsert(10);
        arvore.bstInsert(60);
        arvore.bstInsert(40);

        System.out.println("Travessia em ordem:");
        arvore.inorderTreeWalk(arvore.root);

        System.out.println("\nTravessia pre ordem:");
        arvore.preorderTreeWalk(arvore.root);

        System.out.println("\nTravessia pos ordem:");
        arvore.postorderTreeWalk(arvore.root);

        Node searchResult = arvore.iterativeTreeSearch(90);
        if (searchResult != null) {
            System.out.println("\nEncontrado: " + searchResult.key);
        } else {
            System.out.println("\nNao encontrado.");
        }
        
        
        
        System.out.println("\n\nRetirando o 90 e verificando se foi retirado");
        arvore.bstDelete(90);
        System.out.println("Travessia em ordem:");
        arvore.inorderTreeWalk(arvore.root);

        System.out.println("\nTravessia pre ordem:");
        arvore.preorderTreeWalk(arvore.root);

        System.out.println("\nTravessia pos ordem:");
        arvore.postorderTreeWalk(arvore.root);
        

        searchResult = arvore.iterativeTreeSearch(90);
        if (searchResult != null) {
            System.out.println("\nEncontrado: " + searchResult.key);
        } else {
            System.out.println("\n90 nao encontrado.");
        }
    }
}

