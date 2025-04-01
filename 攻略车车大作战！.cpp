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
	Liuming(string a = "刘明") :Person(a) {};
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
	Cheche(string a = "车车") :Person(a) {};
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
	printf("1、鼓起勇气跟她打一声招呼。\n");
	printf("2、排到她的后面，看看她要点什么菜。\n");
	printf("3、什么都不做。\n");
	cin >> x;
	system("cls");
	return x;
}

void Event::event1(Liuming &you,Cheche& che,Date& day) {
	Print p; int a; Choiceprint t; srand((unsigned)time(NULL));
	p.mid("今天，上完上午最后一节课之后，你便跟往常一样去往了饭堂准备吃午饭。");
	p.mid("由于是周一，上午上课的人很多，饭堂也就排起了不短的队伍。");
	p.mid("而你也和往常一样，一边排队一边玩起了手机。");
	p.mid("突然，你听见了一个你熟悉得不能再熟悉的声音。");
	p.mid("没错，这肯定是车车的声音。");
	p.mid("此时此刻，她就排在你的后面一个。");
	p.mid("那你现在想要做什么呢？");
	switch (t.event1()) {
	case 1: {
		if (you.getcourage() <= 15) {
			p.mid("你猛地回过头去，却发现车车并没有看着你，而是在跟身后的人聊着天。");
			p.mid("你本想张开的嘴却因这突发情况而没有开。");
			p.mid("最后你还是回过头去，掏出手机等待着打饭了……");
			p.mid("后来，你为这尴尬的举动后悔不已。");
			p.noendl("你的勇气值减少");
			a = rand() % 20;
			cout << a;
			p.mid("点。");
			you.jiacourage(-a);
		}
		else if (you.getcourage() > 15) {
			p.mid("你顺其自然地回过头去，发现你与车车的眼神对上了。你紧接着说了一声“Hello”。");
			p.mid("车车先是被你吓了一下下，紧接着也跟着回复了一句“Hello”");
			p.mid("你回过头去自然而然地继续排着队。");
			p.mid("回到宿舍后，你难以平复欢乐的心情——她的声音是如此的动听。");
			p.noendl("你的勇气值增加了");
			a = rand() % 20;
			cout << a;
			p.mid("点");
			you.jiacourage(a);
			p.noendl("车车对你的好感加了");
			a = rand() % 10;
			cout << a;
			che.jiafavorability(a);
			p.mid("点。");
		}
		break;
	}
	case 2: {
		p.mid("你离开了这个队伍，并排到了旁边的队伍。");
		p.mid("在这里，你可以清晰地看到车车点了什么餐。");
		p.noendl("你对车车的了解度多了");
		a = rand() % 25;
		cout << a;
		p.mid("点。");
		che.jiaknown(a);
		p.mid("但车车好像察觉到了你的换队。");
		p.noendl("车车对你的好感度减少了");
		a = rand() % 10;
		cout << a;
		p.mid("点。");
		che.jiafavorability(-a);
		break;
	}
	case 3: {
		p.mid("如同选项说的那样，你什么都没有做……");
		break;
	}
	}
	p.mid("今天一天就这样过去了…………");
	day.jiaday();
	system("pause");
	system("cls");
}

void Event::badend() {
	Print p;
	p.mid("又是新的一天，你原本想要去到林之心看一部恐怖电影来增长你的胆量。");
	p.mid("但你突然发现一张熟悉的面孔，和另一张不熟悉的面孔。");
	p.slow("那是车车，和另一个你完全不认识的男人。");
	p.mid("只见那个男人深情地看着车车，嘴里含糊地说着什么。");
	p.mid("你先看到车车的表情是惊讶的，然后似乎有些不情愿，最后还是点了头。");
	p.mid("然后男人拿出了一个不算得上太漂亮的项链，亲手戴在了车车的脖颈上。");
	p.mid("结果，你是第一个知道车车谈恋爱消息的人。");
	p.mid("而那个男生，不论颜值和身高，都远远不如你……");
	system("pause");
	system("cls");
	p.mid("才刚刚过了一个月，你就听说了车车分手的信息。");
	p.mid("紧接着就是她封心的朋友圈。");
	p.mid("你再也不能追求车车了。");
	p.slow("追求车车大作战失败。");
	p.slow("游戏结束…………");
	exit(0);
}

void kaitou() {
	Print p;
	p.fast("是否要跳过开头（1不跳，0跳）");
	int x;
	cin >> x;
	if (x == 1) {
		system("cls");
		p.mid("你叫刘明，来自一所普普通通的211大学。");
		p.mid("你的外表并不出众，也很少去打扮自己。");
		p.mid("在高中时期，那个不看重外表的时代，你依靠自己的性格有了很多女性朋友。");
		p.mid("可现在到了大学，在这个以貌取人的时代，你突然发现身边不再有女生了。");
		p.mid("“不过有着很多好朋友，也不缺玩伴，我还要什么呢”");
		p.mid("你这么想着，认为干脆单身下去就这样也不差。");
		p.mid("直到……………………");
		p.mid("那个女孩的出现令你呆住了。");
		p.mid("你跟她在同一个学生组织的同一个部门里，这世界上还有比这更巧合的事情吗？");
		p.mid("而当你最亲的朋友——滕杰开始评价她时，你的心里感到不太好受了。");
		p.mid("“所以呀，我跟你说……”滕杰突然停下了嘴，看着你，最后微笑了起来，说道：");
		p.mid("“兄弟，你不会真心动了吧？”");
		p.mid("作为回应，你只是微微点了点头。");
		p.mid("我要拿下她，你的心里想着，眼神无比坚定。");
		p.mid("而她的名字，叫做车车。");
		p.mid("“我跟你说，如果你喜欢一个人，那必须要在30天之内拿下她。”你的好朋友滕杰说道：“你永远不知道30天之后会发生什么。”");
		system("pause");
		system("cls");
		p.mid("现在开始，你要思考怎么去拿下车车了。");
	}
	else {
		p.mid("游戏即将开始……");
		system("cls");
	}
}

void dateleft(Liuming& you,Cheche &che,Date &day) {
	Print p;
	Event e;
	p.noendl("今天是第");
	cout << day.getday();
	p.noendl("天");
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
		p.fast("现在的时间还很充裕，你还有很多的时间去追求车车。");
	else if (day.getday() >= 10 && day.getday() < 20)
		p.fast("车车的旁边开始出现男人了，你不确定他们是不是跟你有一样的想法，你要审视一下自己的时间了。");
	else if (day.getday() >= 20 && day.getday() < 25)
		p.fast("一些谣言传了出来，有几个男人跟车车走的过于靠近了。已经很确定他们是你的情敌了，你得赶在他们之前了。");
	else if (day.getday() >= 25 && day.getday() < 30)
		p.fast("他们已经开始准备表白了，再浪费时间下去，你就要失去车车了！！！");
	if (day.getday() == 30) {
		p.fast("时间不够了……");
		system("cls");
		e.badend();
	}
	p.fast("那么今天，你想要做些什么呢？");
}

int choiceprint(Liuming you,Cheche che,Date d) {
	Print p;
	int x;
	printf("1、查看自己的信息。\n");
	printf("2、查看车车的信息。\n");
	printf("3、去找夕阳哥以提升自己的魅力。（此行动会消耗一天的时间）\n");
	printf("4、跟着滕杰锻炼身体。（此行动会消耗一天的时间）\n");
	printf("5、做些事情来锻炼胆量。（此行动会消耗一天的时间）\n");
	printf("6、跟踪车车去看看她在做什么。（此行动会消耗一天的时间）\n");
	printf("7、给车车挑选并赠送礼物。（此行动会消耗一天的时间）\n");
	printf("8、保存进度。\n");
	printf("9、读取进度。\n");
	printf("10、退出游戏。\n");
	if (che.getfavorability() >= 25)
		printf("11、陪车车玩原神。（此行动会消耗一天的时间）\n");
	if (che.getfavorability() >= 50)
		printf("12、（此行动会消耗一天的时间）\n");
	if (che.getfavorability() >= 75)
		printf("13、单独约车车出去玩。（此行动会消耗一天的时间）\n");
	cin >> x;
	return x;
}

void function1(Liuming x) {
	Print p;
	p.fast("你走到镜子面前，审视了自己……");
	p.fast("你的名字，这你是知道的：");
	p.noendl(x.getname());
	cout << endl;
	p.fast("你仔细端详了一下自己的脸……");
	p.noendl("你的魅力值为：");
	cout << x.getcharm() << endl;
	if (x.getcharm() <= 25)
		p.fast("你的脸还是跟以前一样笨蛋。");
	else if (x.getcharm() > 25 && x.getcharm() <= 50)
		p.fast("你感觉你的脸似乎有了些许变化。");
	else if (x.getcharm() > 50 && x.getcharm() <= 75)
		p.fast("最近越来越多人说你长得帅了");
	else if (x.getcharm() > 75 && x.getcharm() < 100)
		p.fast("对了，昨天好像又有几个女生不断回头来看你了。");
	else if (x.getcharm() >= 100)
		p.fast("你的颜值已经赛过彭于晏了！再也没必要提升魅力值了。");

	p.fast("你把你的袖子往上提，你的肌肉也由此显露出来……");
	p.noendl("你的力量值为：");
	cout << x.getpower() << endl;
	if (x.getpower() <= 25)
		p.fast("你的身上除了骨头就是肥肉了。");
	else if (x.getpower()> 25 && x.getpower() <= 50)
		p.fast("你双臂上的肌肉好像微微拱起了一些。");
	else if (x.getpower() > 50 && x.getpower() <= 75)
		p.fast("你感觉自己比以前好像高了一些。");
	else if (x.getpower() > 75 && x.getpower() < 100)
		p.fast("路上那些精神小伙开始避着你走了。");
	else if (x.getpower() >= 100)
		p.fast("你的目标不是举重比赛，已经没有必要再锻炼了！");
	p.fast("最后，你凑到了镜子的跟前，直视着你自己的双眼……");
	p.noendl("你的勇气值为：");
	cout << x.getcourage() << endl;
	if (x.getcourage() <= 25)
		p.fast("水龙头突然滴下来一滴水，这把你吓了一跳。");
	else if (x.getcourage()  > 25 && x.getcourage() <= 50)
		p.fast("你感觉你的眼神变得有些不一样了。");
	else if (x.getcourage() > 50 && x.getcourage() <= 75)
		p.fast("你想起来昨天你在恐怖游戏里引领着大家的经历。");
	else if (x.getcourage() > 75 && x.getcourage() < 100)
		p.fast("你好像看到你背后有了什么东西，但是你并不觉得害怕。");
	else if (x.getcourage() >= 100)
		p.fast("你已经无所畏惧了！没有必要再锻炼胆量了。");
	system("pause");
	system("cls");
}

void function2(Cheche x) {
	Print p;
	p.fast("车车的名字，这你是牢记于心的：");
	cout << x.getname() << endl;
	p.fast("你打开了手机，翻阅起了相册，找寻你与车车的痕迹……");
	p.noendl("你对车车的了解度为：");
	cout << x.getunknown() << endl;
	if (x.getunknown() <= 25)
		p.fast("你对车车都什么还不了解呢。");
	else if (x.getunknown() > 25 && x.getunknown() <= 50)
		p.fast("现在你至少知道了——车车玩原神。");
	else if (x.getunknown() > 50 && x.getunknown() <= 75)
		p.fast("你现在能精确地推测出车车一天的打扮。");
	else if (x.getunknown() > 75 && x.getunknown() < 100)
		p.fast("就连车车的闺蜜都没有你那般了解车车。");
	else if (x.getunknown() >= 100)
		p.fast("你已经很了解车车了，现在是时候去运用你掌握的信息了！");
	p.fast("你发微信给了车车的闺蜜，询问她车车对你的印象如何……");
	p.noendl("车车对你的好感度为：");
	cout << x.getfavorability() << endl;
	if (x.getfavorability() <= 25)
		p.fast("说句实话，车车现在能不能叫出你的名字都是个问题。");
	else if (x.getfavorability() > 25 && x.getfavorability() <= 50)
		p.fast("现在车车每次遇到你都会打招呼了。");
	else if (x.getfavorability() > 50 && x.getfavorability() <= 75)
		p.fast("车车经常不由自主地提到你。");
	else if (x.getfavorability() > 75 && x.getfavorability() < 100)
		p.fast("车车与你的关系已经亲密无比了。");
	else if (x.getfavorability() >= 100)
		p.fast("现在万物具备，只需要等待一个时机了。");
	system("pause");
	system("cls");
}

void function3(Liuming& x,Date &day) {
	Print p;
	srand((unsigned)time(NULL));
	int a = rand()%20;
	int t = rand() % 3;
	if (t == 0)
		p.mid("夕阳哥跟你讲述并分析了他所听闻的一些感情经历。");
	if (t == 1)
		p.mid("夕阳哥在某物APP上为你找好了穿搭，你改变了一下自己的穿搭。");
	if (t == 2)
		p.mid("夕阳哥在某红书上为你精心挑选了一款发型，你跑去理发店做了个发型。");
	if (t == 3)
		p.mid("夕阳哥对你的表情管理进行了指导，你的表情变得更加自然了。");
	p.noendl("你提升了");
	cout << a;
	p.noendl("点魅力值。");
	x.jiacharm(a);
	cout << endl;
	day.jiaday();
	p.mid("一天就这样过去了……");
	system("pause");
	system("cls");
}

void function4(Liuming& x, Date& day) {
	Print p;
	srand((unsigned)time(NULL));
	int a = rand() % 20;
	int t = rand() % 3;
		p.mid("滕杰带着你来到操场来锻炼");
		if (t == 0)
			p.mid("你做了很多个俯卧撑，你的胸肌增长了。");
		if (t == 1)
			p.mid("你做了很多个引体向上，你的背部肌肉增长了。");
		if (t == 2)
			p.mid("你做了很多个仰卧起坐，你的腹肌生长了。");
		if (t == 3)
			p.mid("你绕着操场跑了好几圈，你的肥肉减少了。");
	p.noendl("你提升了");
	cout << a;
	p.noendl("点力量值。");
	x.jiapower(a);
	cout << endl;
	day.jiaday();
	p.mid("一天就这样过去了……");
	system("pause");
	system("cls");
}

void function5(Liuming& x, Date& day) {
	Print p;
	srand((unsigned)time(0));
	int a = rand() % 20;
	int t = rand() % 3;
	if (t == 0)
		p.mid("你挑选了并观看了一部恐怖电影。");
	if (t == 1)
		p.mid("你跟滕杰等人玩了一款恐怖游戏。");
	if (t == 2)
		p.mid("你在路上尝试跟路人聊天搭话。");
	if (t == 3)
		p.mid("你在凌晨的时候独自逛了校园。");
	p.noendl("你提升了");
	cout << a;
	p.noendl("点勇气值。");
	cout << endl;
	x.jiacourage(a);
	day.jiaday();
	p.mid("一天就这样过去了……");
	system("pause");
	system("cls");
}

void function6(Cheche& x, Date& day) {
	Print p;
	int a, t; srand((unsigned)time(NULL));
	a = rand() % 20;
	t = rand() % 3;
	p.fast("你算准与车车日程重合的时间，开始了你的行动。");
	if (t == 0)
		p.mid("在上高数课上，你仔细端详着她的所作所为。");
	if (t == 1)
		p.mid("在饭堂里，你看到了她今天吃的东西以及评价。");
	if (t == 2)
		p.mid("在路上，你看到了她在跟闺蜜们聊天，你凑过去听了个够。");
	if (t == 3)
		p.mid("你在微信上与车车简单地聊了一下天。");
	p.noendl("你对车车多了");
	cout << a;
	p.noendl("点的了解度。");
	cout << endl;
	x.jiaknown(a);
	day.jiaday();
	p.mid("一天就这样过去了……");
	system("pause");
	system("cls");
}

void function7(Cheche& x, Date& day) {
	Print p;
	int a, b,t; srand((unsigned)time(NULL));
	t = rand() % 3;
	p.fast("你打开了手机，思索着要送些什么给车车好。");
	if (t == 0)
		p.mid("你点开外卖软件，决定给车车送一杯奶茶。");
	if (t == 1)
		p.mid("你直接装给车车648，送了她原神十连抽。");
	if (t == 2)
		p.mid("你将你相册里关于她的照片打印下来并送给了她。");
	if (t == 3)
		p.mid("你买了一束花送给了她。");
	b = 100 - x.getunknown();
	if (b >=75) {
		p.fast("她被你突如其来的礼物吓了一大跳，她现在还不认识你呢。这让她感觉到了冒犯");
		a = 0 - (rand() % 20);
	}
	else if (b < 75 && b >= 50) {
		p.fast("尽管你废了心思，但送的东西不太符合她的心意。但她还是强作微笑接受了。");
		a = rand() % 10-20;
	}
	else if (b < 50 && b >= 25) {
		p.fast("你的礼物戳中了她的心，她微笑着向你道谢了。");
		a = rand() % 30;
	}
	else if (b < 25) {
		p.fast("她被你突如其来礼物吓了一大跳，没有什么比这更加符合她的心意了！");
		a = rand() % 50;
	}
	p.noendl("车车对你多了");
	cout << a;
	p.noendl("点好感度。");
	cout << endl;
	x.jiafavorability(a);
	day.jiaday();
	p.mid("一天就这样过去了……");
	system("pause");
	system("cls");
}

void function8(Date day,Liuming you,Cheche che) {
	fstream p;
	p.open("save.txt", ios::out | ios::trunc);
	if (p.is_open()) {
		p << day.getday() << ' ' << you.getcharm() << ' ' << you.getpower() << ' ' << you.getcourage() << ' ' << che.getunknown() << ' ' << che.getfavorability();
		printf("保存成功！");
	}
	else
		printf("保存失败！");
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
		printf("读取成功！");
	}
	else
		printf("读取失败！");
	system("pause");
	system("cls");
	p.close();
}

void function10() {
	Print p;
	p.mid("确认要退出吗？（1确定，0返回）");
	int x;
	cin >> x;
	if (x == 1) {
		p.fast("感谢你的游玩，再见！");
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