import 'package:graphql/client.dart';
import 'package:fluttertodolistsqfliteapp/graphql/client.dart';

Future<dynamic> insertTodo(dynamic object) async {
  final GraphQLClient _client = getGraphQLClient();
  print("Inside InsertTodo");
  print(object);

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
        mutation InsertTodo($input: InsertTodoInput){
          insertTodo(input: $input) {
            id
            title
            description
            category
          }
        }
      ''',
    ),
    variables: {
      "input": {
        "title": object.title,
        "description": object.description,
        "category": object.category,
      }
    },
  );

  final QueryResult result = await _client.mutate(options);
  if (result.hasException) {
    print(result.exception.toString());
  }

  final dynamic todo = result.data['insertTodo'] as dynamic;
  print(todo);
  return todo;
}

Future<dynamic> deleteTodo(int id) async {
  final GraphQLClient _client = getGraphQLClient();

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
      mutation DeleteTodo($id: Int!) {
        deleteTodo(id: $id) {
          id
          title
        }
      }
    ''',
    ),
    variables: <String, dynamic>{
      'id': id,
    },
  );

  final QueryResult result = await _client.mutate(options);
  if (result.hasException) {
    print(result.exception.toString());
  }
  final dynamic todo = result.data['deleteTodo'] as dynamic;
  print(todo);
  return todo;
}


Future<dynamic> updateTodo(dynamic object) async {
  final GraphQLClient _client = getGraphQLClient();
  print("Inside InsertTodo");
  print(object);

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
        mutation UpdateTodo($id: Int!, $input: InsertTodoInput){
          updateTodo(id: $id, input: $input) {
            id
            title
            description
            category
          }
        }
      ''',
    ),
    variables: {
      "id" : object.id,
      "input": {
        "title": object.title,
        "description": object.description,
        "category": object.category,
      }
    },
  );

  final QueryResult result = await _client.mutate(options);
  if (result.hasException) {
    print(result.exception.toString());
  }

  final dynamic todo = result.data['updateTodo'] as dynamic;
  print(todo);
  return todo;
}