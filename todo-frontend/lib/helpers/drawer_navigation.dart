import 'package:flutter/material.dart';
import 'package:fluttertodolistsqfliteapp/screens/categories_screen.dart';
import 'package:fluttertodolistsqfliteapp/screens/home_screen.dart';
import 'package:fluttertodolistsqfliteapp/screens/todos_by_category.dart';
import 'package:fluttertodolistsqfliteapp/services/category_service.dart';

class DrawerNavigaton extends StatefulWidget {
  @override
  _DrawerNavigatonState createState() => _DrawerNavigatonState();
}

class _DrawerNavigatonState extends State<DrawerNavigaton> {
  List<Widget> _categoryList = <Widget>[];

  CategoryService _categoryService = CategoryService();

  @override
  initState() {
    super.initState();
    getAllCategories();
  }

  getAllCategories() async {
    var categories = await _categoryService.readCategories();

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
                    'https://images.ctfassets.net/hrltx12pl8hq/5hb9s8fTpw4Pk7fEm8Hdfs/d7c5ae85aad44fb334bcde8268135774/flowers-images-card.jpg?fit=fill&w=368&h=207'),
              ),
              accountName: Text('Vishal Chincholi'),
              accountEmail: Text('vishalsc1999@gmail.com'),
              decoration: BoxDecoration(color: Colors.blueGrey[900]),
            ),
            ListTile(
              leading: Icon(Icons.home),
              title: Text('Home'),
              onTap: () => Navigator.of(context)
                  .push(MaterialPageRoute(builder: (context) => HomeScreen())),
            ),
            ListTile(
              leading: Icon(Icons.view_list),
              title: Text('Categories'),
              onTap: () => Navigator.of(context).push(
                  MaterialPageRoute(builder: (context) => CategoriesScreen())),
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
