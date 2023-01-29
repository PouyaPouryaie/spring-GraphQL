package ir.bigz.spring.graphql.person.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PersonQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    private final static Logger log = LoggerFactory.getLogger(PersonQLExceptionHandler.class);

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return switch (ex){
            case PersonQLException e -> toGraphQLError(e, env);
            case ConstraintViolationException e -> handleConstraintViolationException(e, env);
            default -> super.resolveToSingleError(ex,env);
        };
    }

    private <T extends RuntimeException> GraphQLError toGraphQLError(T ex, DataFetchingEnvironment env) {
        log.warn(String.format("Exception while handling request: %s", ex.getMessage()));
        return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    private GraphQLError handleConstraintViolationException(ConstraintViolationException ex, DataFetchingEnvironment env) {
        var errorMessages = new HashSet<String>();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            errorMessages.add(String.format("%s", constraintViolation.getMessage()));
        }
        var message = String.join(", ", errorMessages.stream().toList());
        log.warn(String.format("Exception while handling request: %s", ex.getMessage()));
        return GraphqlErrorBuilder.newError()
                .message(message)
                .errorType(ErrorType.NOT_FOUND)
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
