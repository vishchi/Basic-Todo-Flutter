import 'package:flutter/material.dart';
import 'package:fluttertodolistsqfliteapp/helpers/drawer_navigation.dart';
import 'package:fluttertodolistsqfliteapp/models/todo.dart';
import 'package:fluttertodolistsqfliteapp/screens/todo_screen.dart';
import 'package:fluttertodolistsqfliteapp/graphql/todo_queries.dart';

class HomeScreen extends StatefulWidget {
  final String userEmail;
  HomeScreen({Key key, @required this.userEmail }): super(key: key);
  @override
  _HomeScreenState createState() => _HomeScreenState(userEmail: userEmail);
}

class _HomeScreenState extends State<HomeScreen> {
  List<Todo> _todoList = <Todo>[];
  final String userEmail;
  _HomeScreenState({@required this.userEmail });

  @override
  initState() {
    super.initState();
    getAllTodos();
  }

  getAllTodos() async {
    _todoList = <Todo>[];

    // var todos = await _todoService.readTodos();
    var todos = await getTodos();

    todos.forEach((todo) {
      setState(() {
        var model = Todo();
        model.id = todo['id'];
        model.title = todo['title'];
        model.description = todo['description'];
        model.category = todo['category']['name'];
        _todoList.add(model);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blueGrey[900],
        title: Text('To-Do List'),
        centerTitle: true,
      ),
      backgroundColor: Colors.blueGrey,
      drawer: DrawerNavigaton(userEmail: userEmail),
      body: ListView.builder(
          itemCount: _todoList.length,
          itemBuilder: (context, index) {
            return Padding(
              padding: EdgeInsets.only(top: 8.0, left: 8.0, right: 8.0),
              child: Card(
                  elevation: 8,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(0)),
                  child: ListTile(
                    title: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(_todoList[index].title ?? 'No Title')
                      ],
                    ),
                    subtitle: Text(_todoList[index].category ?? 'No Category'),
                  )),
            );
          }),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.of(context)
            .push(MaterialPageRoute(builder: (context) => TodoScreen())),
        child: Icon(Icons.add),
      ),
    );
  }
}
