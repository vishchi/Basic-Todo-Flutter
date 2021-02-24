import 'package:graphql/client.dart';

class GQLClient {
  static var isToken = false;
  static String token = "";

  static GraphQLClient getGraphQLClient() {
    final Link _link = HttpLink('http://localhost:8080/graphql');

    return GraphQLClient(
      cache: GraphQLCache(),
      link: _link,
    );
  }

  static GraphQLClient getGraphQLClientAuth() {
    final _httpLink = HttpLink('http://localhost:8080/graphql');
    final _authLink = AuthLink(
      getToken: () async => 'Bearer ' + GQLClient.token,
    );
    Link _link = _authLink.concat(_httpLink);
    
    return GraphQLClient(
      cache: GraphQLCache(),
      link: _link,
    );
  }
}
