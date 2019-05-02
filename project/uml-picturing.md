# UML绘制
## 类之间的关系
> 绘制uml图的表示符号
1. 关联
- 普通关联
![](https://upload-images.jianshu.io/upload_images/4714178-ce3be5ef78746d85.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
关联方向，关联重数
递归关联
![屏幕快照 2019-05-02 19.47.10.png](https://upload-images.jianshu.io/upload_images/4714178-315e65e24c914cfd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 限定关联
- 关联类

![](https://upload-images.jianshu.io/upload_images/4714178-05098eefbc0776bc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2. 聚集
- 组合聚集
一个部分的存在依赖于整个实体，如果实体消失，部分也失去意义<br>
如一个学校中有很多学院，学院消失，学校也不存在。 使用黑色的箭头表示

- 共享聚集
整体和部分关系

![屏幕快照 2019-05-02 19.51.47.png](https://upload-images.jianshu.io/upload_images/4714178-4d09e39e0b9884d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


3. 继承
通过继承获得共享积累中所定义的数据/方法
抽象类标记``{abstract}``说明子类具有的行为

4. 依赖
一个模型元素 依赖于另外一个独立的元素

5. 细化
细化的一端指向未细化的一端，可以表示开发不同抽象 层次之间模型的相关性
![](https://upload-images.jianshu.io/upload_images/4714178-196a5c5cba8e8634.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 用例图
包含的模型元素：
系统 参与者 用例 用例之间的关系

### 系统
系统是一个黑箱，黑箱内部如何工作对于建立模型不重要。
### 用例
用例是一个类，用例的实例称为脚本，脚本是一次具体的使用过程
### 参与者
参与者清单<br>
参与者和他们对应的用例
### 场景
用例是所有可能场景的抽象 名字没有二义性并且带有下划线

## 用例之间的关系
- 扩展
向一个用例中添加一些动作构成另外一个用例，两个用例之间的关系就是扩展关系。用``<<entend>>`` 说明
比如售卖地铁列车时刻表是售票的一个“子动作”

![屏幕快照 2019-05-02 21.03.49.png](https://upload-images.jianshu.io/upload_images/4714178-87a7f503f6cd1b39.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 包含
一个用例包括了另外一个用例

## 类图
类之间的关联
![](https://upload-images.jianshu.io/upload_images/4714178-3dcbec41ed025e5c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



