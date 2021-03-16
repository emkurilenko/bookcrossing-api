package com.bookcrossing.api.config.dispatcher;

public interface Dispatcher<K, V> {
  V getByName(K name);
}
