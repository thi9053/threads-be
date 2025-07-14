package thi.com.threads_be.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;
import thi.com.threads_be.enums.ErrorCode;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

public class CustomGQLExceptionResolver implements DataFetcherExceptionResolver {
    @SuppressWarnings("null")
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment env) {
        if (exception instanceof AppException appException) {
            Map<String, Object> extensions = new HashMap<>();
            extensions.put("errorCode", appException.getErrorCode().name());
            GraphQLError error = GraphqlErrorBuilder.newError(env)
                    .message(appException.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .extensions(extensions)
                    .build();
            return Mono.just(List.of(error));
        }

        if (exception instanceof AccessDeniedException accessDeniedException) {
            Map<String, Object> extensions = new HashMap<>();
            extensions.put("errorCode", ErrorCode.FORBIDDEN.name());
            GraphQLError error = GraphqlErrorBuilder.newError(env)
                    .message(accessDeniedException.getMessage())
                    .errorType(ErrorType.FORBIDDEN)
                    .extensions(extensions)
                    .build();
            return Mono.just(List.of(error));
        }

        GraphQLError genericError = GraphqlErrorBuilder.newError(env)
                .message("An unexpected error occurred")
                .errorType(ErrorType.INTERNAL_ERROR)
                .extensions(Map.of("errorCode", ErrorCode.INTERNAL_SERVER_ERROR.name()))
                .build();

        return Mono.just(List.of(genericError));
    }
}
