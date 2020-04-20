package cn.fenrana.tree.binarySortTree;

public class BinarySortTree {
    private Node root;

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2, 4};

        var binary = new BinarySortTree();
        for (int i : arr) {
            binary.add(new Node(i));
        }

        binary.infxOrder();
        System.out.println("删除检点");
        binary.delNode(7);
        binary.infxOrder();
//        Node node = binary.searchParent(2);
//        System.out.println(node);

    }

    public void add(Node node) {
        if (this.root != null) {
            this.root.add(node);
        } else {
            this.root = node;
        }

    }

    public void infxOrder() {
        if (this.root != null) {
            this.root.infxOrder();
        } else {
            System.out.println("空");
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return this.root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return this.root.searchParent(value);
        }
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
//            1. 找到要删除的节点
            Node target = search(value);
            //判断要删的节点找到没
            if (target == null) {
                return;
            }
            //如果只有一个节点的二叉树
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 找到要删除节点的父节点
            Node parent = searchParent(value);
            //如果要删除的节点， 是叶子节点
            if (target.right == null && target.left == null) {
                //删除叶子节点
                //判断targetNode是 父节点的左子节点还是子右节点
                if (parent.left != null && parent.left.val == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.val == value) {
                    parent.right = null;
                }

            } else if (target.left != null && target.right != null) {
                //目标有两个子节点
                target.val = delRightTreeMin(target.right);

            } else {
                //目标只有一个节点
                //如果要删除的节点是左节点
                if (target.left != null) {
                    //判断parent 是否为空
                    if (parent != null) {
                        //如果要删除target是 parent 的左节点
                        if (parent.left.val == value) {
                            parent.left = target.left;
                        } else {
                            //反之
                            parent.right = target.left;
                        }
                    }else {
                        target = target.left;
                    }

                } else {
                    //判断parent是否为空
                    if (parent != null) {
                        // 如果要删除target是 parent 的右节点
                        if (parent.right.val == value) {
                            parent.right = target.right;
                        } else {
                            parent.left = target.right;
                        }
                    }else {
                        target = target.right;
                    }

                }
            }

        }
    }

    /**
     * 删除最小的那个节点， 并返回值
     */
    public int delRightTreeMin(Node node) {
        var target = node;

        if (target.left != null) {
            target = target.left;
        }

        delNode(target.val);
        return target.val;
    }


}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    public void add(Node node) {
        if (node.val < this.val) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void infxOrder() {
        if (this.left != null) {
            this.left.infxOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infxOrder();
        }
    }

    public Node search(int value) {
        if (this.val == value) {
            return this;
        }
        if (value < this.val) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);

        }
    }

    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.val == value) ||
                (this.right != null && this.right.val == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.val && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value >= this.val && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
