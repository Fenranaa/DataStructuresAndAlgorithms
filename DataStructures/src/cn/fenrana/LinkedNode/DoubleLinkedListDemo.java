package cn.fenrana.LinkedNode;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }


}

class DoubleLinked {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void setHead(HeroNode2 head) {
        this.head = head;
    }

    /**
     * 添加节点到单链表
     * 思路 : 当考虑编号时
     * 1, 找到链表的最后一个节点
     * 2, 将这个节点的next指向新的节点
     */
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        //找到链表的最后, 然后打断遍历
        while (temp.next != null) {
            //不是最后一个节点, 将temp后移
            temp = temp.next;
        }
        //当退出while循环时, temp就指向最后一个节点了 也就是temp.next为null
        //将最后这个节点的next 指向 新的节点
        heroNode.pre = temp;
        temp.next = heroNode;
    }

    /**
     * 修改一个节点
     */
    public void update(HeroNode2 newHeroNode) {
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        boolean flag = false; //标致有没有找到要删除的节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}

