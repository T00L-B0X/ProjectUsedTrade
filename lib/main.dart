import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      initialRoute: '/one',
      routes: {
        '/one':(context)=>OneScreen(),
        '/two':(context)=>TwoScreen(),
      },
    );
  }

}

class OneScreen extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return OneScreenState();
  }
}

class OneScreenState extends State<OneScreen>{
  bool webScreen =true;

  var controller = WebViewController()
    ..setJavaScriptMode(JavaScriptMode.unrestricted)
    ..setBackgroundColor(const Color(0x00000000))
    ..setNavigationDelegate(
      NavigationDelegate(
        onProgress: (int progress) {
          // Update loading bar.
        },
        onPageStarted: (String url) {},
        onPageFinished: (String url) {},
        onWebResourceError: (WebResourceError error) {},
        onNavigationRequest: (NavigationRequest request) {
          if (request.url.startsWith('https://www.youtube.com/')) {
            return NavigationDecision.prevent;
          }
          return NavigationDecision.navigate;
        },
      ),
    )
    ..loadRequest(Uri.parse('http://c6d2311t1.itwillbs.com/'));

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('내 프로젝트'),
          actions: [
            Switch(
                value: webScreen,
                onChanged: (value){
                  setState(() {
                    webScreen = value;
                  });

                  Navigator.pushNamed(context, '/two');
                })
          ],
        ),
        body: WebViewWidget(controller: controller),
      ),

    );
  }

}


class TwoScreen extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return TwoScreenState();
  }
}
class TwoScreenState extends State<TwoScreen>{

  Future<List<MyProduct>> dioTest() async{
    List<MyProduct> list =[];

    try{
      var response = await Dio().get('http://c6d2311t1.itwillbs.com/goods/glist');
      if(response.statusCode == 200){
        String result = response.data.toString();
        print("result : $result");
        List jList = jsonDecode(result);
        for(var myObj in jList){
          if(myObj is Map<String, dynamic>){
            MyProduct p1 = MyProduct.fromJson(myObj);
            list.add(p1);
          }
        }
        print('가져온 데이터 리스트의 개수: ${list.length}');
      }
    }catch(e){
      print(e);
    }
    return list;
  }

  show_dialog(var myProd){
    showDialog(context: context,
        barrierDismissible:true,
        builder: (context){
          return AlertDialog(
            title: Text('세부정보 보기'),
            content: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Image(image: NetworkImage('http://c6d2311t1.itwillbs.com/test_flutter/flutterImg/${myProd.imgSrc}') ,),
              ],
            ),
            actions: [
              TextButton(onPressed: (){
                Navigator.pop(context);
              }, child: Text('OK'))
            ],
          );

        });
  }

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
          appBar: AppBar(
            title: Text('서버에서 데이터 가져오기'),
            actions: [
              IconButton(
                  onPressed: (){
                    Navigator.popAndPushNamed(context, '/three');
                    // Navigator.pushNamed(context, '/three');
                  }, icon: Icon(Icons.adb))
            ],
          ),
          body: FutureBuilder(
            future: dioTest(),
            builder: (context, AsyncSnapshot<List<MyProduct>> snapshot) {
              if(snapshot.hasData){
                var data = snapshot.data!;
                return ListView.separated(
                    itemBuilder: (context, index){
                      return ListTile(
                        // leading: const CircleAvatar(
                        //   radius: 25,
                        //   backgroundImage: NetworkImage('http://192.168.6.200:8181/test_flutter/flutterImg/') ,),
                        //NetworkImage('https://docs.flutter.dev/assets/images/dash/dash-fainting.gif') ,),
                        title: Text(data[index].goods_title),
                        subtitle: Text('가격 ${data[index].current_price}원'),
                        trailing: Icon(Icons.more_vert),
                        onTap: (){
                          show_dialog(data[index]);
                        },
                      );},
                    separatorBuilder: (context, index){ return Divider(height: 2, color: Colors.black,);},
                    itemCount: data.length);
              }
              return const Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    SizedBox(
                      width: 100,
                      height: 100,
                      child: CircularProgressIndicator(),
                    ),
                    Text('waiting..', style: TextStyle(color: Colors.black, fontSize: 20),)
                  ],
                ),
              );

            },)
      ),
    );
  }

}


class MyProduct {
  String goods_title;
  int current_price;

  MyProduct(this.goods_title, this.current_price);
  MyProduct.fromJson(Map<String, dynamic> json)
      :goods_title=json['goods_title'], current_price=json['current_price'];
}


class ThreeScreen extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return ThreeScreenState();
  }
}

class ThreeScreenState extends State<ThreeScreen>{
  String message="들어온 데이터 없음 ";

  @override
  Widget build(BuildContext context) {
    var controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..setBackgroundColor(Colors.grey)
      ..setNavigationDelegate(
        NavigationDelegate(
          onProgress: (int progress) {
            // Update loading bar.
          },
          onPageStarted: (String url) {},
          onPageFinished: (String url) {},
          onWebResourceError: (WebResourceError error) {},
          onNavigationRequest: (NavigationRequest request) {
            if (request.url.startsWith('https://www.youtube.com/')) {
              return NavigationDecision.prevent;
            }
            return NavigationDecision.navigate;
          },
        ),
      )
      ..addJavaScriptChannel('flutter_channel',
          onMessageReceived: (JavaScriptMessage msg){
            print(' 들어온 메세지 ${msg.message}');
            setState(() {
              message = msg.message;
            });
          })
      ..loadRequest(Uri.parse('http://192.168.6.200:8181/test_flutter/downloadWebview.jsp'));

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Webview로 서버 양방향 통신'),),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('들어온 데이터 : $message'),
              SizedBox(
                width: 400,
                height: 400,
                child: WebViewWidget(controller: controller,),
              ),
              ElevatedButton(onPressed: (){
                controller.runJavaScript('appToWeb("모바일에서 보낸 데이터")');
              }, child: Text("서버에 메세지 보내기"))
            ],
          ),
        ),
      ),
    );
  }
}

