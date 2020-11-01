#### 第2章 环境准备

##### 2-1 Spring模块梳理

![](images\spring模块梳理1.png)

![](images\spring模块梳理2.png)



###### 2-1-1 Spring基础核心模块预览

**spring-core：**

- **<span style="color:red">AOP、DI</span>**

- 包含框架基本的核心工具类，其它组件都要使用到这个包里的类
- 定义并提供资源的访问方式

**spring-beans**（Spring主要面向Bean编程（BOP））：

- **<span style="color:red">BeanFactory</span>**

- Bean的定义
- Bean的解析
- Bean的创建

**spring-context：**

- 为Spring提供运行时环境，保存对象的状态
- 扩展了BeanFactory               **<span style="color:red">ApplicationContext</span>**

**spring-aop**（最小化的动态代理实现）：

- JDK动态代理
- Cglib
- 只能使用运行时织入，仅支持方法级编织，仅支持方法执行切入点
- ![](images\spring模块梳理3.png)



##### 2-4 自研框架的整体介绍及雏形搭建

![](images\自研框架1.png)



![](images\JSP运行原理1.png)





#### 第3章 业务系统架子的构建【自研框架的起源】

##### 3-1 架构设计

**从整体到局部逐步实现，采用MVC架构**

- **Model、View、Controller**

  ![](images\架构设计1.png)

- **单一职责原则**（引起一个类变化的因素不要多于一个）

  - 尽可能让一个类负责相对独立的业务
  - 保证类之间的耦合度低，降低类的复杂度

- **门面模式 Facade Pattern**（由slf4j日志框架使用）

  - 提供一个高层次的接口，使得子系统更易于使用

    ![](images\架构设计2.png)

- **通过slf4j实现对多种日志框架的兼容**

  - Simple Logging Facade for Java

    ![](images\架构设计3.png)





##### 3-4 泛型讲解

**让数据类型变得参数化：**

- 定义泛型时，对应的数据类型是不确定的
- 泛型方法被调用时，会指定具体类型
- **核心目标：解决容器类型在编译时安全检查的问题**



**类型：**

- **泛型类**
- **泛型接口**
- **泛型方法**



**泛型的参数不支持基本类型**

**泛型相关的信息不会进入到运行时阶段（<span style="color:red">泛型擦除</span>）**



**能否在泛型里面使用具备继承关系的类：**

- 使用通配符 ？，但是会使得泛型的类型检查失去意义
- 给泛型加入**<span style="color:red">上边界 ？extends E</span>**
- 给泛型加入**<span style="color:red">下边界 ？super E</span>**



**泛型字母的含义：**

- E	-	Element：在集合中使用，因为集合中存放的是元素
- T    -    Type：Java类
- K    -    Key：键
- V    -     Value：值
- N    -    Number：数值类型



##### 3-7 Controller层代码架子的搭建

![](images\Servlet1.png)

 **参照Spring MVC，仅通过DispatcherServlet进行请求派发**

- 拦截所有请求
- 解析请求
- 派发给对应的Controller里面的方法进行处理



#### 第4章 自研框架IOC实现前奏

##### 4-1 尝试引入简单工厂模式

**定义一个工厂类，根据传入的参数的值不同返回不同的实例**

- 特点：被创建的实例具有共同的父类或接口

  ![](images\自研框架iop实现前奏1.png)

- 适用场景：

  - 需要创建的对象较少
  - 客户端不关心对象的创建过程

- 优缺点：

  - 优点：可以对创建的对象进行“加工”，对客户端隐藏相关细节
  - 缺点：因创建逻辑复杂或创建对象过多而造成代码臃肿
  - 缺点：新增、删除子类均会违反开闭原则

- <span style="color:red">开闭原则</span>：一个软件实体，应该对扩展开放，对修改关闭

  - 应该通过扩展来实现变化，而不是通过修改已有的代码来实现变化

- 所有原则并非一定要严格遵守，而是需要结合业务的实际情况



##### 4-2 尝试引入工厂方法模式

**定义一个用于创建对象的接口，让子类决定实例化哪一个类**

- 特点：对类的实例化延迟到其子类

  ![](images\自研框架iop实现前奏2.png)

- 优点：

  - 遵循开闭原则
  - 对客户端隐藏对象的创建细节
  - 遵循单一职责

- 不足：

  - 添加子类时太多
  - 只支持同一类产品的创建



##### 4-3 尝试引入抽象工厂模式

**提供一个创建一系列相关或相互依赖对象的接口**

- 抽象工厂模式侧重的是同一产品族
- 工厂方法模式更加侧重于同一产品等级

![](images\自研框架iop实现前奏3.png)

- 解决了工厂模式只支持生产一种产品的弊端
  - 新增一个产品族，只需要增加一个新的具体工厂，不需要修改代码
- 对工厂模式进行了抽象
  - 工厂方法模式：每个抽象产品派生多个具体产品类，每个抽象工厂类派生多个具体工厂类，每个具体工厂类负责一个具体产品的实例创建
  - 抽象工厂模式：每个抽象产品派生多个具体产品类，每个抽象工厂派生多个具体工程类，每个具体工厂负责一系列具体产品的实例创建
- 可行性调研：当更多类型的Controller被加入到工程时
  - 添加新产品时依旧违背开闭原则，增加系统复杂度
- **<span style="color:red">结合了工厂模式和反射机制的Spring IOC容器值得借鉴</span>**



##### 4-4 利器之反射

允许程序在运行时来进行自我检查并且对内部的成员进行操作

- 反射主要是指程序可以访问、检测和修改它本身状态或行为的一种能力，并能根据自身行为的状态和结果，调整或修改应用所描述行为的状态和相关的语义

反射机制的作用：

- 在运行时判断任意一个对象所属的类
- 在运行时获取类的对象
- 在运行时访问java对象的属性、方法、构造方法等

java.lang.reflect类库里面主要的类：

- Field：表示类中的成员变量
- Method：表示类中的方法
- Constructor：表示类的构造方法
- Array：该类提供了动态创建数组和访问数组元素的静态方法

反射依赖的Class：

- 用来表示运行时类型信息的对应类：
  - 每个类都有唯一一个与之相对应的Class对象
  - Class类为类类型，而Class对象为类类型对象
- Class类的特点：
  - Class类也是类的一种，class则是关键字
  - Class类只有一个私有的构造函数，只有JVM能够创建Class类的实例
  - JVM中只有唯一一个和类相对应的Class对象来描述其类型信息
- 获取Class对象的三种方式
  - Object：getClass()
  - 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
  - 通过Class类的静态方法：forName(String className)，常用
- **<span style="color:red">在运行期间，一个类，只有一个与之相对应的Class对象产生</span>**
- 通过Class对象能够看到类的结构



##### 4-x 反射的主要用法

- 获取类的构造方法并使用

  - 批量获取
    - public Constructor[] getConstructors()：获取所有“公有的”构造方法
    - public Constructor[] getDeclaredConstructors()：获取所有的构造方法，包括私有、受保护、默认、公有
  - 获取单个的方法，并调用
    - public Constructor getConstructor(Class... parameterTypes)：获取单个的“公有的”构造方法
    - public Constructor getDeclaredConstructor(Class... parameterTypes)：获取“构造方法”，可以是私有的
  - 调用构造方法：newInstance(Object... initargs)

- 获取类的成员变量并使用

  - 批量获取
    - Field[] getFields()：获取所有的“公有字段”，包括父类的
    - Field[] getDeclaredFields()：获取当前类所有字段，包括私有、受保护、默认、公有。不能获取任何父类的字段
  - 单个获取
    - public Field getField(String fieldName)：获取某个“公有的”字段
    - public Field getDeclaredField(String fieldName)：获取某个字段（可以是私有的）
  - 设置字段的值：Field  →  public void set(Object obj, Object value)，参数说明：
    - obj：要设置的字段所在的对象
    - value：要为字段设置的值

- 获取类的成员方法并使用




##### 4-8 必知必会的注解

![](images\注解1.png)

**注解：**

- <span style="color:red">提供一种为程序元素设置元数据的方法：</span>
  - 元数据是添加到程序元素如方法、字段、类和包上的额外信息
  - 注解是一种分散式的元数据设置方式，XML是集中式的设置方式
  - 注解不能直接干扰程序代码的运行
- 反编译注解的class文件后，能看到其父类是继承Annotation接口
- 注解的功能：
  - 作为特定的标记，用于告诉编译器一些信息，如@Override
  - 编译时动态处理，如动态生成代码，如：Lombok的使用
  - <span style="color:red">运行时动态处理，作为额外信息的载体，如获取注解信息</span>
- 注解的分类：
  - 标准注解：Override、Deprecated、SuppressWarnings
  - 元注解：@Retention、@Target、@Inherited、@Documented
  - 自定义注解



###### 4-8-1 元注解

**用于修饰注解的注解，通常用在注解的定义上：**

- <span style="color:red">@Target：注解的作用目标、使用范围</span>
  - packages、types（类、接口、枚举、Annotation类型）
  - 类型成员（方法、构造方法、成员变量、枚举值）
  - 方法参数和本地变量（如循环变量、catch参数）
- <span style="color:red">@Retention：注解的生命周期，标注注解被保留时间的长短</span>
  - 响应通过反射获取，需要设置为Runtime
- <span style="color:red">@Documented：注解是否应当被包含在JavaDoc文档中</span>
- <span style="color:red">@Inherited：是否允许子类继承该注解</span>



##### 4-9 自定义注解

  ![](images\注解2.png)

- **注解属性支持的类型：**
  - 所有基本数据类型
  - String类型
  - Class类型
  - Enum类型
  - Annotation类型
  - 以上所有类型的数组



##### 4-10 注解背后的底层实现

- **JVM会为注解生成代理对象**
- **注解的工作原理：**
  - 通过键值对的形式为注解属性赋值
  - 编译器检查注解的使用范围，将注解信息写入元素属性表
  - 运行时JVM将RUNTIME的所有注解属性取出并最终存入map里
  - 创建AnnotationInvocationHandler实例并传入前面的map
  - JVM使用JDK动态代理为注解生成代理类，并初始化处理器
  - 调用invoke方法，通过传入方法名返回注解对应的属性值

 

##### 4-11 上述学习对自研框架的意义

- **使用注解标记需要工厂管理的实例，并依据注解属性做精细控制。**

  ![](images\注解3.png)



###### 4-11-1 控制反转 IoC-Inversion of Control

- 依托一个类似工厂的IoC容器
- <span style="color:red">将对象的创建、依赖关系的管理以及生命周期交由IoC容器管理</span>
- <span style="color:red">降低系统在实现上的复杂性和耦合度，易于扩展，满足开闭原则</span>
- Spring Core最核心的部分
- 需要先了解依赖注入（Dependency Inversion）



###### 4-11-2 依赖注入DI

- 上层建造依赖下层建筑

  ![](images\DI1.png)

- 把底层类作为参数传递给上层类，实现上层对下层的“控制”

  ![](images\DI2.png)

- IOC、DI、DL的关系

  ![](images\DI3.png)

- 依赖注入的方式

  - Setter
  - Interface
  - Constructor
  - Annotation



###### 4-11-3 依赖倒置原则、IOC、DI、IOC容器的关系

![](images\DI4.png)



###### 4-11-4 IOC容器的优势

- **避免在各处使用new来创建类，并且可以做到统一维护**

- **创建实例的时候不需要了解其中的细节**

- **反射+工厂模式的合体，满足开闭原则**

  ![](images\IOC1.png)

  ![](images\IOC2.png)



##### 4-12 本章小结

![](images\IOC3.png)





#### 第5章 自研框架IoC容器的实现 【实战了解SpringIOC的脉络】

##### 5-1 实现思路概述以及注解标签的创建

![](images\IOC容器实现.png)

###### 5-1-1 框架具备的最基本功能

- <span style="color:red">解析配置</span>
- <span style="color:red">定位与注册对象</span>
- <span style="color:red">注入对象</span>
- <span style="color:red">提供通用的工具类</span>



###### 5-1-2 IoC容器的实现

**需要实现的点：**

![](images\IOC容器实现2.png)



###### 5-1-3 注解的创建

```java
package org.melonframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}

```

```java
package org.melonframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}

```

```java
package org.melonframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
}

```

```java
package org.melonframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
}

```



##### 5-2 提取标记对象

**实现思路：**

- <span style="color:red">指定范围，获取范围内的所有类</span>
- <span style="color:red">遍历所有类，获取被注解标记的类并加载进容器里</span>



###### 5-2-1 extractPackageClass里面需要完成的事情

- <span style="color:red">获取到类的加载器</span>
  - 获取项目发布的实际路径
- <span style="color:red">通过类加载器获取到加载的资源信息</span>
- <span style="color:red">依据不同的资源类型，采用不同的方式获取资源的集合</span>



###### 5-2-2 类加载器ClassLoader

![](images\IOC容器实现3.png)

- 根据一个指定的类的名称，找到或者生成其对应的字节码
- 加载Java应用所需的资源



###### 5-2-3 通用资源定位符

**某个资源的唯一地址：**

- 通过获取java.net.URL实例获取协议名、资源名路径等信息

  ![](images\IOC容器实现4.png)



##### 5-3 关于单例模式

###### 5-3-1 单例模式介绍

**确保一个类只有一个实例，并对外提供统一访问方式：**

- <span style="color:red">饿汉模式：类被加载的时候就立即初始化并创建唯一实例。</span>
- <span style="color:red">懒汉模式：在被客户端首次调用的时候才创建唯一实例</span>
  - **加入双重检查锁机制的懒汉模式能确保线程安全**



###### 5-3-2 单例模式的安全性

**使用<span style="color:red">枚举</span>可防止反射和序列化的攻击**

 

##### 5-7 容器的载体以及容器的加载

**容器的组成部分：**

- <span style="color:red">保存Class对象及其实例的载体</span>
- <span style="color:red">容器的加载</span>
- <span style="color:red">容器的操作方式</span>



```java
package org.melonframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.melonframework.core.annotation.Component;
import org.melonframework.core.annotation.Controller;
import org.melonframework.core.annotation.Repository;
import org.melonframework.core.annotation.Service;
import org.melonframework.util.ClassUtil;
import org.melonframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 存放所有被配置标记的目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap();

    /**
     * 容器是否已经加载过bean
     */
    private boolean loaded = false;

    /**
     * 是否已经加载过Bean
     *
     * @return 是否已加载
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * 获取Bean实例的数量
     *
     * @return 数量
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    /**
     * 获取Bean容器实例
     *
     * @return BeanContainer
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        /**
         * 存放BeanContainer的单例
         */
        HOLDER;
        private BeanContainer instance;
        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 扫描加载所有Bean
     *
     * @param basePackage 包名
     */
    public synchronized void loadBeans(String basePackage) {
        // 判断bean容器是否被加载过
        if (isLoaded()) {
            log.warn("BeanContainer has bean loaded");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(basePackage);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("extract nothing from packageName " + basePackage);
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                // 判断类上是否标记了定义的注解
                if (clazz.isAnnotationPresent(annotation)) {
                    // 将目标类本身作为键，目标类的实例作为值，放入到beanMap中
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     * 添加一个class对象及其Bean实例
     *
     * @param clazz Class对象
     * @param bean Bean实例
     * @return 原有的Bean实例，没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz Class对象
     * @return 删除的Bean实例，没有则返回null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return Class集合
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取所有Bean集合
     *
     * @return Bean集合
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        // 1、获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }

        // 2、通过注解筛选被注解标记的class对象，并添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            // 类是否被相关的注解标记
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     * 根据接口或者父类获取实现类或者子类的Class集合，不包括其本身
     *
     * @param interfaceOrClass 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        // 1、获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }

        // 2、判断keySet里的元素是否是传入的接口或者类的子类，如果是，就将其添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            // 判断keySet里的元素是否是传入的接口或者类的子类
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }
}

```





###### 5-7-1 实现容器的加载

**实现思路：**

- 配置的管理与获取（annotation）
- 获取指定范围内的Class对象
- 依据配置提取Class对象，连同实例一并存入容器



###### 5-7-2 实现容器的操作方式

**涉及到容器的增删改查：**

- 增加、删除操作
- 根据Class获取对应实例
- 获取所有的Class和实例
- 通过注解来获取被注解标注的Class
- 通过超类获取对应的子类Class
- 获取容器载体保存Class的数量



###### 5-7-3 容器管理Bean的模式

**默认皆为单例：**

![](images\IOC容器实现5.png)



**Spring框架有多种作用域：**

- <span style="color:red">singleton</span>
- prototype
- request
- session
- globalsession



##### 5-8 实现容器的依赖注入

**目前容器里面管理的Bean实例仍可能是不完备的：**

- 实例里面某些必须的成员变量还没有被创建出来

**实现思路：**

- 定义相关的注解标签

- 实现创建被注解标记的成员变量实例，并将其注入到成员变量里

- 依赖注入的使用

  

```java
package org.melonframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.melonframework.core.BeanContainer;
import org.melonframework.inject.annotation.Autowired;
import org.melonframework.util.ClassUtil;
import org.melonframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {

    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行Ioc
     */
    public void doIoc() {
        // 1、遍历Bean容器中所有的Class对象
        if (ValidationUtil.isEmpty(beanContainer.getClasses())) {
            log.warn("empty classes in BeanContainer");
            return;
        }

        // 2、遍历Class对象的所有成员变量
        for(Class<?> clazz: beanContainer.getClasses()) {
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            // 3、找出被Autowired标记的成员变量
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    // 4、获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    // 5、获取这些成员变量的类型在容器里对应的实例
                    Object filedValue = getFieldInstance(fieldClass, autowiredValue);
                    if (filedValue == null) {
                        throw new RuntimeException("unable to inject relevant type, target fieldClass is: " + fieldClass.getName() + autowiredValue);
                    } else {
                        // 6、通过反射将对应的成员变量实例注入到成员变量所在的类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, filedValue, true);
                    }
                }
            }
        }
    }

    /**
     * 根据Class在beanContainer里获取其实例或者实现类
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object filedValue = beanContainer.getBean(fieldClass);
        if (filedValue != null) {
            return filedValue;
        } else {
            Class<?> implementedClass = getImplementClass(fieldClass, autowiredValue);
            if (implementedClass != null) {
                return beanContainer.getBean(implementedClass);
            } else {
                return null;
            }
        }
    }

    /**
     * 获取接口的实现类
     */
    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)) {
            if (ValidationUtil.isEmpty(autowiredValue)) {
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                } else {
                    // 如果多于两个实现类且用户未指定其中一个实现类，则抛出异常
                    throw new RuntimeException("multiple implemented classes for " + fieldClass.getName() + ". please set @Autowired's value to pick one");
                }
            } else {
                for (Class<?> clazz : classSet) {
                    if (autowiredValue.equals(clazz.getSimpleName())) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }

}

```





##### 5-11 本章小结

![](images\IOC容器实现6.png)





#### 第6章 SpringIoC容器的源码解析

##### 6-1 挖掘切入源码的线索

**主线逻辑：**

- **解析配置**
- **定位与注册对象**
- **注入对象**



**解决了关键的问题：将对象之间的关系转而用配置来管理：**

- <span style="color:red">依赖注入</span>：依赖关系在Spring的IoC容器中管理
- <span style="color:red">通过把对象包装在Bean中以达到管理对象和进行额外操作的目的</span>



##### 6-2 Bean与BeanDefinition

**Bean：**

- Bean的本质就是java对象，只是这个对象的声明周期由容器来管理
- 不需要为了创建Bean而在原来的java类上添加任何额外的限制
- 对java对象的控制方式体现在配置上



**BeanDefinition---Bean的定义（类似java.lang.class对对象的描述）：**

- <span style="color:red">作用范围scope（@Scope）</span>
- <span style="color:red">懒加载lazy-init（@Lazy）：决定Bean实例是否延迟加载</span>
- <span style="color:red">首选primary（@Primary）：设置为true的bean会是优先的实现类</span>
- factory-bean和factory-method（@Configuration和@Bean）



**容器初始化主要做的事情（主要脉络）：**

![](images\SpringIOC1.png)



##### 6-3 关于BeanDefinition

![](images\SpringIOC2.png)

![](images\SpringIOC3.png)



##### 6-4 BeanFactory和FactoryBean

**BeanFactory：**

- <span style="color:red">Bean工厂，IOC容器的顶级接口，所有的IOC容器都实现了此接口，提供了一些关于IOC容器操作的基本方法。</span>

**FactoryBean：**

- <span style="color:red">工厂Bean，本质为Bean对象，通过bean的id获取到的实质上是调用的FactoryBean的getObject返回的对象，可以通过该方法定制Bean，如若需要获取该FactoryBean，需要id为 &xxx 。</span>



##### 6-5 关于简单容器

![](images\SpringIOC4.png)



- **基于单一职责原则，不同的Factory接口提供了不同的功能。**
  - <span style="color:red">DefaultListableBeanFactory中的**beanDefinitionMap**，提供了对beanDefinition的操作。</span>
- **ApplicationContext是高级容器接口，广泛使用的容器，提供了许多面向实际应用的功能。**



**术语补充：**

- 组件扫描：自动发现应用容器中需要创建的Bean
- 自动装配：自动满足Bean之间的依赖



##### 6-6 关于高级容器

![](images\SpringIOC5.png)

**高级容器皆实现了ApplicationContext接口**

**ApplicationContext接口：**

- 实现<span style="color:red">EnvironmentCapable</span>接口
  - 提供了获取运行时配置环境的方法：getEnvironment();
- 实现<span style="color:red">ListableBeanFactory</span>接口
  - 提供可以通过列表的形式管理Bean的方法
- 实现<span style="color:red">HierarchicalBeanFactory</span>接口
  - 提供获取层级关系的工厂
- 实现<span style="color:red">ResourcePatternResolver</span>接口
  - 提供加载资源的功能
- 实现<span style="color:red">MessageSource</span>接口
  - 管理message，提供国际化的功能
- 实现<span style="color:red">ApplicationEventPublisher</span>接口
  - 提供事件发布的能力



##### 6-7 ApplicationContext常用容器

**传统的基于XML配置的经典容器：**

- <span style="color:red">FileSystemXmlApplicationContext</span>：从文件系统加载配置
- <span style="color:red">ClassPathXmlApplicationContext</span>：从classpath加载配置
- <span style="color:red">XmlWebApplicationContext</span>：用于Web应用程序的容器

**目前比较流行的容器（基于注解）：**

- <span style="color:red">AnnotationConfigServletWebServerApplicationContext</span>：在SpringBoot模块下
- <span style="color:red">AnnotationConfigReactiveWebServerApplicationContext</span>：提供响应式编程，在SpringBoot模块下
- <span style="color:red">AnnocationConfigApplicationContext</span>：spring模块下目前常用的

**容器的共性——<span style="color:red">refresh()</span>方法：**

- 容器初始化、配置解析
- BeanFactoryPostProcessor和BeanPostProcessor的注册和激活
- 国际化配置
- ......

**核心抽象类<span style="color:red">AbstractApplicationContext</span>：**

- 提供了高级容器的绝大部分方法
- 基于**模板方法模式**，将某些设置下沉至子类，



##### 6-8 模板方式模式

**围绕抽象类，实现通用逻辑，定义模板结构，部分逻辑由子类实现。**

- <span style="color:red">复用代码</span>

- <span style="color:red">反向控制 </span>

  

![](images\SpringIOC6.png)



##### 6-9 弄清Resource、ResourceLoader、容器之间的微妙关系

###### 6-9-1 Resource

![](images\SpringIOC7.png)

**根据资源地址自动选择正确的Resource：**

- **强大的加载资源的方式**

  - 自动识别"classpath:"、"file:" 等资源地址前缀

  - 支持自动解析Ant风格带通配符的资源地址

    - Ant：路径匹配表达式，用来对URI进行匹配（类似正则表达式）

      - ? 匹配任何单字符

      - \* 匹配0或任意数量的字符

      - ** 匹配0或者更多的目录

        ![](images\SpringIOC8.png)



###### 6-9-2 ResourceLoader

**实现不同的Resource加载策略，按需返回特定类型的Resource**

![](images\SpringIOC9.png)

- **DefaultResourceLoader#getResource(String location)**
- **ResourcePatternResolver**：支持Ant匹配模式
- 注意：此处有**AbstractApplicationContext，所以高级容器支持统一资源加载**



##### 6-10 ResourceLoader的使用者-BeanDefinitionReader

- **读取BeanDefinition**
- **利用BeanDefinitionRegistry注册BeanDefinition**

![](images\SpringIOC10.png)

**几个关键的类：**

- <span style="color:red">location</span>
- <span style="color:red">Resource</span>
- <span style="color:red">ResourceLoader</span>
- <span style="color:red">BeanDefinitionReader</span>
- <span style="color:red">BeanDefinitionRegistry</span>
- <span style="color:red">DefaultListableBeanFactory</span>



##### 6-11 BeanDefinition的注册

```java
# DefaultBeanDefinitionDocumentReader.java
......
// 向Spring IOC容器注册解析得到的BeanDefinition，这是BeanDefinition向IOC容器注册的入口
BeanDefinitionReaderUtils.registerBeanDefinition(dbHolder, getReaderContext().getRegistry());    
......    
```

```java
#BeanDefinitionReaderUtils.java
......
// 将beanDefinition及其名字注册到容器里    
String beanName = definitionHolder.getBeanName();    
registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());    
......    
```

```java
#DefaultListableBeanFactory.java
#registerBeanDefinition
beanDefinitionMap.xxx    
```



##### 6-12 本章小结

![](images\SpringIOC11.png)



![](images\SpringIOC12.png)





#### 第7章 详解SpringIoC容器的初始化 【打通refresh方法的全链路】

##### 7-1 后置处理器之PostProcessor

**本身也是一种需要注册到容器里的Bean**

- <span style="color:red">其里面的方法会在特定的时机被容器调用</span>
- <span style="color:red">实现不改变容器或者Bean核心逻辑的情况下对Bean进行扩展</span>
- <span style="color:red">对Bean进行包装、影响其行为、修改Bean的内容等</span>



###### 7-1-1 PostProcessor的种类

**大类分为<span style="color:red">容器级别的后置处理器</span>以及<span style="color:red">Bean级别的后置处理器</span>**

- <span style="color:red">BeanDefinitionRegistryPostProcessor</span>
  - 可注册BeanDefinition
  - 可以使用此将第三方框架的能力集成进spring
- <span style="color:red">BeanFactoryPostProcessor</span>
- <span style="color:red">BeanPostProcessor</span>
  - 在每一个Bean初始化之前和之后执行，可以实现对Bean的包装
  - postProcessBeforeInitialization
  - postProcessAfterInitialization



##### 7-2 Aware及其子接口

**通过实现相关接口可以获取容器中的相关组件。**

**从Bean里获取到的容器实例并对其进行操作。**

![](images\SpringIOC13.png)



##### 7-3 进攻refresh方法前必会知识之事件监听器模式

###### 7-3-1 事件监听器模式

**回调函数：**

- 往组件注册自定义的方法以便组件在特定场景下调用

**事件监听器模式：**

- **监听器将监听感兴趣的事件，一旦事件发生，便做出响应**
- <span style="color:red">事件源（Event Source）</span>
- <span style="color:red">事件监听器（Event Listener）</span>
- <span style="color:red">事件对象（Event Object）</span>

```java
@Data
public class Event {
    private String type;
}
```

```java
public interface EventListener {
    public void processEvent(Event event);
}
```

```java
public class SingleClickEventListener implements EventListener {
    @Override
    public void processEvent(Event event) {
        if("singleclick".equals(event.getType())) {
            System.out.println("单击被触发")
        }
    }
}
```

```java
public class DoubleClickEventListener implements EventListener {
    @Override
    public void processEvent(Event event) {
        if("doubleclick".equals(event.getType())) {
            System.out.println("双击被触发")
        }
    }
}
```

```java
public class EventSource {
    private List<EventListener> listenerList = new ArrayList<>();
    
    public void register(EventListener listener) {
        listenerList.add(listener);
    }
    
    public void publishEvent(Event event) {
        for (EventListener listener.: listenerList) {
            listener.processEvent(event);
        }
    }
}
```

```java
public class EventModeDemo {
    EventSource eventSource = new EventSource();
    SingleClickEventListener singleClickEventListener = new SingleClickEventListener();
    DoubleClickEventListener doubleClickEventListener = new DoubleClickEventListener();
    Event event = new Event();
    event.setType("doubleclick");
    eventSource.register(singleClickEventListener);
    eventSource.register(doubleClickEventListener);
    eventSource.publishEvent(event);
}
```



###### 7-3-2 Spring的事件驱动模型

**事件驱动模型的三大组成部分：**

- <span style="color:red">事件：ApplicationEvent抽象类</span>

  ![](images\SpringIOC14.png)

- <span style="color:red">事件监听器：ApplicationListener</span>

  ![](images\SpringIOC15.png)

- <span style="color:red">事件发布器：Publisher以及Multicaster</span>

  ![](images\SpringIOC16.png)

  

##### 7-5 Spring容器的刷新逻辑（5.0.9版本）

- **<span style="color:red">1、prepareRefresh()：刷新前的工作准备</span>**
- **<span style="color:red">2、obtainFreshBeanFactory()：获取子类刷新后的内部beanFactory实例</span>**
- **<span style="color:red">3、prepareBeanFactory()：为容器注册必要的系统级别的Bean</span>**
- **<span style="color:red">4、postProcessBeanFactory()：允许容器的子类去注册postProcessor</span>**
- **<span style="color:red">5、invokeBeanFactoryPostProcessors()：调用容器注册的容器级别的后置处理器</span>**
- **<span style="color:red">6、registerBeanPostProcessors()：向容器注册Bean级别的后置处理器</span>**
- **<span style="color:red">7、initMessageSource()：初始化国际化配置</span>**
- **<span style="color:red">8、initApplicationEventMulticaster()：初始化事件发布者组件</span>**
- **<span style="color:red">9、onRefresh()：在单例Bean初始化之前预留给子类初始化其它特殊bean的途径</span>**
- **<span style="color:red">10、registerListeners()：向前面的事件发布者组件注册事件监听</span>**
- **<span style="color:red">11、finishBeanFactoryInitialization()：设置系统级别的服务，实例化所有非懒加载的单例</span>**
- **<span style="color:red">12、finishRefresh()：触发初始化完成的回调方法，并发布容器刷新完成的事件给监听者</span>**
- **<span style="color:red">13、resetCommonCaches()：重置Spring内核中的共用缓存</span>**



###### 7-5-1 prepareRefresh()

```
// Prepare this context for refreshing.
prepareRefresh();
```

```java
	/**
	 * Prepare this context for refreshing, setting its startup date and
	 * active flag as well as performing any initialization of property sources.
	 */
	protected void prepareRefresh() {
		this.startupDate = System.currentTimeMillis();
		this.closed.set(false);
		this.active.set(true);

		if (logger.isInfoEnabled()) {
			logger.info("Refreshing " + this);
		}

		// Initialize any placeholder property sources in the context environment
		initPropertySources();

		// Validate that all properties marked as required are resolvable
		// see ConfigurablePropertyResolver#setRequiredProperties
		getEnvironment().validateRequiredProperties();

		// Allow for the collection of early ApplicationEvents,
		// to be published once the multicaster is available...
		this.earlyApplicationEvents = new LinkedHashSet<>();
	}
```



###### 7-5-2 ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

**告诉子类启动refreshBeanFactory()方法，Bean定义资源文件的载入从子类的refreshBeanFactory()方法启动，里面有抽象方法。**
**针对xml配置，最终创建内部容器，该容器负责Bean的创建与管理，此步会进行BeanDefinition的注册。**

```
// Tell the subclass to refresh the internal bean factory.
ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
```

```java
	/**
	 * Tell the subclass to refresh the internal bean factory.
	 * @return the fresh BeanFactory instance
	 * @see #refreshBeanFactory()
	 * @see #getBeanFactory()
	 */
	protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
		refreshBeanFactory();
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		if (logger.isDebugEnabled()) {
			logger.debug("Bean factory for " + getDisplayName() + ": " + beanFactory);
		}
		return beanFactory;
	}
```



###### 7-5-3 prepareBeanFactory(beanFactory)

```java
// Prepare the bean factory for use in this context.
prepareBeanFactory(beanFactory);
```

```java
	/**
	 * Configure the factory's standard context characteristics,
	 * such as the context's ClassLoader and post-processors.
	 * @param beanFactory the BeanFactory to configure
	 */
	protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// Tell the internal bean factory to use the context's class loader etc.
		beanFactory.setBeanClassLoader(getClassLoader());
		beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader()));
		beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));

		// Configure the bean factory with context callbacks.
		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
		beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
		beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
		beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
		beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
		beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
		beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);

		// BeanFactory interface not registered as resolvable type in a plain factory.
		// MessageSource registered (and found for autowiring) as a bean.
		beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
		beanFactory.registerResolvableDependency(ResourceLoader.class, this);
		beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
		beanFactory.registerResolvableDependency(ApplicationContext.class, this);

		// Register early post-processor for detecting inner beans as ApplicationListeners.
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));

		// Detect a LoadTimeWeaver and prepare for weaving, if found.
		if (beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
			beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
			// Set a temporary ClassLoader for type matching.
			beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
		}

		// Register default environment beans.
		if (!beanFactory.containsLocalBean(ENVIRONMENT_BEAN_NAME)) {
			beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
		}
		if (!beanFactory.containsLocalBean(SYSTEM_PROPERTIES_BEAN_NAME)) {
			beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
		}
		if (!beanFactory.containsLocalBean(SYSTEM_ENVIRONMENT_BEAN_NAME)) {
			beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
		}
	}
```





#### 第8章 精讲SpringIoC容器的依赖注入

Spring在Bean实例的创建过程中做了很多精细化控制



- **AbstractBeanFactory**
  - **doGetBean：获取Bean实例**
- **DefaultSingletonRegistry**
  - **getSingleton：获取单例实例**
  - **三级缓存：解决循环依赖**
- **AbstractAutowiredCapableBeanFactory**
  - **createBean：创建Bean实例的准备**
  - **doCreateBean：创建Bean实例**
  - **applyMergedBeanDefinitionPostProcessors：处理@Autowired以及@Value**
  - **populateBean：给Bean实例注入属性值（依赖注入在此）**
- **AutowiredAnnotationBeanPostProcessor**
  - **postProcessorProperties：Autowired的依赖注入逻辑**
- **DefaultListableBeanFactory**
  - **doResolveDependency：依赖解析**
- **DependencyDescriptor**
  - **InjectionPotiont：创建依赖实例**



##### 8-1 doGetBean逻辑

1. **<span style="color:red">尝试从缓存获取Bean</span>**
2. **<span style="color:red">循环依赖的判断</span>**
3. **<span style="color:red">递归去父容器获取Bean实例</span>**
4. **<span style="color:red">从当前容器获取BeanDefinition实例</span>**
5. **<span style="color:red">递归实例化显示依赖的Bean depends-on</span>**
6. **<span style="color:red">根据不同的Scope采用不同的策略创建Bean实例</span>**
7. **<span style="color:red">对Bean进行类型检查</span>**



**AbstractBeanFactory#doGetBean**

```java
protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
			@Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {
		// 通过三种形式获取beanName
    	// 一种是原始的beanName，一个是加了&的，一个是别名
		final String beanName = transformedBeanName(name);
		Object bean;

		// Eagerly check singleton cache for manually registered singletons.
    	// 尝试从单例缓存集合里获取bean实例，详解下一个方法描述
		Object sharedInstance = getSingleton(beanName);
    	// args之所以要为空，是因为如果有args，则需要做进一步赋值，因此无法直接返回
		if (sharedInstance != null && args == null) {
			if (logger.isDebugEnabled()) {
                // 如果Bean还在创建中，则说明是循环引用
				if (isSingletonCurrentlyInCreation(beanName)) {
					logger.debug("Returning eagerly cached instance of singleton bean '" + beanName +
							"' that is not fully initialized yet - a consequence of a circular reference");
				}
				else {
					logger.debug("Returning cached instance of singleton bean '" + beanName + "'");
				}
			}
            // 如果是普通bean，直接返回，如果是FactoryBean，则返回它的getObject
			bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
		}

		else {
			// Fail if we're already creating this bean instance:
			// We're assumably within a circular reference.
            // 如果scope为prototype并且显示还在创建中，则基本是循环依赖的情况
            // 针对prototype的循环依赖，spring无解，直接抛出异常
			if (isPrototypeCurrentlyInCreation(beanName)) {
				throw new BeanCurrentlyInCreationException(beanName);
			}

			// Check if bean definition exists in this factory.
			BeanFactory parentBeanFactory = getParentBeanFactory();
			if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
				// Not found -> check parent.
				String nameToLookup = originalBeanName(name);
				if (parentBeanFactory instanceof AbstractBeanFactory) {
					return ((AbstractBeanFactory) parentBeanFactory).doGetBean(
							nameToLookup, requiredType, args, typeCheckOnly);
				}
				else if (args != null) {
					// Delegation to parent with explicit args.
					return (T) parentBeanFactory.getBean(nameToLookup, args);
				}
				else {
					// No args -> delegate to standard getBean method.
					return parentBeanFactory.getBean(nameToLookup, requiredType);
				}
			}

			if (!typeCheckOnly) {
				markBeanAsCreated(beanName);
			}

			try {
				final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
				checkMergedBeanDefinition(mbd, beanName, args);

				// Guarantee initialization of beans that the current bean depends on.
				String[] dependsOn = mbd.getDependsOn();
				if (dependsOn != null) {
					for (String dep : dependsOn) {
						if (isDependent(beanName, dep)) {
							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
									"Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
						}
						registerDependentBean(dep, beanName);
						try {
							getBean(dep);
						}
						catch (NoSuchBeanDefinitionException ex) {
							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
									"'" + beanName + "' depends on missing bean '" + dep + "'", ex);
						}
					}
				}

				// Create bean instance.
				if (mbd.isSingleton()) {
					sharedInstance = getSingleton(beanName, () -> {
						try {
							return createBean(beanName, mbd, args);
						}
						catch (BeansException ex) {
							// Explicitly remove instance from singleton cache: It might have been put there
							// eagerly by the creation process, to allow for circular reference resolution.
							// Also remove any beans that received a temporary reference to the bean.
							destroySingleton(beanName);
							throw ex;
						}
					});
					bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
				}

				else if (mbd.isPrototype()) {
					// It's a prototype -> create a new instance.
					Object prototypeInstance = null;
					try {
						beforePrototypeCreation(beanName);
						prototypeInstance = createBean(beanName, mbd, args);
					}
					finally {
						afterPrototypeCreation(beanName);
					}
					bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
				}

				else {
					String scopeName = mbd.getScope();
					final Scope scope = this.scopes.get(scopeName);
					if (scope == null) {
						throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
					}
					try {
						Object scopedInstance = scope.get(beanName, () -> {
							beforePrototypeCreation(beanName);
							try {
								return createBean(beanName, mbd, args);
							}
							finally {
								afterPrototypeCreation(beanName);
							}
						});
						bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
					}
					catch (IllegalStateException ex) {
						throw new BeanCreationException(beanName,
								"Scope '" + scopeName + "' is not active for the current thread; consider " +
								"defining a scoped proxy for this bean if you intend to refer to it from a singleton",
								ex);
					}
				}
			}
			catch (BeansException ex) {
				cleanupAfterBeanCreationFailure(beanName);
				throw ex;
			}
		}

		// Check if required type matches the type of the actual bean instance.
		if (requiredType != null && !requiredType.isInstance(bean)) {
			try {
				T convertedBean = getTypeConverter().convertIfNecessary(bean, requiredType);
				if (convertedBean == null) {
					throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
				}
				return convertedBean;
			}
			catch (TypeMismatchException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Failed to convert bean '" + name + "' to required type '" +
							ClassUtils.getQualifiedName(requiredType) + "'", ex);
				}
				throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
			}
		}
		return (T) bean;
	}
```



**DefaultSingletonBeanRegistry#getSingleton**

```java
	protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        // 尝试从一级缓存里面获取完备的Bean
		Object singletonObject = this.singletonObjects.get(beanName);
        // 如果完备的单例还没有创建出来，创建中的Bean的名字会被保存在singletonsCurrentlyInCreation中
        // 因此看看是否正在创建
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            // 尝试给一级缓存对象加锁，因为接下来就要对缓存对象操作了
			synchronized (this.singletonObjects) {
                // 尝试从二级缓存earlySingletonObjects这个存储还没进行属性添加操作的Bean实例缓存中获取
				singletonObject = this.earlySingletonObjects.get(beanName);
                // 如果还没有获取到并且第二个参数为true，为true则表示bean允许被循环引用
				if (singletonObject == null && allowEarlyReference) {
                    // 从三级缓存singletonFactories这个ObjectFactory实例的缓存里尝试获取创建此Bean的单例工厂实例
					ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
					if (singletonFactory != null) {
                        // 调用单例工厂的getObject方法返回对象实例
						singletonObject = singletonFactory.getObject();
                        // 将实例放入二级缓存里
						this.earlySingletonObjects.put(beanName, singletonObject);
                        // 从三级缓存里移除
						this.singletonFactories.remove(beanName);
					}
				}
			}
		}
		return singletonObject;
	}
```



##### 8-2 createBean逻辑

......



##### 8-3 @Autowired以及@Value

主要基于AutowiredAnnotationBeanPostProcessor



##### 8-4 单例循环依赖

**populateBean() 递归 createBean 处理循环依赖**

![](images\循环依赖1.png)



##### 8-5 面试常问问题之Spring对循环依赖的支持情况

**循环依赖的情况如下：**

- <span style="color:red">构造器循环依赖（singleton、prototype）</span>
- <span style="color:red">Setter注入循环依赖（singleton、prototype）</span>



- **prototype不支持循环依赖**
  - 因为没有设置三级缓存进行支持
  - 只能通过将Bean名字放入缓存里阻断无限循环
- **构造器循环依赖不支持**





#### 第9章 自研框架AOP的讲解与实现

##### 9-1 AOP相关概念

- 切面Aspect：将横切关注点逻辑进行模块化封装的实体对象
- 通知Advice：好比是Class里面的方法，还定义了织入逻辑的时机
- 连接点Joinpoint：允许使用Advice的地方
- SpringAOP默认只支持方法级别的Joinpoint
- 切入点Pointcut：定义一系列规则对Joinpoint进行筛选
- 目标对象Target：符合Pointcut条件，要被织入横切逻辑的对象



##### 9-2 Advice的种类

- **BeforeAdvice**：在JoinPoint前被执行的Advice
- **AfterAdvice**：好比try...catch...finally里面的finally
- **AfterReturningAdvice**：在Joinpoint执行流程正常返回后被执行
- **AfterThrowingAdvice**：Joinpoint执行过程中抛出异常才会触发
- **AroundAdvice**：在Joinpoint前和后都指向，最常用的Advice

![](images\单个Aspect的执行顺序.png)

![](images\多个Aspect的执行顺序2.png)



##### 9-3 SpringAOP的实现之JDK动态代理

- **寻求改进**
  - <span style="color:red">溯源ClassLoader</span>
    - 通过带有包名的类来获取对应class文件的二进制字节流
    - 根据读取的字节流，将代表的静态存储结构转化为运行时数据结构
    - 生成一个代表该类的Class对象，作为方法区该类的数据访问入口
  
- **改进的切入点**
  - <span style="color:red">根据一定规则去改动或者生成新的字节流，将切面逻辑织入其中</span>
    - 行之有效的方案就是取代被代理类的动态代理机制
    - 根据接口或者目标类，计算出代理类的字节码并加载到JVM中去
  
- **JDK动态代理**

  - 程序运行时动态生成类的字节码，并加载到JVM中

  - 要求【被代理的类】必须实现接口

  - 并不要求【代理对象】去实现接口，所以可以复用代理对象的逻辑

    ```java
    public class JdkDynamicProxyUtil {
        public static <T> T newProxyInstance(T targetObject, InvocationHandler handler) {
            ClassLoader classLoader = targetObject.getClass().getClassLoader();
            Class<?>[] interfaces = targetObject.getClass().getInterfaces();
            return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
        }
    
    }
    ```

    ```java
    /**
     * 织入的切面逻辑
     */
    public class AlipayInvocationHandler implements InvocationHandler {
    
        private Object targetObject;
    
        public AlipayInvocationHandler(Object targetObject) {
            this.targetObject = targetObject;
        }
    
        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            beforePay();
            Object result = method.invoke(targetObject, args);
            afterPay();
            return result;
        }
    
        private void beforePay() {
            System.out.println("~~~~取款~~~~");
        }
    
        private void afterPay() {
            System.out.println("~~~~支付~~~~");
        }
    }
    ```

    

##### 9-4 SpringAOP的实现原理之CGLIB动态代理

**代码生成库：Code Generation Library**

- 不要求被代理类实现接口
- 内部主要封装了ASM Java字节码操控框架
- 动态生成子类以覆盖非final的方法，绑定钩子回调自定义拦截器

```xml
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.2.9</version>
        </dependency>
```

```java
public class AlipayMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        beforePay();
        Object result = methodProxy.invokeSuper(o, args);
        afterPay();
        return result;
    }

    private void beforePay() {
        System.out.println("取钱");
    }

    private void afterPay() {
        System.out.println("花钱");
    }

}
```

```java
public class CglibUtil {
    public static <T> T create(T targetObject, MethodInterceptor methodInterceptor) {
        return (T) Enhancer.create(targetObject.getClass(), methodInterceptor);
    }
}
```

```java
public class Demo {
    public static void main(String[] args) {
        MethodInterceptor methodInterceptor = new AlipayMethodInterceptor();
        CommonPay commonPay = CglibUtil.create(new CommonPay(), methodInterceptor);
        commonPay.pay();
    }

    static class CommonPay {
        public void pay() {
            System.out.println("开始~~~~");
        }
    }
}
```



##### 9-5 JDK动态代理和CGLIB

**JDK动态代理的优势：**

- JDK原生，在JVM里运行较为可靠
- 平滑支持JDK版本的升级

**CGLIB的优势：**

- 被代理对象无需实现接口，能实现代理类的无侵入

**SpringAOP的底层机制：**

- <span style="color:red">CGLIB和JDK动态代理共存</span>
- 默认策略：Bean实现了接口则用JDK，否则使用CGLIB



##### 9-8 实现自研框架AOP1.0

**使用CGLIB来实现：不需要业务类实现接口，相对灵活**

- 解决标记的问题，定义横切逻辑的骨架
- 定义Aspect横切逻辑以及被代理方法的执行顺序
- 将横切逻辑织入被代理的对象以生成动态代理对象



###### 9-8-1 解决横切逻辑的标记问题以及定义Aspect骨架

- 定义与横切逻辑相关的注解
- 定义供外部使用的横切逻辑骨架

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
```

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    int value();
}
```

```java
public abstract class DefaultAspect {

    /**
     * 事前拦截
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @throws Throwable
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {

    }

    /**
     * 事后拦截
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @param returnValue 被代理的目标方法执行后的返回值
     * @throws Throwable
     */
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        return returnValue;
    }

    /**
     *
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @param e 被代理的目标方法抛出的异常
     * @throws Throwable
     */
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {

    }

}
```



###### 9-8-2 实现Aspect横切逻辑以及被代理方法的定序执行

- 创建MethodInterceptor的实现类

  ```java
  public class AspectListExecutor implements MethodInterceptor {
  
      // 被代理的类
      private Class<?> targetClass;
  
      @Getter
      List<AspectInfo> sortedAspectInfoList;
  
      public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
          this.targetClass = targetClass;
          this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList);
      }
  
      /**
       * 按照order的值进行升序排序，确保order值小的aspect先被织入
       * @param aspectInfoList
       * @return
       */
      private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
          Collections.sort(aspectInfoList, Comparator.comparingInt(AspectInfo::getOrderIndex));
          return aspectInfoList;
      }
  
  
      @Override
      public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
          Object returnValue = null;
          if (ValidationUtil.isEmpty(sortedAspectInfoList)) {return returnValue;}
          //1、按照order的顺序升序执行完所有Aspect的before方法
          invockBeforeAdvices(method, args);
  
          try {
              //2、执行被代理类的方法
              returnValue = methodProxy.invokeSuper(proxy, args);
              //3、如果被代理方法正常返回，则按照order的顺序降序执行完所有Aspect的afterReturning方法
              returnValue = invockAfterReturningAdvices(method, args, returnValue);
          } catch (Exception e) {
              //4、如果被代理方法抛出异常，则按照order的顺序降序执行完所有Aspect的afterThrowing方法
              invockeThrowingAdvices(method, args, e);
          }
  
          return returnValue;
      }
  
      //4、如果被代理方法抛出异常，则按照order的顺序降序执行完所有Aspect的afterThrowing方法
      private void invockeThrowingAdvices(Method method, Object[] args, Exception e) throws Throwable {
          for (int i = sortedAspectInfoList.size() - 1; i >= 0; i--) {
              sortedAspectInfoList.get(i).getAspectObject()
                      .afterThrowing(targetClass.getClass(), method, args, e);
          }
      }
  
      //3、如果被代理方法正常返回，则按照order的顺序降序执行完所有Aspect的afterReturning方法
      private Object invockAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable {
          Object result = null;
          for (int i = sortedAspectInfoList.size() - 1; i >= 0; i--) {
              result = sortedAspectInfoList.get(i).getAspectObject()
                      .afterReturning(targetClass.getClass(), method, args, returnValue);
          }
          return result;
      }
  
      //1、按照order的顺序升序执行完所有Aspect的before方法
      private void invockBeforeAdvices(Method method, Object[] args) throws Throwable {
          for (AspectInfo aspectInfo : sortedAspectInfoList) {
              aspectInfo.getAspectObject().before(targetClass.getClass(), method, args);
          }
      }
  }
  ```

  ```java
  public class ProxyCreator {
      public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor) {
          return Enhancer.create(targetClass, methodInterceptor);
      }
  }
  ```

- 定义必要的成员变量——被代理的类以及Aspect列表

  ```java
  @AllArgsConstructor
  @Getter
  public class AspectInfo {
      private int orderIndex;
      private DefaultAspect aspectObject;
  }
  ```

- 按照Order对Aspect进行排序

- 实现对横切逻辑以及被代理对象方法的定序执行

  ```java
  public class AspectWeaver {
      private BeanContainer beanContainer;
      public AspectWeaver() {
          this.beanContainer = BeanContainer.getInstance();
      }
  
      public void doAop() {
          //1、获取所有的切面类
          Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
  
          //2、将切面类按照不同的织入目标进行切分
          Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap = new HashMap<>();
          if (ValidationUtil.isEmpty(aspectSet)) {
              return;
          }
  
          for (Class<?> aspectClass : aspectSet) {
              if (verifyAspect(aspectClass)) {
                  categorizeAspect(categorizedMap, aspectClass);
              } else {
                  throw new RuntimeException("@Aspect and @Order have not been added to the Aspect class," +
                          "or Aspect class does not extends from DefaultAspect, or the value in Aspect Tag equals @Aspect");
              }
          }
  
          //3、按照不同的织入目标分别去按织入Aspect的逻辑
          if (ValidationUtil.isEmpty(categorizedMap)) {
              return;
          }
          for (Class<? extends Annotation> category : categorizedMap.keySet()) {
              weaveByCategory(category, categorizedMap.get(category));
          }
      }
  
      private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfos) {
          //1.获取被代理类的集合
          Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
          if (ValidationUtil.isEmpty(classSet)) {
              return;
          }
  
          //2.遍历被代理类，分别为每个被代理类生成动态代理实例
          for (Class<?> targetClass : classSet) {
              //创建动态代理对象
              AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, aspectInfos);
              Object proxyBean = ProxyCreator.createProxy(targetClass, aspectListExecutor);
              //3.将动态代理对象实例添加到容器里，取代未被代理前的类实例
              beanContainer.addBean(targetClass, proxyBean);
          }
  
  
      }
  
      //2.将切面类按照不同的织入目标进行切分
      private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap, Class<?> aspectClass) {
          Order orderTag = aspectClass.getAnnotation(Order.class);
          Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
          DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
          AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect);
          if (!categorizedMap.containsKey(aspectTag.value())) {
              //如果织入的joinpoint第一次出现，则以该joinpoint为key，以新创建的List<AspectInfo>为value
              List<AspectInfo> aspectInfoList = new ArrayList<>();
              aspectInfoList.add(aspectInfo);
              categorizedMap.put(aspectTag.value(), aspectInfoList);
          } else {
              //如果织入的joinpoint不是第一次出现，则往joinpoint对应的value里面添加新的Aspect逻辑
              List<AspectInfo> aspectInfoList = categorizedMap.get(aspectTag.value());
              aspectInfoList.add(aspectInfo);
          }
      }
  
      //框架中一定要遵守给Aspect类添加@Aspect和@Order标签的规范，同时必须继承自DefaultAspect.class
      //此外，@Aspect的属性值不能是它本身
      private boolean verifyAspect(Class<?> aspectClass) {
          return aspectClass.isAnnotationPresent(Aspect.class) &&
                  aspectClass.isAnnotationPresent(Order.class) &&
                  DefaultAspect.class.isAssignableFrom(aspectClass) &&
                  aspectClass.getAnnotation(Aspect.class).value() != Aspect.class;
      }
  }
  ```

- 测试

  ```java
  public class AspectWeaverTest {
  
      @DisplayName("织入通用逻辑测试：dopAop")
      @Test
      public void doAopTest() {
          BeanContainer beanContainer = BeanContainer.getInstance();
          beanContainer.loadBeans("com.melon");
          //先aop，使对应的对象都为代理对象，再ioc
          new AspectWeaver().doAop();
          new DependencyInjector().doIoc();
          HeadLineOperationController headLineOperationController = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
          headLineOperationController.addHeadLine(null, null);
      }
  }
  ```

  

##### 9-9 自研框架的AOP1.0待改进的地方

- Aspect只支持对被某个标签标记的类进行横切逻辑的织入
- 需要披上AspectJ的外衣