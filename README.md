# project_used_trade

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://docs.flutter.dev/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://docs.flutter.dev/cookbook)

For help getting started with Flutter development, view the
[online documentation](https://docs.flutter.dev/), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

실행이 안될 경우
1. 안드로이드 스튜디오에서 new flutter project를 선택하여 새 프로젝트를 생성한다.
2. pubspec.yaml 파일에 cupertino_icons: ^1.0.2 하단에
   webview_flutter: ^4.7.0
   dio: ^5.4.2+1
   를 추가하고 상단의 pub get을 클릭한다.
4. android > app > src > main > AndroidMainfest.xml에 <application> 태그의 첫줄에 android:usesCleartextTraffic="true"를 추가한다.
5. lib > main.dart를 붙여넣기한다.
6. ..loadRequest(Uri.parse('http://c6d2311t1.itwillbs.com/'));
   var response = await Dio().get('http://c6d2311t1.itwillbs.com/goods/glist');
   주소를 확인한다.
   학원에서 주소를 내렸을 시 sts 로컬 주소로 변경한 후 로컬 서버를 실행해야 작동함.
   cmd의 ifconfig 명령어를 통해 http://{ip 주소}:{톰캣 포트번호}/ 로 변경하면 된다.
