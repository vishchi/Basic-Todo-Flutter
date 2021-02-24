import 'package:flutter/material.dart';
import 'package:fluttertodolistsqfliteapp/screens/categories_screen.dart';
import 'package:fluttertodolistsqfliteapp/screens/home_screen.dart';
import 'package:fluttertodolistsqfliteapp/screens/todos_by_category.dart';
import 'package:fluttertodolistsqfliteapp/graphql/category_queries.dart';

class DrawerNavigaton extends StatefulWidget {
  final String userEmail;
  DrawerNavigaton({Key key, @required this.userEmail});
  @override
  _DrawerNavigatonState createState() => _DrawerNavigatonState(userEmail: this.userEmail);
}

class _DrawerNavigatonState extends State<DrawerNavigaton> {
  final String userEmail;
  _DrawerNavigatonState({@required this.userEmail});
  List<Widget> _categoryList = <Widget>[];

  @override
  initState() {
    super.initState();
    getAllCategories();
  }

  getAllCategories() async {
    // var categories = await _categoryService.readCategories();
    var categories = await getCategories();

    categories.forEach((category) {
      setState(() {
        _categoryList.add(InkWell(
          onTap: () => Navigator.push(
              context,
              new MaterialPageRoute(
                  builder: (context) => new TodosByCategory(
                        category: category['name'],
                      ))),
          child: ListTile(
            title: Text(category['name']),
          ),
        ));
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Drawer(
        child: ListView(
          children: <Widget>[
            UserAccountsDrawerHeader(
              currentAccountPicture: CircleAvatar(
                backgroundImage: NetworkImage(
                    'https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-512.png'),
              ),
              accountName: Text(""),
              accountEmail: Text(userEmail) ,
              decoration: BoxDecoration(color: Colors.blueGrey[900]),
            ),
            ListTile(
              leading: Icon(Icons.home),
              title: Text('Home'),
              onTap: () => Navigator.of(context)
                  .push(MaterialPageRoute(builder: (context) => HomeScreen(userEmail: userEmail))),
            ),
            ListTile(
              leading: Icon(Icons.view_list),
              title: Text('Categories'),
              onTap: () => Navigator.of(context).push(
                  MaterialPageRoute(builder: (context) => CategoriesScreen(userEmail: userEmail))),
            ),
            Divider(),
            Column(
              children: _categoryList,
            ),
          ],
        ),
      ),
    );
  }
}
