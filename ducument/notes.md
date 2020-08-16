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