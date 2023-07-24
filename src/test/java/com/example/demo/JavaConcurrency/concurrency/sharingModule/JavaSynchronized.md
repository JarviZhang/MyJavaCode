# synchronized介绍
即俗称的【对象锁】
它采用互斥的方式让同一时刻至多只有一个线程能持有【对象锁】，其它线程再想获取这个【对象锁】时就会阻塞住。这样就能保证拥有锁的线程可以安全的执行临界区内的代码，不用担心线程上下文切换
Java中的每一个对象都可以作为一个锁。具体有三种：
- 对于普通的同步方法，锁的是当前实例对象
- 对于静态同步方法，锁的是当前类的class对象
- 对于同步方法块，锁的是Synchronized括号里的对象
# 语法
```java
synchronized (object) {
        //临界区
    }
```
用在方法上
```java
class Test{
    public synchronized void test(){
        
    }
}
```
等价于
```java
class Test{
    public void test(){
        synchronized (this){
            
        }
    }
}
```
用在静态方法上
```java
class Test{
    public synchronized static void test() {
    }
}
```
等价于
```java
class Test{
 public static void test() {
 synchronized(Test.class) {
 
 }
 }
}

```

# 线程八锁
考察对synchronized锁住对象
## 情况一
```java
class Number{
 public synchronized void a() {
 log.debug("1");
 }
 public synchronized void b() {
 log.debug("2");
 }
}
public static void main(String[] args) {
    Number n1 = new Number();
    new Thread(()->{ n1.a(); }).start();
    new Thread(()->{ n1.b(); }).start();
}
```
这里a和b锁住的实例对象
所以main中两个线程抢的是一个锁

## 情况二
```java
class Number{
 public synchronized void a() {
 sleep(1);
 log.debug("1");
 }
 public synchronized void b() {
 log.debug("2");
 }
}
public static void main(String[] args) {
 Number n1 = new Number();
 new Thread(()->{ n1.a(); }).start();
 new Thread(()->{ n1.b(); }).start();
}

```
与情况一相同

## 情况三
```java
class Number{
 public synchronized void a() {
 sleep(1);
 log.debug("1");
 }
 public synchronized void b() {
 log.debug("2");
 }
 public void c() {
 log.debug("3");
 }
}
public static void main(String[] args) {
 Number n1 = new Number();
 new Thread(()->{ n1.a(); }).start();
 new Thread(()->{ n1.b(); }).start();
 new Thread(()->{ n1.c(); }).start();
}
```
a b和情况一相同
c无锁

## 情况四
```java
class Number{
 public synchronized void a() {
 sleep(1);
 log.debug("1");
 }
 public synchronized void b() {
 log.debug("2");
 }
}
public static void main(String[] args) {
 Number n1 = new Number();
 Number n2 = new Number();
 new Thread(()->{ n1.a(); }).start();
 new Thread(()->{ n2.b(); }).start();
}
```
两个线程各自单独一个实例对象锁

## 情况五
```java
class Number{
     public static synchronized void a() {
     sleep(1);
     log.debug("1");
     }
     public synchronized void b() {
     log.debug("2");
     }
}
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n1.b(); }).start();
    }
```
线程1锁住的是类对象
线程2锁住的是实例对象
两个对象是独立的

## 情况六
```java
class Number{
 public static synchronized void a() {
 sleep(1);
 log.debug("1");
 }
 public static synchronized void b() {
 log.debug("2");
 }
}
public static void main(String[] args) {
 Number n1 = new Number();
 new Thread(()->{ n1.a(); }).start();
 new Thread(()->{ n1.b(); }).start();
}
```
两个线程使用的是同一个类对象的锁

## 情况七
```java
class Number{
 public static synchronized void a() {
 sleep(1);
 log.debug("1");
 }
 public synchronized void b() {
 log.debug("2");
 }
}
public static void main(String[] args) {
 Number n1 = new Number();
 Number n2 = new Number();
 new Thread(()->{ n1.a(); }).start();
 new Thread(()->{ n2.b(); }).start();
}
```
两个锁，注意与情况五作比较

## 情况八
```java
class Number{
 public static synchronized void a() {
 sleep(1);
 log.debug("1");
 }
 public static synchronized void b() {
 log.debug("2");
 }
}
public static void main(String[] args) {
 Number n1 = new Number();
 Number n2 = new Number();
 new Thread(()->{ n1.a(); }).start();
 new Thread(()->{ n2.b(); }).start();
}
```
两个线程用的同一个类对象的锁

