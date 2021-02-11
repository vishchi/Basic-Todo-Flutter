class Todo {
  int id;
  String title;
  String description;
  String category;

  todoMap() {
    var mapping = Map<String, dynamic>();
    mapping['id'] = id;
    mapping['title'] = title;
    mapping['description'] = description;
    mapping['category'] = category;

    return mapping;
  }
}
