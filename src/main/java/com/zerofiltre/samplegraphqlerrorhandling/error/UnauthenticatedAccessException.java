package com.zerofiltre.samplegraphqlerrorhandling.error;

import graphql.*;
import graphql.language.*;

import java.util.*;

public class UnauthenticatedAccessException extends RuntimeException implements GraphQLError {

    public UnauthenticatedAccessException(String msg) {
        super(msg);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
