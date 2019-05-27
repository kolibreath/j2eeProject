# 项目架构
- [Quartz 定时任务框架](https://juejin.im/post/5ab77f5d6fb9a028e52dccc3)
这篇文章也说到了在Sprig-boot 中整合Quartz
- 贫血模式
贫血模式主要针对对纯数据的操作。一个用作保存数据的对象不应该有除了set get 之外的方法，保存这个对象中的内容到数据库的方式使用manager中的save方法。
- 关于cron 需要知道的内容
cron是linux上一个定时任务工具

# 使用IDEA 创建Spring boot 项目
[Spring boot 相关资料](http://www.ityouknow.com/spring-boot)
使用Idea 构建Spring boot 项目
Spring initializr -> 勾选项目 web ，template engines 中选择 thymeleaf ，sql 选择jpa，然后选择创建pom.xml
参考这个[项目](http://tengj.top/2017/02/26/springboot1/)创建一个spring boot项目

- 出现呢 If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
解决方法
在Application 类上加上注解：
```
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
```

#Spring boot 链接mysql
Springboot 中我们使用的是jpa 去链接mysql 数据库
[jpa 链接数据库的方法](https://blog.csdn.net/jinbaosite/article/details/77587600)

## Sprig boot 链接mysql
- 相关配置
```
# 服务器访问端口
server.port=8080
# 数据库基本配置 连接到你的test 数据库
# 显式声明你的用户 名和密码
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/staff_management
spring.datasource.username=kolibreath
spring.datasource.password=szypride
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.database=MYSQL
# 显示后台处理的SQL语句
spring.jpa.show-sql=true
# 自动检查实体和数据库表是否一致，如果不一致则会进行更新数据库表
spring.jpa.hibernate.ddl-auto=update
```
注意最后一行，使用最后一行会在启动数据库的时候drop掉你的表
## 编写相关类
[参考博客](https://juejin.im/post/5aa733af518825558a0646fb)
数据库中的表不需要手动编写，但是数据库本身还是需要创建的

<br>
打开终端

```
create database staff_management
```
创建项目需要的数据库

## 创建实体类

```
@Entity
@Table(name ="staff")
public class Staff {

    @Id
    private int staffId;

    @Column(length = 255)
    private int staffType;

    @Column(length = 255)
    private String name;


    @Column(length = 255)
    private String title;
}
```

在application类上修改注解成：


```
@EnableAutoConfiguration
@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

```
如果``EnableAutoConfiguration``提示redundant annotation，大可不必理他<br>
然后确认properties文件没有问题,点``run``看看staff_management中又没有出现staff表

## Spring 访问静态资源
```
- static
- templates
- resources
```
``static``表示静态资源，表示直接通过浏览器中的url访问，如果要使用``@Controller`` 跳转到另外的页面去需要放在templates下yy

## Spring 传递数据给html
[传递数据](https://blog.csdn.net/weixin_36380516/article/details/78668199)