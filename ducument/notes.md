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