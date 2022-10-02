final double PI = 3.14; //定义常量
byte a = 68;
char c = 'a';
// 数值型变量默认值是 0，布尔型默认值是 false，引用类型默认值是 null
byte n1 = 100;
int n2 = 100;
short n3= 0100; // 8
long n4 = 0x100; // 16

// 强制类型转换
int i1 = 123;
byte b1 = (byte)i1; // 强制转换类型

public class Employee {
    private static double salary;
    public static final String NAME = "I guess";
    public static void main(String[] args){
        salary = 10000;
        System.out.println(NAME + ": " + salary);
    }
}
//final 修饰符，用来修饰类、方法和变量，
//final 修饰的类不能够被继承，修饰的方法不能被继承类重新定义，
// 修饰的变量为常量，是不可修改的

// synchronized 关键字声明的方法同一时间只能被一个线程访问

// ^ 异或
// |= 非
// instanceof String

// java5 增强for循环
for (int n : nums) {}

System.out.printf()

String.format()

StringBuffer // 每次修改是对象本身操作，不生成新对象，线程安全
StringBuilder // 线程不安全，但速度快

double[] myList = new double[10];// 定义数组

new int[]{1,2,3}

Thread.sleep(1000); // 休眠3秒

Calendar.getInstance()

public static void printMax(double... nums) {} // 可变参数

System.gc(); // 调用Java垃圾收集器

protected void finalize() throws java.lang.Throwable {
    super.finalize();
    System.out.println("finalize");
}

// try后括号可以对实现 AutoCloseable 接口的资源，在finally自动关闭
try (a;b;c) { // 可以处理多个资源，关闭时，后声明的先关闭

} catch (Exception e) {

} catch(Exception e1) { // 可以接多个catch

} finally { // 可以有finally

}

extends // 继承类。java只能单继承，多重继承，不能多继承（多个父类）

implements // 实现，主要实现接口，可以同时实现多个接口

final // 修饰类，表示不能被继承；修饰方法，表示不能被子类重写

// 多态存在的3个必要条件
1.继承
2.重写
3.父类引用指向子类对象
Parent a = new Child(); // 编译时会按照Parent检查属性方法是否存在，运行时会调用子类对应的属性方法

// 抽象类：不能实例化，只能被继承；一个类只能继承一个抽象类
public abstract class Employee {
    private String name;
    public abstract double computePay(); // 抽象方法，具体实现由子类定义
}
// 抽象方法只能存在于抽象类中
// 子类必须重写父类的抽象方法，或声明自身为抽象类

Interface // 接口包含类要实现的方法
public abstract // 接口中的方法默认是
public static final // 接口的成员变量被隐式指定的类型
// 接口可以多继承接口

// 标记接口：没有任何方法的接口，称为标记接口

// 枚举：每个枚举在内部实现是public static final

for (Color myVar : Color.values()) {
    System.out.println(myVar);
}
values() // 返回枚举类中所有的值
ordinal() // 找到常量对应的索引，可以用于for:arr循环体中
valueOf() // Color.valueOf('RED') 返回字符串的枚举常量

// Set 和 List 的区别
// Set 无序 不重复 List 有序，可重复
// Set 检索效率低，删除插入效率高；List可以的动态增长，查找效率高，插入删除效率低
