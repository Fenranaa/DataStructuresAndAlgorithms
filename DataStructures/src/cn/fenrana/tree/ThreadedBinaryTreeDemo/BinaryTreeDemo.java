package cn.fenrana.tree.ThreadedBinaryTreeDemo;

class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        var threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10号结点的后继结点是=" + rightNode); //1


    }


}

class ThreadedBinaryTree {
    private HeroNode root;

    private HeroNode pre = null;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 二叉树的中序线索化
     */
    public void threadedNodes(HeroNode heroNode) {
        if (heroNode == null) {
            return;
        }
        //1.线索左二叉树
        threadedNodes(heroNode.getLeft());
        //2. 线索化当前节点
        if (heroNode.getLeft() == null) {
            heroNode.setLeft(pre);
            heroNode.setLeftTypre(1);
        }
        //处理后序节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode);
            pre.setRightTypre(1);
        }

        pre = heroNode;

        //3.线索化右子树
        threadedNodes(heroNode.getRight());
    }

    /**
     * 二叉树的前序线索化
     */
    public void threadedNodesPre(HeroNode heroNode) {
        //2. 线索化当前节点
        if (heroNode.getLeft() == null) {
            heroNode.setLeft(pre);
            heroNode.setLeftTypre(1);
        }
        //处理后序节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode);
            pre.setRightTypre(1);
        }

        pre = heroNode;

        //1.线索左二叉树
        threadedNodes(heroNode.getLeft());

        //3.线索化右子树
        threadedNodes(heroNode.getRight());
    }

    /**
     * 二叉树的后序线索化
     */
    public void threadedNodesAfter(HeroNode heroNode) {
        //1.线索左二叉树
        threadedNodes(heroNode.getLeft());

        //3.线索化右子树
        threadedNodes(heroNode.getRight());

        //2. 线索化当前节点
        if (heroNode.getLeft() == null) {
            heroNode.setLeft(pre);
            heroNode.setLeftTypre(1);
        }
        //处理后序节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode);
            pre.setRightTypre(1);
        }

        pre = heroNode;
    }

    //中序遍历线索化二叉树
    public void threadedNodesList() {
        var node = root;
        while (node != null) {
            while (node.getLeftTypre() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);
            while (node.getRightTypre() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 前序遍历线索化二叉树
     */
    public void threadedNodesPreList() {
        var node = root;
        while (node != null) {

            System.out.println(node);

            while (node.getLeftTypre() == 0) {
                node = node.getLeft();
            }

            while (node.getRightTypre() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    /**
     * 后序遍历线索化二叉树
     * */
    public void threadedNodesAfterList() {
        var node = root;
        while (node != null) {
            while (node.getLeftTypre() == 0) {
                node = node.getLeft();
            }

            while (node.getRightTypre() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            System.out.println(node);
            node = node.getRight();
        }
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftTypre;
    private int rightTypre;

    public int getLeftTypre() {
        return leftTypre;
    }

    public void setLeftTypre(int leftTypre) {
        this.leftTypre = leftTypre;
    }

    public int getRightTypre() {
        return rightTypre;
    }

    public void setRightTypre(int rightTypre) {
        this.rightTypre = rightTypre;
    }

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
