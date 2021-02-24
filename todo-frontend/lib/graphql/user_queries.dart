import 'package:graphql/client.dart';
import 'package:fluttertodolistsqfliteapp/graphql/client.dart';

Future<dynamic> loginUser(dynamic object) async {
  final GraphQLClient _client = GQLClient.getGraphQLClientAuth();
  print("Inside LoginUser");
  print(object);

  final QueryOptions options = QueryOptions(
    document: gql(
      r'''
        query LoginUser($input: InsertUserInput!) {
          user(input: $input) {
            email
            token
          }
        }
      ''',
    ),
    variables: {
      "input": {"email": object.email, "password": object.password}
    },
  );

  final QueryResult result = await _client.query(options);
  if (result.hasException) {
    print(result.exception.toString());
  }
  final dynamic user = result.data['user'] as dynamic;
  print(user);
  return user;
}
