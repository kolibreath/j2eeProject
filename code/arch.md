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
