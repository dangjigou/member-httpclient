���ĵ����ܶ�Ӧ��Http �� Dubbo ������нӿڲ���ʱ����Ҫ��Ĳ��Թ��̲���Ͳ������÷��������ο���

������ Dubbo
-----------------
Dubbo ��һ���ֲ�ʽ�����ܡ�
Dubbo �����ѯҳ�棺http://10.111.12.201:9010/dubbo/
�˺�/���룺root/root
���Բ�ѯĳ��Ӧ��?���� dubbo ����
<br>
![dubbo_service](https://github.com/wangyinguang/member-httpclient/blob/master/images/dubbo_service.png)<br>

������Http
-----------------

���ػ�������
-----------------
�����װ��JDK 1.8��IntelliJ IDEA 14��Gradle-3.1
IDEA ��װ�����غ��ѹ���ɣ�ע��һ�������ú� JDK �ٰ�װ IDEA.
Gradle ��װ�����غ��ѹ���ɣ� Gradle ��һ������������(���� Maven)��

Idea �½����Թ���
-----------------
1��	File->New Project
ѡ�� Gradle����ѡUse local gradle distribution�����úñ���gradle home ��ַ����� Next
![first](https://github.com/wangyinguang/member-httpclient/blob/master/images/first.jpg)<br>

���빤�̵�Project name����� Finish
File->New Module
ѡ�� Gradle�����Next
����Module name�����Finish
2��	������ɺ󣬸ù��̺�ģ���·������� build.gradle��
Ȼ����ģ���½����ļ��в㼶Ŀ¼��src/test/java,src/test/resources
![gradle](https://github.com/wangyinguang/member-httpclient/blob/master/images/gradle.png)<br>

���﹤�̵� build.gradle ����Ҫ���ù��̵������ֿ��ַ:��
http://mvn.test.51juban.cn/nexus/#welcome���˺����룺admin/admin123
ģ��� build.gradele ����Ҫ����ģ������Ҫ��������
���÷�ʽΪ groovy �﷨�����﹤�̵������ֿ��ֱַ�� copy �˿���������������ֿ��ַ��ģ��� build.gradle ��Ҫ���ò��������� �������Ͳ�����Ҫ����������Junit��testng��rest-assured�ȣ����������£�
![gradle2](https://github.com/wangyinguang/member-httpclient/blob/master/images/gradle2.png)<br>

 compile "poggyio:poggyio-organization-api:3.5.0-SNAPSHOT"
�������õľ���poggyioӦ�õ�poggyio-organization-api����
��ͬ��Ӧ�ÿ���������鿪��������ȥ�İ��汾��
http://mvn.test.51juban.cn/nexus/#nexus-search
![maven](https://github.com/wangyinguang/member-httpclient/blob/master/images/maven.png)<br>

Realease ���ȶ��汾�������Ŀ���Թ�������Ҫ������Ŀ�´�Ŀ� ����������Ҫ�������ļ������޸��������汾��
Testng���ܣ�testng�ǲ��Կ�ܣ�����junit��������ѡ��testngԭ�򣺱�@Test��ǵķ����������������������junit���ǲ��е�

ʵ��testng��ܣ�������Ҫ����һЩԼ����
-----------------
1��	��ĿҪ��Test��β
<br>
![test](https://github.com/wangyinguang/member-httpclient/blob/master/images/test.png)<br>

2��	Resources��testresĿ¼�Žӿڲ�����Ҫ��csv�ļ����������ļ���
<br>
![testres](https://github.com/wangyinguang/member-httpclient/blob/master/images/testres.png)<br>

3��	TestresĿ¼���ļ������Ʊ���������Testǰ������
<br>
![testres2](https://github.com/wangyinguang/member-httpclient/blob/master/images/testres2.png)<br>

4��	Csv�ļ������Է���������
<br>
![csv](https://github.com/wangyinguang/member-httpclient/blob/master/images/csv.jpg)<br>

5��	�������������csv�ļ�һһ��Ӧ
<br>
![method](https://github.com/wangyinguang/member-httpclient/blob/master/images/method.jpg)<br>


Http�ӿڲ��԰���
-----------------
1��	ʹ��httpclient���Լ�ʵ��http���úͶ���
<br>
![1](https://github.com/wangyinguang/member-httpclient/blob/master/images/1.jpg)<br>

2��	ʹ��rest-assured����ܷ�װhttp���úͶ���
<br>
![2](https://github.com/wangyinguang/member-httpclient/blob/master/images/2.png)<br>

dubbo�ӿڲ��԰���
-----------------
1��dubbo-config.xml������zookeeper��dubbo�ӿ�
<br>
![3](https://github.com/wangyinguang/member-httpclient/blob/master/images/3.jpg)<br>

ʾ����
<br>
![4](https://github.com/wangyinguang/member-httpclient/blob/master/images/4.png)<br>

2���½�dubbo�������߶���
<br>
![5](https://github.com/wangyinguang/member-httpclient/blob/master/images/5.jpg)<br>

3��	��д��������
<br>
![6](https://github.com/wangyinguang/member-httpclient/blob/master/images/6.jpg)<br>

�½���dubbo�����߶�����д��TestBase��Լ���д�������������Ǽ̳�TestBase
<br>
![7](https://github.com/wangyinguang/member-httpclient/blob/master/images/7.png)<br>
