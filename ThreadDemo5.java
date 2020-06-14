public class ThreadDemo5 {
    // 一键加 try - catch 快捷键: ctrl + alt + t.

    static class Singleton {
        // 1. "饿汉模式": 只要类被加载, 实例就会立刻创建.
        //     => 线程安全.

        // a. 创建一个表示单例的类
        // b. 把 构造方法 变成私有, 此时在该类外部就无法 new 在这个类的实例了.
        private Singleton() { }
        // c.再来创建一个 static 的成员, 表示 Singleton 类的唯一的实例.
        //   static 和 类相关, 和 实例无关.
        //   说明 类在内存中只有一份, static 成员也就只有一份.
        static Singleton instance = new Singleton();

        public static Singleton getInstance() {
            return instance;
        }
    }
    public static void main1(String[] args) {
        // 单例模式(常见的"设计模式")
        // 设计模式: 一些解决方法模板
        // 单例模式: 保证指定的类只能有一个实例.

        // 此处的 getInstance 就是获取该类实例的唯一方式. 不应该使用其他方式来创建实例了.
        Singleton s = Singleton.getInstance();
        Singleton singleton = Singleton.getInstance();
        System.out.println(s == singleton); // true
    }

    static class Single {
        // 2. "懒汉模式": 类被加载, 实例不会立刻创建
        //    等到第一次使用这个实例 (调用 getInstance) 的时候再实例化.
        //    => 线程不安全.
        //    解决方式: (必考)
        //    1. 加锁, 保证线程安全
        //    2. 双重 if 保证效率
        //    3. volatile 避免内存可见性引来的问题.

        // "懒汉模式" 比 "饿汉模式" 效率高:
        // 前者的实例可能 不会 被使用, 节省了实例化的开销.

        private Single() { }

        private volatile static Single ins = null;
        //private static Single ins = null;

        // 类加载的时候, 没有立刻实例化
        /*public static Single getInstance() {
            if (ins == null) {
                ins = new Single();
                // 第一次调用 getInstance 的时候, 才真的实例化
            }
            return ins;
        }*/

        public static Single getInstance() {
            // 线程安全写法(加锁):
            if (ins == null) {
                // 只在第一次实例化的时候加锁
                // 之后再加锁已经没有意义了.
                synchronized (Single.class) {
                    // 锁粒度最小
                    if (ins == null) {
                        ins = new Single();
                    }
                }
            }
            return ins;
        }
    }
}
