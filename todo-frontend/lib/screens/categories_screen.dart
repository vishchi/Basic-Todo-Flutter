import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertodolistsqfliteapp/models/category.dart';
import 'package:fluttertodolistsqfliteapp/screens/home_screen.dart';
import 'package:fluttertodolistsqfliteapp/graphql/category_queries.dart';
import 'package:fluttertodolistsqfliteapp/graphql/category_mutations.dart';

class CategoriesScreen extends StatefulWidget {
  final String userEmail;
  CategoriesScreen({Key key, @required this.userEmail}): super(key: key);
  @override
  _CategoriesScreenState createState() => _CategoriesScreenState(userEmail: userEmail);
}

class _CategoriesScreenState extends State<CategoriesScreen> {
  final String userEmail;
  _CategoriesScreenState({@required this.userEmail});
  var _categoryNameController = TextEditingController();

  var _category = Category();

  List<Category> _categoryList = <Category>[];

  var category;

  var _editCategoryNameController = TextEditingController();

  @override
  void initState() {
    super.initState();
    getAllCategories();
  }

  final GlobalKey<ScaffoldState> _globalKey = GlobalKey<ScaffoldState>();

  getAllCategories() async {
    _categoryList = <Category>[];
    // var categories = await _categoryService.readCategories();
    var categories = await getCategories();
    categories.forEach((category) {
      setState(() {
        var categoryModel = Category();
        categoryModel.name = category['name'];
        categoryModel.id = category['id'];
        _categoryList.add(categoryModel);
      });
    });
  }

  _editCategory(BuildContext context, categoryId) async {
    // category = await _categoryService.readCategoryById(categoryId);
    category = await getCategory(categoryId);
    setState(() {
      _editCategoryNameController.text = category['name'] ?? 'No Name';
    });
    _editFormDialog(context);
  }

  _showFormDialog(BuildContext context) {
    return showDialog(
        context: context,
        barrierDismissible: true,
        builder: (param) {
          return AlertDialog(
            actions: <Widget>[
              // ignore: deprecated_member_use
              FlatButton(
                color: Colors.red,
                onPressed: () => Navigator.pop(context),
                child: Text('Cancel'),
              ),
              // ignore: deprecated_member_use
              FlatButton(
                color: Colors.blue,
                onPressed: () async {
                  _category.name = _categoryNameController.text;

                  // var result = await _categoryService.saveCategory(_category);
                  var result = await insertCategory(_category);
                  if (!result.isEmpty) {
                    print(result);
                    Navigator.pop(context);
                    getAllCategories();
                  }
                },
                child: Text('Save'),
              ),
            ],
            title: Text('Categories Form'),
            content: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  TextField(
                    controller: _categoryNameController,
                    decoration: InputDecoration(
                        hintText: 'Write a category', labelText: 'Category'),
                  ),
                ],
              ),
            ),
          );
        });
  }

  _editFormDialog(BuildContext context) {
    return showDialog(
        context: context,
        barrierDismissible: true,
        builder: (param) {
          return AlertDialog(
            actions: <Widget>[
              // ignore: deprecated_member_use
              FlatButton(
                color: Colors.red,
                onPressed: () => Navigator.pop(context),
                child: Text('Cancel'),
              ),
              // ignore: deprecated_member_use
              FlatButton(
                color: Colors.blue,
                onPressed: () async {
                  _category.id = category['id'];
                  _category.name = _editCategoryNameController.text;

                  // var result = await _categoryService.updateCategory(_category);
                  var result = await updateCategory(_category); // TODO update
                  if (!result.isEmpty) {
                    Navigator.pop(context);
                    getAllCategories();
                    _showSuccessSnackBar(Text('Updated'));
                  }
                },
                child: Text('Update'),
              ),
            ],
            title: Text('Edit Categories Form'),
            content: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  TextField(
                    controller: _editCategoryNameController,
                    decoration: InputDecoration(
                        hintText: 'Write a category', labelText: 'Category'),
                  ),
                ],
              ),
            ),
          );
        });
  }

  _deleteFormDialog(BuildContext context, categoryId) {
    return showDialog(
        context: context,
        barrierDismissible: true,
        builder: (param) {
          return AlertDialog(
            actions: <Widget>[
              // ignore: deprecated_member_use
              FlatButton(
                color: Colors.green,
                onPressed: () => Navigator.pop(context),
                child: Text('Cancel'),
              ),
              // ignore: deprecated_member_use
              FlatButton(
                color: Colors.red,
                onPressed: () async {
                  // var result = await _categoryService.deleteCategory(categoryId);
                  var result = await deleteCategory(categoryId);
                  if (!result.isEmpty) {
                    Navigator.pop(context);
                    getAllCategories();
                    _showSuccessSnackBar(Text('Deleted'));
                  }
                },
                child: Text('Delete'),
              ),
            ],
            title: Text('Are you sure you want to delete this?'),
          );
        });
  }

  _showSuccessSnackBar(message) {
    var _snackBar = SnackBar(content: message);
    // ignore: deprecated_member_use
    _globalKey.currentState.showSnackBar(_snackBar);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _globalKey,
      appBar: AppBar(
        backgroundColor: Colors.blueGrey[900],
        // ignore: deprecated_member_use
        leading: RaisedButton(
          onPressed: () => Navigator.of(context)
              .push(MaterialPageRoute(builder: (context) => HomeScreen(userEmail: userEmail))),
          elevation: 0.0,
          child: Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
          color: Colors.blueGrey[900],
        ),
        title: Text('Categories'),
      ),
      backgroundColor: Colors.blueGrey,
      body: ListView.builder(
          itemCount: _categoryList.length,
          itemBuilder: (context, index) {
            return Padding(
              padding: EdgeInsets.only(top: 8.0, left: 16.0, right: 16.0),
              child: Card(
                elevation: 8.0,
                child: ListTile(
                  leading: IconButton(
                      icon: Icon(Icons.edit),
                      onPressed: () {
                        _editCategory(context, _categoryList[index].id);
                      }),
                  title: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Text(_categoryList[index].name),
                      IconButton(
                          icon: Icon(
                            Icons.delete,
                            color: Colors.red,
                          ),
                          onPressed: () {
                            _deleteFormDialog(context, _categoryList[index].id);
                          })
                    ],
                  ),
                ),
              ),
            );
          }),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _showFormDialog(context);
        },
        child: Icon(Icons.add),
      ),
    );
  }
}
