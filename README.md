本文档介绍对应用Http 和 Dubbo 服务进行接口测试时，需要搭建的测试工程步骤和测试配置方法，供参考。

介绍下 Dubbo
-----------------
Dubbo 是一个分布式服务框架。
Dubbo 服务查询页面：http://10.111.12.201:9010/dubbo/
账号/密码：root/root
可以查询某个应用?供的 dubbo 服务。
<br>
![dubbo_service](https://github.com/wangyinguang/member-httpclient/blob/master/images/dubbo_service.png)<br>

介绍下Http
-----------------

本地环境配置
-----------------
软件安装：JDK 1.8、IntelliJ IDEA 14、Gradle-3.1
IDEA 安装：下载后解压即可，注意一定先配置好 JDK 再安装 IDEA.
Gradle 安装：下载后解压即可， Gradle 是一种依赖管理工具(类似 Maven)。

Idea 新建测试工程
-----------------
1、	File->New Project
选择 Gradle，勾选Use local gradle distribution，配置好本地gradle home 地址，点击 Next
![first](https://github.com/wangyinguang/member-httpclient/blob/master/images/first.jpg)<br>

输入工程的Project name，点击 Finish
File->New Module
选择 Gradle，点击Next
输入Module name，点击Finish
2、	创建完成后，该工程和模块下方会生成 build.gradle。
然后在模块下建立文件夹层级目录：src/test/java,src/test/resources
![gradle](https://github.com/wangyinguang/member-httpclient/blob/master/images/gradle.png)<br>

这里工程的 build.gradle 中需要设置工程的依赖仓库地址:。
http://mvn.test.51juban.cn/nexus/#welcome，账号密码：admin/admin123
模块的 build.gradele 中需要配置模块所需要的依赖。
配置方式为 groovy 语法。这里工程的依赖仓库地址直接 copy 了开发工程里的依赖仓库地址，模块的 build.gradle 需要配置测试依赖的 开发包和测试需要的依赖包（Junit、testng、rest-assured等），配置如下：
![gradle2](https://github.com/wangyinguang/member-httpclient/blob/master/images/gradle2.png)<br>

 compile "poggyio:poggyio-organization-api:3.5.0-SNAPSHOT"
这里引用的就是poggyio应用的poggyio-organization-api包，
不同的应用可以在这里查开发发布出去的包版本：
http://mvn.test.51juban.cn/nexus/#nexus-search
![maven](https://github.com/wangyinguang/member-httpclient/blob/master/images/maven.png)<br>

Realease 是稳定版本，如果项目测试过程中需要测试项目新打的开 发包，则需要在配置文件这里修改依赖包版本。
Testng介绍：testng是测试框架（类似junit），我们选择testng原因：被@Test标记的方法可以有输入参数，而在junit中是不行的

实现testng框架，代码需要做的一些约定：
-----------------
1、	类目要以Test结尾
<br>
![test](https://github.com/wangyinguang/member-httpclient/blob/master/images/test.png)<br>

2、	Resources中testres目录放接口测试需要的csv文件（参数化文件）
<br>
![testres](https://github.com/wangyinguang/member-httpclient/blob/master/images/testres.png)<br>

3、	Testres目录下文件夹名称必须以类名Test前面命名
<br>
![testres2](https://github.com/wangyinguang/member-httpclient/blob/master/images/testres2.png)<br>

4、	Csv文件必须以方法名命名
<br>
![csv](https://github.com/wangyinguang/member-httpclient/blob/master/images/csv.jpg)<br>

5、	方法参数必须和csv文件一一对应
<br>
![method](https://github.com/wangyinguang/member-httpclient/blob/master/images/method.jpg)<br>


Http接口测试案例
-----------------
1、	使用httpclient，自己实现http调用和断言
<br>
![1](https://github.com/wangyinguang/member-httpclient/blob/master/images/1.jpg)<br>

2、	使用rest-assured，框架封装http调用和断言
<br>
![2](https://github.com/wangyinguang/member-httpclient/blob/master/images/2.png)<br>

dubbo接口测试案例
-----------------
1、dubbo-config.xml中配置zookeeper和dubbo接口
<br>
![3](https://github.com/wangyinguang/member-httpclient/blob/master/images/3.jpg)<br>

示例：
<br>
![4](https://github.com/wangyinguang/member-httpclient/blob/master/images/4.png)<br>

2、新建dubbo的消费者对象
<br>
![5](https://github.com/wangyinguang/member-httpclient/blob/master/images/5.jpg)<br>

3、	编写测试用例
<br>
![6](https://github.com/wangyinguang/member-httpclient/blob/master/images/6.jpg)<br>

新建的dubbo消费者对象是写在TestBase里，自己编写测试用例别忘记继承TestBase
<br>
![7](https://github.com/wangyinguang/member-httpclient/blob/master/images/7.png)<br>
