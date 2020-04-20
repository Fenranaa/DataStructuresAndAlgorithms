package cn.fenrana.tree.avl;

public class AVLTree {
    private Node root;

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};

//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
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
                    } else {
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
                    } else {
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

        //右字节点的高度大于左子节点, 进行坐旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果当前节点的右子树的左子树高度大于当前节点右子树的右子树
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对当前节点的右子节点进行右旋转
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        //左字节点的高度大于右子节点, 进行右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果当前节点的左子树的右子树的高度大于当前节点的左子树的左子树
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //对当前节点的左子节点进行左旋转
                left.leftRotate();
            }
            rightRotate();
        }
    }

    /**
     * 返回以该节点为根节点的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左子树的高度
     *
     * @return 左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    /**
     * 右字树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();

    }

    /**
     * 左旋转平衡二叉树
     */
    public void leftRotate() {
        //创建新的节点，以当前节点的值
        var newNode = new Node(val);
        //把新节点的左子树，设置成当前节点的左子树
        newNode.left = left;
        //把新节点的右子树，设置成当前节点的右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值，设置成右子节点的值
        val = right.val;
        //把当前节点的右子树，设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树，设置成新的节点
        left = newNode;
    }

    /**
     * 右旋转平衡二叉树
     */
    public void rightRotate() {
        //创建一个新的节点
        var newNode = new Node(val);
        newNode.right = right;
        newNode.left = left.right;
        val = left.val;
        left = left.left;
        right = newNode;
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