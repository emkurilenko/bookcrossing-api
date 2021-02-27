package com.bookcrossing.api.utils;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamSupportUtils {

    public static <R> Stream<R> toStream(Iterable<R> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
