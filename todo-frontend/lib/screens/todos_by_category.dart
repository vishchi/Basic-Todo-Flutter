import 'package:flutter/material.dart';
import 'package:fluttertodolistsqfliteapp/graphql/todo_queries.dart';
import 'package:fluttertodolistsqfliteapp/models/todo.dart';

class TodosByCategory extends StatefulWidget {
  final String category;

  TodosByCategory({this.category});

  @override
  _TodosByCategoryState createState() => _TodosByCategoryState();
}

class _TodosByCategoryState extends State<TodosByCategory> {
  List<Todo> _todoList = <Todo>[];

  @override
  void initState() {
    super.initState();
    getTodosByCategories();
  }

  getTodosByCategories() async {
    // var todos = await _todoService.readTodosByCategory(this.widget.category);
    var todos = await getTodosByCat(this.widget.category); 
    todos.forEach((todo) {
      setState(() {
        var model = Todo();
        model.title = todo['title'];
        model.category = todo['category']['name'];
        model.description = todo['description'];
        _todoList.add(model);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(this.widget.category)),
      backgroundColor: Colors.blueGrey,
      body: Column(
        children: <Widget>[
          Expanded(
              child: ListView.builder(
                  itemCount: _todoList.length,
                  itemBuilder: (context, index) {
                    return Padding(
                      padding: EdgeInsets.only(top: 8.0, left: 8.0, right: 8.0),
                      child: Card(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(0)),
                        elevation: 8,
                        child: ListTile(
                          title: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: <Widget>[Text(_todoList[index].title)],
                          ),
                          subtitle: Text(_todoList[index].description),
                        ),
                      ),
                    );
                  }))
        ],
      ),
    );
  }
}
