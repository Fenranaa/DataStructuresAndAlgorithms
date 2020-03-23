package cn.fenrana.LinkedNode;

public class Josephus {
    public static void main(String[] args) {
        var cricle = new CricleSingleLinkedList();
        cricle.add(5);
        cricle.showBoy();
        cricle.contBoy(1, 2, 5);
    }
}

class CricleSingleLinkedList {
    private Boy first;

    //添加小孩节点, 构建一个环形链表
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null; //辅助变量, 帮助我们构建环形链表.,.
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形列表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        Boy temp = first;
        while (true) {
            System.out.printf("小孩得编号为:%d\n", temp.getId());
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * @param startId 开始的位置
     * @param counNum 表示数几下
     * @param nums    表示最初有多少个人在圈中
     */
    public void contBoy(int startId, int counNum, int nums) {
        // 先对数据进行校验
        if (first == null || startId < 1 || startId > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }

        //创建辅助变量, 帮助出圈
        Boy helper = first;
        // 让helper指向最后一个变量
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        //吧first和helper移动到开始的位置
        for (int i = 0; i < startId - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (helper == first) {
                //说明圈中只有一个节点, 结束
                break;
            }
            //开始移动first和 helper counNum - 1次
            for (int i = 0; i < counNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.printf("小孩%d出圈\n", first.getId());
            first = first.getNext();
            helper.setNext(first);

        }


    }
}

class Boy {
    private int id;
    private Boy next;

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                ", next=" + next +
                '}';
    }
}
