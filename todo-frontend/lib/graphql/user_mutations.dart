import 'package:graphql/client.dart';
import 'package:fluttertodolistsqfliteapp/graphql/client.dart';

Future<dynamic> insertUser(dynamic object) async {
  final GraphQLClient _client = GQLClient.getGraphQLClientAuth();
  print("Inside InsertUser");
  print(object);

  final MutationOptions options = MutationOptions(
    document: gql(
      r'''
        mutation InsertUser($input: InsertUserInput) {
          insertUser(input: $input) {
            email
            token
          }
        }
      ''',
    ),
    variables: {
      "input": {
        "email": object.email,
        "password": object.password
      }
    },
  );

  final QueryResult result = await _client.mutate(options);
  if (result.hasException) {
    print(result.exception.toString());
  }

  final dynamic user = result.data['insertUser'] as dynamic;
  print(user);
  return user;
}