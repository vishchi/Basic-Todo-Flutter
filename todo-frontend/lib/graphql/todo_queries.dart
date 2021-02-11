import 'package:graphql/client.dart';
import 'package:fluttertodolistsqfliteapp/graphql/client.dart';

Future<List<dynamic>> getTodos() async {
  final GraphQLClient _client = getGraphQLClient();

  final QueryOptions options = QueryOptions(
    document: gql(
      r'''
        query GetTodos {
          todos {
            id
            title
            description
            category
          }
        }
      ''',
    ),
    variables: {},
  );

  final QueryResult result = await _client.query(options);
  if (result.hasException) {
    print(result.exception.toString());
  }
  final List<dynamic> todos = result.data['todos'] as List<dynamic>;
  print(todos);
  return todos;
}

Future<dynamic> getTodo(int id) async {
  final GraphQLClient _client = getGraphQLClient();

  final QueryOptions options = QueryOptions(
    document: gql(
      r'''
        query GetTodo($id: Int!) {
          todo(id: $id) {
            id
            title
            description
            category
          }
        }
      ''',
    ),
    variables: {
      'id': id,
    },
  );

  final QueryResult result = await _client.query(options);
  if (result.hasException) {
    print(result.exception.toString());
  }
  final dynamic todo = result.data['todo'] as dynamic;
  print(todo);
  return todo;
}

Future<dynamic> getTodosByCat(String category) async {
  final GraphQLClient _client = getGraphQLClient();

  final QueryOptions options = QueryOptions(
    document: gql(
      r'''
        query GetTodosByCat($category: String!) {
          todos: todosByCat(category: $category){
            id
            title
            description
            category
          }
        }
      ''',
    ),
    variables: {
      'category' : category,
    },
  );

  final QueryResult result = await _client.query(options);
  if (result.hasException) {
    print(result.exception.toString());
  }
  final List<dynamic> todos = result.data['todos'] as List<dynamic>;
  print(todos);
  return todos;
}
