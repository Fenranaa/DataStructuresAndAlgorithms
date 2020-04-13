package cn.fenrana.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

      /*  System.out.println("前序遍历..");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.midOrder();
        System.out.println("后序遍历....");
        binaryTree.infixOrder();*/

        //
        System.out.println("前序搜索");
        HeroNode heroNode = binaryTree.preSearch(3);
        if (heroNode != null) {
            System.out.println(heroNode);
        }

    }


}

class BinaryTree {
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("空");
        }
    }
    //中序遍历
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        }else {
            System.out.println("空");
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("空");
        }
    }

    public HeroNode preSearch(int no) {
        if (this.root != null) {
            return this.root.preSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode midSearch(int no) {
        if (this.root != null) {
            return this.root.midSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode postSearch(int no) {
        if (this.root != null) {
            return this.root.postSearch(no);
        }else {
            return null;
        }
    }

}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //后序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
        System.out.println(this);
    }
    /**
     * 前序搜索
     * */
    public HeroNode preSearch(int no) {
        if (this.id == no) {
            return this;
        }
        HeroNode heroNode = null;
        if(left != null) {
            heroNode = this.left.preSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (right != null) {
            heroNode = this.right.preSearch(no);
        }
        return heroNode;
    }
    /**
     * 中序搜索
     * */
    public HeroNode midSearch(int no) {
        HeroNode heroNode = null;
        if(left != null) {
            heroNode = this.left.preSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.id == no) {
            return this;
        }
        if (right != null) {
            heroNode = this.right.preSearch(no);
        }
        return heroNode;
    }
    /**
     * 后序搜索
     * */
    public HeroNode postSearch(int no) {
        HeroNode heroNode = null;
        if(left != null) {
            heroNode = this.left.preSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }

        if (right != null) {
            heroNode = this.right.preSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }

        if (this.id == no) {
            return this;
        }
        return heroNode;
    }

}
