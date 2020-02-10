package com.zerofiltre.samplegraphqlerrorhandling.error.handler;

import graphql.*;
import graphql.kickstart.execution.error.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Component
public class GraphQLExceptionHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::getNested).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
            if (exceptionError.getException() instanceof GraphQLError) {
                return (GraphQLError) exceptionError.getException();
            }
        }
        return error;
    }

}
