import 'package:graphql/client.dart';
import 'package:fluttertodolistsqfliteapp/graphql/client.dart';

Future<List<dynamic>> getCategories() async {
  final GraphQLClient _client = getGraphQLClient();

  final QueryOptions options = QueryOptions(
    document: gql(
      r'''
        query GetTodos {
          categories {
            id
            name
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
  final List<dynamic> categories = result.data['categories'] as List<dynamic>;
  print(categories);
  return categories;
}

Future<dynamic> getCategory(int id) async {
  final GraphQLClient _client = getGraphQLClient();

  final QueryOptions options = QueryOptions(
    document: gql(
      r'''
        query GetCategory($id: Int!) {
          category(id: $id) {
            id
            name
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
  final dynamic category = result.data['category'] as dynamic;
  print(category);
  return category;
}
