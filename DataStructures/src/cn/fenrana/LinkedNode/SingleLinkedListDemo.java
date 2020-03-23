package cn.fenrana.LinkedNode;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");


        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();


        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.list();

        System.out.println("反转打印");
        reversePrint(singleLinkedList.getHead());


//        System.out.println("反转后的单链表");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

//        singleLinkedList.list();
//        HeroNode hero5 = new HeroNode(4, "林冲11", "豹子头111");
//        singleLinkedList.update(hero5);
//        System.out.println("修改后的链表:");
//        singleLinkedList.list();
//        System.out.println("删除后的后的链表:");
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(4);
//        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHead()));
//        System.out.println("------");
//        System.out.println(getaa(singleLinkedList.getHead(), 2));
    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    public static int getLength(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;
        }
        var lenght = 0;
        var temp = heroNode.next;
        while (temp != null) {
            lenght++;
            temp = temp.next;

        }
        return lenght;
    }

    //获得倒数的几个节点
    public static HeroNode getaa(HeroNode head, int index) {
        HeroNode temp = head.next;
        int size = getLength(head);
        if (temp == null) {
            return null;
        }
        if (index <= 0 || index > size) {
            return null;
        }

        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助变量, 帮助我们遍历链表
        HeroNode temp = head.next;
        HeroNode next = null; //
        HeroNode reverseNode = new HeroNode(0, "", "");

        while (temp != null) {
            next = temp.next; //将原链表后面的保存起来,
            temp.next = reverseNode.next;
            reverseNode.next = temp;
            temp = next;
        }
        head.next = reverseNode.next;
    }

    //反转打印单链表, 使用栈,这样可以不改变原有的链表结构
    public static void reversePrint(HeroNode heroNode) {
        if (heroNode.next == null) {
            return;
        }

        HeroNode temp = heroNode.next;
        Stack<HeroNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 添加节点到单链表
     * 思路 : 当考虑编号时
     * 1, 找到链表的最后一个节点
     * 2, 将这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                //找到链表的最后, 然后打断遍历
                break;
            }
            //不是最后一个节点, 将temp后移
            temp = temp.next;
        }
        //当退出while循环时, temp就指向最后一个节点了 也就是temp.next为null
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //找到链表的最后, 然后打断遍历
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            //不是最后一个节点, 将temp后移
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("以no=%d为no的节点已经存在!!\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改一个节点
     */
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //找到链表的最后, 然后打断遍历
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            //不是最后一个节点, 将temp后移
            temp = temp.next;
        }

        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     */
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false; //标致有没有找到要删除的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    /**
     * 显示节点
     */
    public void list() {

        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //将节点后移
            temp = temp.next;
        }
    }
}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
