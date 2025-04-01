#include<iostream>
#include <ctime>
#include <cstdlib>
#include<windows.h>
#include<fstream>
using namespace std;

class Print {
private:
	string str;
public:
	Print(string s = "") {
		str = s;
	};
	void slow(string s) {
		str = s;
		for (int i = 0; i < (int)str.length(); i++) {
			cout << str[i];
			Sleep(125);
		}
		Sleep(300);
		cout << endl;
	}
	void mid(string s) {
		str = s;
		for (int i = 0; i < (int)str.length(); i++) {
			cout << str[i];
			Sleep(50);
		}
		Sleep(300);
		cout << endl;
	}
	void fast(string s) {
		str = s;
		for (int i = 0; i < (int)str.length(); i++) {
			cout << str[i];
			Sleep(25);
		}
		Sleep(300);
		cout << endl;
	}
	void noendl(string s) {
		str = s;
		for (int i = 0; i < (int)str.length(); i++) {
			cout << str[i];
			Sleep(25);
		}
	}
};

class Date {
private:
	static int day;
public:
	Date() {};
	int getday() {
		return day;
	};
	void jiaday() {
		day++;
	};
	void load(int a) {
		day = a;
	};
};

int Date::day = 1;

class Person {
protected:
	const string name;
public:
	Person() {};
	Person(string x) :name(x) {};
};

class Liuming :public Person {
private:
	static int charm;
	static int power;
	static int courage;
public:
	Liuming(string a = "����") :Person(a) {};
	string getname()const;
	int getcharm()const;
	int getpower()const;
	int getcourage()const;
	void jiacharm(int x) {
		charm = charm + x;
	};
	void jiapower(int x) {
		power = power + x;
	};
	void jiacourage(int x) {
		courage = courage + x;
	}; void load(int, int, int);
};

int Liuming::charm = 0;
int Liuming::power = 0;
int Liuming::courage = 0;

string Liuming::getname()const {
	return name;
}

int Liuming::getcharm()const {
	return charm;
}

int Liuming::getpower()const {
	return power;
}

int Liuming::getcourage()const {
	return courage;
}

void Liuming::load(int a, int b, int c) {
	charm = a;
	power = b;
	courage = c;
}

class Cheche :public Person {
private:
	static int unknown;
	static int favorability;
public:
	Cheche(string a = "����") :Person(a) {};
	string getname()const {
		return name;
	};
	int getunknown()const {
		return unknown;
	};
	int getfavorability()const {
		return favorability;
	};
	void jiaknown(int x) {
		unknown = unknown + x;
	};
	void jiafavorability(int x) {
		favorability=favorability+x;
	};
	void load(int a, int b) {
		unknown = a;
		favorability = b;
	};
};

int Cheche::unknown = 0;
int Cheche::favorability = 0;

class Event {
public:
	void event1(Liuming &,Cheche &,Date &);
	void badend();
};

class Choiceprint {
public:
	int event1();
	int event2();
};

int Choiceprint::event1() {
	int x;
	printf("1����������������һ���к���\n");
	printf("2���ŵ����ĺ��棬������Ҫ��ʲô�ˡ�\n");
	printf("3��ʲô��������\n");
	cin >> x;
	system("cls");
	return x;
}

void Event::event1(Liuming &you,Cheche& che,Date& day) {
	Print p; int a; Choiceprint t; srand((unsigned)time(NULL));
	p.mid("���죬�����������һ�ڿ�֮����������һ��ȥ���˷���׼�����緹��");
	p.mid("��������һ�������Ͽε��˺ܶ࣬����Ҳ�������˲��̵Ķ��顣");
	p.mid("����Ҳ������һ����һ���Ŷ�һ���������ֻ���");
	p.mid("ͻȻ����������һ������Ϥ�ò�������Ϥ��������");
	p.mid("û����϶��ǳ�����������");
	p.mid("��ʱ�˿̣�����������ĺ���һ����");
	p.mid("����������Ҫ��ʲô�أ�");
	switch (t.event1()) {
	case 1: {
		if (you.getcourage() <= 15) {
			p.mid("���͵ػع�ͷȥ��ȴ���ֳ�����û�п����㣬�����ڸ������������졣");
			p.mid("�㱾���ſ�����ȴ����ͻ�������û�п���");
			p.mid("����㻹�ǻع�ͷȥ���ͳ��ֻ��ȴ��Ŵ��ˡ���");
			p.mid("��������Ϊ�����εľٶ���ڲ��ѡ�");
			p.noendl("�������ֵ����");
			a = rand() % 20;
			cout << a;
			p.mid("�㡣");
			you.jiacourage(-a);
		}
		else if (you.getcourage() > 15) {
			p.mid("��˳����Ȼ�ػع�ͷȥ���������복������������ˡ��������˵��һ����Hello����");
			p.mid("�������Ǳ�������һ���£�������Ҳ���Żظ���һ�䡰Hello��");
			p.mid("��ع�ͷȥ��Ȼ��Ȼ�ؼ������Ŷӡ�");
			p.mid("�ص������������ƽ�����ֵ����顪��������������˵Ķ�����");
			p.noendl("�������ֵ������");
			a = rand() % 20;
			cout << a;
			p.mid("��");
			you.jiacourage(a);
			p.noendl("��������ĺøм���");
			a = rand() % 10;
			cout << a;
			che.jiafavorability(a);
			p.mid("�㡣");
		}
		break;
	}
	case 2: {
		p.mid("���뿪��������飬���ŵ����ԱߵĶ��顣");
		p.mid("���������������ؿ�����������ʲô�͡�");
		p.noendl("��Գ������˽�ȶ���");
		a = rand() % 25;
		cout << a;
		p.mid("�㡣");
		che.jiaknown(a);
		p.mid("������������������Ļ��ӡ�");
		p.noendl("��������ĺøжȼ�����");
		a = rand() % 10;
		cout << a;
		p.mid("�㡣");
		che.jiafavorability(-a);
		break;
	}
	case 3: {
		p.mid("��ͬѡ��˵����������ʲô��û��������");
		break;
	}
	}
	p.mid("����һ���������ȥ�ˡ�������");
	day.jiaday();
	system("pause");
	system("cls");
}

void Event::badend() {
	Print p;
	p.mid("�����µ�һ�죬��ԭ����Ҫȥ����֮�Ŀ�һ���ֲ���Ӱ��������ĵ�����");
	p.mid("����ͻȻ����һ����Ϥ����ף�����һ�Ų���Ϥ����ס�");
	p.slow("���ǳ���������һ������ȫ����ʶ�����ˡ�");
	p.mid("ֻ���Ǹ���������ؿ��ų��������ﺬ����˵��ʲô��");
	p.mid("���ȿ��������ı����Ǿ��ȵģ�Ȼ���ƺ���Щ����Ը������ǵ���ͷ��");
	p.mid("Ȼ�������ó���һ���������̫Ư�������������ִ����˳����Ĳ����ϡ�");
	p.mid("��������ǵ�һ��֪������̸������Ϣ���ˡ�");
	p.mid("���Ǹ�������������ֵ����ߣ���ԶԶ�����㡭��");
	system("pause");
	system("cls");
	p.mid("�Ÿոչ���һ���£������˵�˳������ֵ���Ϣ��");
	p.mid("�����ž��������ĵ�����Ȧ��");
	p.mid("����Ҳ����׷�󳵳��ˡ�");
	p.slow("׷�󳵳�����սʧ�ܡ�");
	p.slow("��Ϸ������������");
	exit(0);
}

void kaitou() {
	Print p;
	p.fast("�Ƿ�Ҫ������ͷ��1������0����");
	int x;
	cin >> x;
	if (x == 1) {
		system("cls");
		p.mid("�������������һ������ͨͨ��211��ѧ��");
		p.mid("�����������ڣ�Ҳ����ȥ����Լ���");
		p.mid("�ڸ���ʱ�ڣ��Ǹ�����������ʱ�����������Լ����Ը����˺ܶ�Ů�����ѡ�");
		p.mid("�����ڵ��˴�ѧ���������òȡ�˵�ʱ������ͻȻ������߲�����Ů���ˡ�");
		p.mid("���������źܶ�����ѣ�Ҳ��ȱ��飬�һ�Ҫʲô�ء�");
		p.mid("����ô���ţ���Ϊ�ɴ൥����ȥ������Ҳ���");
		p.mid("ֱ������������������");
		p.mid("�Ǹ�Ů���ĳ��������ס�ˡ�");
		p.mid("�������ͬһ��ѧ����֯��ͬһ��������������ϻ��б�����ɺϵ�������");
		p.mid("���������׵����ѡ������ܿ�ʼ������ʱ���������е���̫�����ˡ�");
		p.mid("������ѽ���Ҹ���˵����������ͻȻͣ�����죬�����㣬���΢Ц��������˵����");
		p.mid("���ֵܣ��㲻�����Ķ��˰ɣ���");
		p.mid("��Ϊ��Ӧ����ֻ��΢΢���˵�ͷ��");
		p.mid("��Ҫ������������������ţ������ޱȼᶨ��");
		p.mid("���������֣�����������");
		p.mid("���Ҹ���˵�������ϲ��һ���ˣ��Ǳ���Ҫ��30��֮��������������ĺ���������˵����������Զ��֪��30��֮��ᷢ��ʲô����");
		system("pause");
		system("cls");
		p.mid("���ڿ�ʼ����Ҫ˼����ôȥ���³����ˡ�");
	}
	else {
		p.mid("��Ϸ������ʼ����");
		system("cls");
	}
}

void dateleft(Liuming& you,Cheche &che,Date &day) {
	Print p;
	Event e;
	p.noendl("�����ǵ�");
	cout << day.getday();
	p.noendl("��");
	cout << endl;
	switch (day.getday()) {
	case 5: {
		e.event1(you,che,day);
		break;
	}
	case 12: {

		break;
	}
	case 15: {
		break;
	}
	case 20: {
		break;
	}
	case 26: {
		break;
	}
	case 29: {
		break;
	}
	}
	if (day.getday() < 10)
		p.fast("���ڵ�ʱ�仹�ܳ�ԣ���㻹�кܶ��ʱ��ȥ׷�󳵳���");
	else if (day.getday() >= 10 && day.getday() < 20)
		p.fast("�������Ա߿�ʼ���������ˣ��㲻ȷ�������ǲ��Ǹ�����һ�����뷨����Ҫ����һ���Լ���ʱ���ˡ�");
	else if (day.getday() >= 20 && day.getday() < 25)
		p.fast("һЩҥ�Դ��˳������м������˸������ߵĹ��ڿ����ˡ��Ѿ���ȷ���������������ˣ���ø�������֮ǰ�ˡ�");
	else if (day.getday() >= 25 && day.getday() < 30)
		p.fast("�����Ѿ���ʼ׼������ˣ����˷�ʱ����ȥ�����Ҫʧȥ�����ˣ�����");
	if (day.getday() == 30) {
		p.fast("ʱ�䲻���ˡ���");
		system("cls");
		e.badend();
	}
	p.fast("��ô���죬����Ҫ��Щʲô�أ�");
}

int choiceprint(Liuming you,Cheche che,Date d) {
	Print p;
	int x;
	printf("1���鿴�Լ�����Ϣ��\n");
	printf("2���鿴��������Ϣ��\n");
	printf("3��ȥ��Ϧ�����������Լ��������������ж�������һ���ʱ�䣩\n");
	printf("4���������ܶ������塣�����ж�������һ���ʱ�䣩\n");
	printf("5����Щ���������������������ж�������һ���ʱ�䣩\n");
	printf("6�����ٳ���ȥ����������ʲô�������ж�������һ���ʱ�䣩\n");
	printf("7����������ѡ��������������ж�������һ���ʱ�䣩\n");
	printf("8��������ȡ�\n");
	printf("9����ȡ���ȡ�\n");
	printf("10���˳���Ϸ��\n");
	if (che.getfavorability() >= 25)
		printf("11���㳵����ԭ�񡣣����ж�������һ���ʱ�䣩\n");
	if (che.getfavorability() >= 50)
		printf("12�������ж�������һ���ʱ�䣩\n");
	if (che.getfavorability() >= 75)
		printf("13������Լ������ȥ�档�����ж�������һ���ʱ�䣩\n");
	cin >> x;
	return x;
}

void function1(Liuming x) {
	Print p;
	p.fast("���ߵ�������ǰ���������Լ�����");
	p.fast("������֣�������֪���ģ�");
	p.noendl(x.getname());
	cout << endl;
	p.fast("����ϸ������һ���Լ���������");
	p.noendl("�������ֵΪ��");
	cout << x.getcharm() << endl;
	if (x.getcharm() <= 25)
		p.fast("��������Ǹ���ǰһ��������");
	else if (x.getcharm() > 25 && x.getcharm() <= 50)
		p.fast("��о�������ƺ�����Щ��仯��");
	else if (x.getcharm() > 50 && x.getcharm() <= 75)
		p.fast("���Խ��Խ����˵�㳤��˧��");
	else if (x.getcharm() > 75 && x.getcharm() < 100)
		p.fast("���ˣ�����������м���Ů�����ϻ�ͷ�������ˡ�");
	else if (x.getcharm() >= 100)
		p.fast("�����ֵ�Ѿ������������ˣ���Ҳû��Ҫ��������ֵ�ˡ�");

	p.fast("���������������ᣬ��ļ���Ҳ�ɴ���¶��������");
	p.noendl("�������ֵΪ��");
	cout << x.getpower() << endl;
	if (x.getpower() <= 25)
		p.fast("������ϳ��˹�ͷ���Ƿ����ˡ�");
	else if (x.getpower()> 25 && x.getpower() <= 50)
		p.fast("��˫���ϵļ������΢΢������һЩ��");
	else if (x.getpower() > 50 && x.getpower() <= 75)
		p.fast("��о��Լ�����ǰ�������һЩ��");
	else if (x.getpower() > 75 && x.getpower() < 100)
		p.fast("·����Щ����С�￪ʼ���������ˡ�");
	else if (x.getpower() >= 100)
		p.fast("���Ŀ�겻�Ǿ��ر������Ѿ�û�б�Ҫ�ٶ����ˣ�");
	p.fast("�����յ��˾��ӵĸ�ǰ��ֱ�������Լ���˫�ۡ���");
	p.noendl("�������ֵΪ��");
	cout << x.getcourage() << endl;
	if (x.getcourage() <= 25)
		p.fast("ˮ��ͷͻȻ������һ��ˮ�����������һ����");
	else if (x.getcourage()  > 25 && x.getcourage() <= 50)
		p.fast("��о������������Щ��һ���ˡ�");
	else if (x.getcourage() > 50 && x.getcourage() <= 75)
		p.fast("���������������ڿֲ���Ϸ�������Ŵ�ҵľ�����");
	else if (x.getcourage() > 75 && x.getcourage() < 100)
		p.fast("����񿴵��㱳������ʲô�����������㲢�����ú��¡�");
	else if (x.getcourage() >= 100)
		p.fast("���Ѿ�����η���ˣ�û�б�Ҫ�ٶ��������ˡ�");
	system("pause");
	system("cls");
}

void function2(Cheche x) {
	Print p;
	p.fast("���������֣��������μ����ĵģ�");
	cout << x.getname() << endl;
	p.fast("������ֻ�������������ᣬ��Ѱ���복���ĺۼ�����");
	p.noendl("��Գ������˽��Ϊ��");
	cout << x.getunknown() << endl;
	if (x.getunknown() <= 25)
		p.fast("��Գ�����ʲô�����˽��ء�");
	else if (x.getunknown() > 25 && x.getunknown() <= 50)
		p.fast("����������֪���ˡ���������ԭ��");
	else if (x.getunknown() > 50 && x.getunknown() <= 75)
		p.fast("�������ܾ�ȷ���Ʋ������һ��Ĵ�硣");
	else if (x.getunknown() > 75 && x.getunknown() < 100)
		p.fast("���������Ĺ��۶�û�����ǰ��˽⳵����");
	else if (x.getunknown() >= 100)
		p.fast("���Ѿ����˽⳵���ˣ�������ʱ��ȥ���������յ���Ϣ�ˣ�");
	p.fast("�㷢΢�Ÿ��˳����Ĺ��ۣ�ѯ�������������ӡ����Ρ���");
	p.noendl("��������ĺøж�Ϊ��");
	cout << x.getfavorability() << endl;
	if (x.getfavorability() <= 25)
		p.fast("˵��ʵ�������������ܲ��ܽг�������ֶ��Ǹ����⡣");
	else if (x.getfavorability() > 25 && x.getfavorability() <= 50)
		p.fast("���ڳ���ÿ�������㶼����к��ˡ�");
	else if (x.getfavorability() > 50 && x.getfavorability() <= 75)
		p.fast("�������������������ᵽ�㡣");
	else if (x.getfavorability() > 75 && x.getfavorability() < 100)
		p.fast("��������Ĺ�ϵ�Ѿ������ޱ��ˡ�");
	else if (x.getfavorability() >= 100)
		p.fast("��������߱���ֻ��Ҫ�ȴ�һ��ʱ���ˡ�");
	system("pause");
	system("cls");
}

void function3(Liuming& x,Date &day) {
	Print p;
	srand((unsigned)time(NULL));
	int a = rand()%20;
	int t = rand() % 3;
	if (t == 0)
		p.mid("Ϧ������㽲�����������������ŵ�һЩ���龭����");
	if (t == 1)
		p.mid("Ϧ������ĳ��APP��Ϊ���Һ��˴����ı���һ���Լ��Ĵ��");
	if (t == 2)
		p.mid("Ϧ������ĳ������Ϊ�㾫����ѡ��һ��ͣ�����ȥ�������˸����͡�");
	if (t == 3)
		p.mid("Ϧ�������ı�����������ָ������ı����ø�����Ȼ�ˡ�");
	p.noendl("��������");
	cout << a;
	p.noendl("������ֵ��");
	x.jiacharm(a);
	cout << endl;
	day.jiaday();
	p.mid("һ���������ȥ�ˡ���");
	system("pause");
	system("cls");
}

void function4(Liuming& x, Date& day) {
	Print p;
	srand((unsigned)time(NULL));
	int a = rand() % 20;
	int t = rand() % 3;
		p.mid("���ܴ����������ٳ�������");
		if (t == 0)
			p.mid("�����˺ܶ�����Գţ�����ؼ������ˡ�");
		if (t == 1)
			p.mid("�����˺ܶ���������ϣ���ı������������ˡ�");
		if (t == 2)
			p.mid("�����˺ܶ��������������ĸ��������ˡ�");
		if (t == 3)
			p.mid("�����Ųٳ����˺ü�Ȧ����ķ�������ˡ�");
	p.noendl("��������");
	cout << a;
	p.noendl("������ֵ��");
	x.jiapower(a);
	cout << endl;
	day.jiaday();
	p.mid("һ���������ȥ�ˡ���");
	system("pause");
	system("cls");
}

void function5(Liuming& x, Date& day) {
	Print p;
	srand((unsigned)time(0));
	int a = rand() % 20;
	int t = rand() % 3;
	if (t == 0)
		p.mid("����ѡ�˲��ۿ���һ���ֲ���Ӱ��");
	if (t == 1)
		p.mid("������ܵ�������һ��ֲ���Ϸ��");
	if (t == 2)
		p.mid("����·�ϳ��Ը�·��������");
	if (t == 3)
		p.mid("�����賿��ʱ����Թ���У԰��");
	p.noendl("��������");
	cout << a;
	p.noendl("������ֵ��");
	cout << endl;
	x.jiacourage(a);
	day.jiaday();
	p.mid("һ���������ȥ�ˡ���");
	system("pause");
	system("cls");
}

void function6(Cheche& x, Date& day) {
	Print p;
	int a, t; srand((unsigned)time(NULL));
	a = rand() % 20;
	t = rand() % 3;
	p.fast("����׼�복���ճ��غϵ�ʱ�䣬��ʼ������ж���");
	if (t == 0)
		p.mid("���ϸ������ϣ�����ϸ����������������Ϊ��");
	if (t == 1)
		p.mid("�ڷ�����㿴����������ԵĶ����Լ����ۡ�");
	if (t == 2)
		p.mid("��·�ϣ��㿴�������ڸ����������죬��չ�ȥ���˸�����");
	if (t == 3)
		p.mid("����΢�����복���򵥵�����һ���졣");
	p.noendl("��Գ�������");
	cout << a;
	p.noendl("����˽�ȡ�");
	cout << endl;
	x.jiaknown(a);
	day.jiaday();
	p.mid("һ���������ȥ�ˡ���");
	system("pause");
	system("cls");
}

void function7(Cheche& x, Date& day) {
	Print p;
	int a, b,t; srand((unsigned)time(NULL));
	t = rand() % 3;
	p.fast("������ֻ���˼����Ҫ��Щʲô�������á�");
	if (t == 0)
		p.mid("��㿪���������������������һ���̲衣");
	if (t == 1)
		p.mid("��ֱ��װ������648��������ԭ��ʮ���顣");
	if (t == 2)
		p.mid("�㽫����������������Ƭ��ӡ�������͸�������");
	if (t == 3)
		p.mid("������һ�����͸�������");
	b = 100 - x.getunknown();
	if (b >=75) {
		p.fast("������ͻ����������������һ�����������ڻ�����ʶ���ء��������о�����ð��");
		a = 0 - (rand() % 20);
	}
	else if (b < 75 && b >= 50) {
		p.fast("�����������˼�����͵Ķ�����̫�����������⡣��������ǿ��΢Ц�����ˡ�");
		a = rand() % 10-20;
	}
	else if (b < 50 && b >= 25) {
		p.fast("�����������������ģ���΢Ц�������л�ˡ�");
		a = rand() % 30;
	}
	else if (b < 25) {
		p.fast("������ͻ��������������һ������û��ʲô������ӷ������������ˣ�");
		a = rand() % 50;
	}
	p.noendl("�����������");
	cout << a;
	p.noendl("��øжȡ�");
	cout << endl;
	x.jiafavorability(a);
	day.jiaday();
	p.mid("һ���������ȥ�ˡ���");
	system("pause");
	system("cls");
}

void function8(Date day,Liuming you,Cheche che) {
	fstream p;
	p.open("save.txt", ios::out | ios::trunc);
	if (p.is_open()) {
		p << day.getday() << ' ' << you.getcharm() << ' ' << you.getpower() << ' ' << you.getcourage() << ' ' << che.getunknown() << ' ' << che.getfavorability();
		printf("����ɹ���");
	}
	else
		printf("����ʧ�ܣ�");
	system("pause");
	system("cls");
	p.close();
}

void function9(Date day, Liuming you, Cheche che) {
	fstream p; int a,b,c;
	p.open("save.txt", ios::in);
	if (p.is_open()) {
		p >> a;
		day.load(a);
		p >> a>>b>>c;
		you.load(a, b, c);
		p >> a >> b;
		che.load(a, b);
		printf("��ȡ�ɹ���");
	}
	else
		printf("��ȡʧ�ܣ�");
	system("pause");
	system("cls");
	p.close();
}

void function10() {
	Print p;
	p.mid("ȷ��Ҫ�˳��𣿣�1ȷ����0���أ�");
	int x;
	cin >> x;
	if (x == 1) {
		p.fast("��л������棬�ټ���");
		exit(0);
	}
}

void function11(Liuming &you, Cheche& che, Date& d) {

	d.jiaday();
}

void menu() {
	Print p;
	Date d;
	Liuming you;
	Cheche che;
	while (1) {
		dateleft(you,che,d);
		switch (choiceprint(you,che,d)) {
		case 1: {
			system("cls");
			function1(you);
			break;
		}
		case 2: {
			system("cls");
			function2(che);
			break;
		}
		case 3: {
			system("cls");
			function3(you, d);
			break;
		}
		case 4: {
			system("cls");
			function4(you, d);
			break;
		}
		case 5: {
			system("cls");
			function5(you, d);
			break;
		}
		case 6: {
			system("cls");
			function6(che, d);
			break;
		}
		case 7: {
			system("cls");
			function7(che, d);
			break;
		}
		case 8: {
			system("cls");
			function8(d,you,che);
			break;
		}
		case 9: {
			system("cls");
			function9(d,you,che);
			break;
		}
		case 10: {
			system("cls");
			function10();
			break;
		}
		case 11: {
			system("cls");
			function11(you,che,d);
			break;
		}
		}
	}
}

int main() {
	kaitou();
	menu();
	return 0;
}