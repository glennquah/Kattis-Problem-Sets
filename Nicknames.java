import java.util.HashMap;

class Nicknames {

    static class Node {
        private String name;
        private Node left;
        private Node right;
        private int height;

        public Node(String name) {
            this.name = name;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        // to set left node
        public void setLeftNode(Node node) {
            if (node == null) {
                this.left = null;
            } else {
                this.left = node;
            }
        }

        // to set right node
        public void setRightNode(Node node) {
            if (node == null) {
                this.right = null;
            } else {
                this.right = node;
            }
        }

        // to set height
        public void setHeight(int height) {
            this.height = height;
        }

        public String getName() {
            return this.name;
        }

        public int getHeight() {
            return this.height;
        }

    }

    static class AVL {
        private Node rootNode;

        public AVL() {
            this.rootNode = null;
        }

        // to get height of the node
        public int getHeight(Node node) {
            if (node == null) {
                return -1;
            } else {
                return node.getHeight();
            }
        }

        // to update height of node by taking the biggest L / R node + 1
        public int newUpdatedHeight(Node left, Node right) {
            return Math.max(getHeight(left), getHeight(right)) + 1;
        }

        public void insertName(String name) {
            this.rootNode = insertName(name, rootNode);
        }

         public Node insertName(String name, Node node) {
            if (node == null) {
                return new Node(name);
            } 
            
            //to insert the name in the AVL
            if (name.compareTo(node.getName()) > 0) {
                node.right = insertName(name, node.right);
            } else {
                node.left = insertName(name, node.left);
            }

            node.setHeight(newUpdatedHeight(node.left, node.right));
            int balance = getBalance(node);

            // to rebalance the tree
            // if tree is right skewed
            if (balance == 2) {
                //if need to rebalance twice
                if (getBalance(node.right) == -1) {
                    node.right = rightRotate(node.right);
                } 
                node = leftRotate(node);
            // if tree is left skewed
            } else if (balance == -2) {
                //if need to rebalance twice
                if (getBalance(node.left) == 1) {
                    node.left = leftRotate(node.left);
                }
                node = rightRotate(node); 
            }
            
            return node;
        }
        
        //get balance by comparing height of both nodes
        public int getBalance(Node node) {
            if (node == null) {
                return 0;
            } else {
                return getHeight(node.right) - getHeight(node.left);
            }
        }
        
        //rotate left
        public Node leftRotate(Node node) {
            if (node == null || node.right == null) {
                return node;
            }

            Node tempNode = node.right;
            node.setRightNode(tempNode.left);
            tempNode.setLeftNode(node);
            node.setHeight(newUpdatedHeight(node.left, node.right));
            tempNode.setHeight(newUpdatedHeight(node.left, node.right));
            return tempNode;
        }

        //rotate right
        public Node rightRotate(Node node) {
            if (node == null || node.left == null) {
                return node;
            }

            Node tempNode = node.left;
            node.setLeftNode(tempNode.right);
            tempNode.setRightNode(node);
            node.setHeight(newUpdatedHeight(node.left, node.right));
            tempNode.setHeight(newUpdatedHeight(node.left, node.right));
            return tempNode;
        }

        //binary search to improve algo by finding greatest root
        public Node binarySearchForNames(String name, Node node) {
            if (node == null) {
                return null;
            } else {
                if (node.name.startsWith(name)) {
                    return node;
                } else if (name.compareTo(node.name) < 0) {
                    return binarySearchForNames(name, node.left);
                } else {
                    return binarySearchForNames(name, node.right);
                }
            }
        }

        //find nick names similar
        public int numOfNickNamesSimilar(String name) {
            Node newNode = binarySearchForNames(name, this.rootNode);
            return numOfNickNamesSimilar(name, newNode);
        }
        
        //using recursion
        public int numOfNickNamesSimilar(String name, Node node) {
            if (node == null) {
                return 0;
            } else {
                if (node.getName().indexOf(name) == 0) {
                    return 1 + numOfNickNamesSimilar(name, node.left) + numOfNickNamesSimilar(name, node.right);
                } else if (name.compareTo(node.name) < 0) {
                    //System.out.println("check > left");
                    return numOfNickNamesSimilar(name, node.left);
                } else {
                    //System.out.println("check rec < right");
                    return numOfNickNamesSimilar(name, node.right);
                }
            }
        }
        
    }
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int numOfNames = kat.getInt();

        //split avl tree into a - z to get smaller trees
        AVL[] avlTree = new AVL[26];
        for (int i = 0; i < 26; i++) {
            avlTree[i] = new AVL();
        }

        //hashmap for repeated nicknames
        HashMap<String, Integer> repeatedNames = new HashMap<>();

        for (int i = 0; i < numOfNames; i++) {
            String name = kat.getWord();
            int askii = name.charAt(0) - 'a';
            avlTree[askii].insertName(name);
        }

        int numOfNickNames = kat.getInt();
        
        for (int i = 0; i < numOfNickNames; i++) {
            String checkName = kat.getWord();
            int num = 0;
            int askii = checkName.charAt(0) - 'a';
            if (repeatedNames.containsKey(checkName)) {
                num = repeatedNames.get(checkName);
                kat.println(num); 
            } else {
                num = avlTree[askii].numOfNickNamesSimilar(checkName);
                repeatedNames.put(checkName, num);
                kat.println(num);
                //System.out.println("boom");
            }
        }
        kat.flush();
        kat.close();
    }    
}
