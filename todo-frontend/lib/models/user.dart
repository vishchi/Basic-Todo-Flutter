class User {
  int id;
  String email;
  String password;

  userMap() {
    var mapping = Map<String, dynamic>();
    mapping['id'] = id;
    mapping['email'] = email;
    mapping['password'] = password;
    return mapping;
  }
}