import 'package:graphql/client.dart';

GraphQLClient getGraphQLClient() {
  final Link _link = HttpLink('http://localhost:8080/graphql');

  return GraphQLClient(
    cache: GraphQLCache(),
    link: _link,
  );
}