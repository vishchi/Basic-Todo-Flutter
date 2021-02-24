import 'package:graphql/client.dart';
import 'package:fluttertodolistsqfliteapp/graphql/client.dart';

Future<dynamic> insertCategory(dynamic object) async {
  final GraphQLClient _client = GQLClient.getGraphQLClientAuth();

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
        mutation InsertCategory($input: InsertCategoryInput) {
          insertCategory(input: $input) {
            id
            name
          }
        }
      ''',
    ),
    variables: <String, dynamic>{
      "input": {
        "name": object.name,
      }
    },
  );

  final QueryResult result = await _client.mutate(options);
  if (result.hasException) {
    print(result.exception.toString());
  }

  final dynamic todo = result.data['insertCategory'] as dynamic;
  print(todo);
  return todo;
}

Future<dynamic> updateCategory(dynamic object) async {
  final GraphQLClient _client = GQLClient.getGraphQLClientAuth();

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
        mutation UpdateCategory($id: Int!, $input: InsertCategoryInput) {
          updateCategory(id: $id, input: $input) {
            id
            name
          }
        }
      ''',
    ),
    variables: <String, dynamic>{
      "id": object.id,
      "input": {
        "name": object.name,
      }
    },
  );

  final QueryResult result = await _client.mutate(options);
  if (result.hasException) {
    print(result.exception.toString());
  }

  final dynamic todo = result.data['updateCategory'] as dynamic;
  print(todo);
  return todo;
}

Future<dynamic> deleteCategory(int id) async {
  final GraphQLClient _client = GQLClient.getGraphQLClientAuth();

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
        mutation DeleteCategory($id: Int!){
          deleteCategory(id: $id) {
            id
            name
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
  final dynamic category = result.data['deleteCategory'] as dynamic;
  print(category);
  return category;
}
