public class Test {

    /*public static void main1(String[] args) {
        String s;
        System.out.println("s = " + s);
        //Error: java: 可能尚未初始化变量 s
    }*/

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("2");
            }
        });

        t.start();
        t.join();
        System.out.print("1");// 21
    }

    public static void main3(String[] args) {

        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");

        operate(a, b);
        System.out.println(a + "." + b);// AB.B
    }

    static void operate(StringBuffer x, StringBuffer y) {

        x.append(y);
        // A 附加 B, x = A;
        // StringBuffer.append(); 将各种参数的字符串表示 附加 到此序列

        y = x;
    }

    public static void main4(String[] args) {
        int x, y;

        x = 5 >> 2;
        // >> 表示带符号右移，即符号位保持不变，次高位开始右移
        // 5 >> 2: 表示将 5 的二进制 0101 带符号位向右移动 2 位, 高位补 0, 得 0001 = 1

        y = x >>> 2;
        // 表示无符号右移，即符号位也参与右移
        // 1 >>> 2: 表示将 1 的二进制 0001 不带符号位右移 2 位, 高位补 0, 得 0000 = 0

        System.out.println(y);// 0
    }

    static class Exmple {

        String str = new String("good");
        char[] ch = {'a', 'b', 'c'};

        public static void main(String[] args) {

            Exmple ex = new Exmple();
            ex.change(ex.str, ex.ch);

            System.out.print(ex.str + " and ");
            System.out.println(ex.ch);
            // good and gbc

            System.out.print(ex.str + " and " + ex.ch);
            // good and [C@1540e19d
        }

        static void change(String str, char ch[]) {
            // 引用类型传参, 传的是 参数 不是 地址, 与主函数的值无关
            // 数组 相当于 指针, 传的是地址,可以改变主函数的值

            str = "task ok";
            ch[0] = 'g';
        }
    }
}
