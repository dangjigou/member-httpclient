���ĵ����ܶ�Ӧ��Http �� Dubbo ������нӿڲ���ʱ����Ҫ��Ĳ��Թ��̲���Ͳ������÷��������ο���

������ Dubbo
-----------------
Dubbo ��һ���ֲ�ʽ�����ܡ�
Dubbo �����ѯҳ�棺http://10.111.12.201:9010/dubbo/
�˺�/���룺root/root
���Բ�ѯĳ��Ӧ��?���� dubbo ����


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

���빤�̵�Project name����� Finish
File->New Module
ѡ�� Gradle�����Next
����Module name�����Finish
2��	������ɺ󣬸ù��̺�ģ���·������� build.gradle��
Ȼ����ģ���½����ļ��в㼶Ŀ¼��src/test/java,src/test/resources

���﹤�̵� build.gradle ����Ҫ���ù��̵������ֿ��ַ:��
http://mvn.test.51juban.cn/nexus/#welcome���˺����룺admin/admin123
ģ��� build.gradele ����Ҫ����ģ������Ҫ��������
���÷�ʽΪ groovy �﷨�����﹤�̵������ֿ��ֱַ�� copy �˿���������������ֿ��ַ��ģ��� build.gradle ��Ҫ���ò��������� �������Ͳ�����Ҫ����������Junit��testng��rest-assured�ȣ����������£�
 compile "poggyio:poggyio-organization-api:3.5.0-SNAPSHOT"
�������õľ���poggyioӦ�õ�poggyio-organization-api����
��ͬ��Ӧ�ÿ���������鿪��������ȥ�İ��汾��
http://mvn.test.51juban.cn/nexus/#nexus-search

Realease ���ȶ��汾�������Ŀ���Թ�������Ҫ������Ŀ�´�Ŀ� ����������Ҫ�������ļ������޸��������汾��
Testng���ܣ�testng�ǲ��Կ�ܣ�����junit��������ѡ��testngԭ�򣺱�@Test��ǵķ����������������������junit���ǲ��е�
-----------------
ʵ��testng��ܣ�������Ҫ����һЩԼ����
1��	��ĿҪ��Test��β

2��	Resources��testresĿ¼�Žӿڲ�����Ҫ��csv�ļ����������ļ���

3��	TestresĿ¼���ļ������Ʊ���������Testǰ������

4��	Csv�ļ������Է���������

5��	�������������csv�ļ�һһ��Ӧ


Http�ӿڲ��԰���
-----------------
1��	ʹ��httpclient���Լ�ʵ��http���úͶ���

2��	ʹ��rest-assured����ܷ�װhttp���úͶ���

dubbo�ӿڲ��԰���
-----------------
1��dubbo-config.xml������zookeeper��dubbo�ӿ�

ʾ����

2���½�dubbo�������߶���

3��	��д��������

�½���dubbo�����߶�����д��TestBase��Լ���д�������������Ǽ̳�TestBase
