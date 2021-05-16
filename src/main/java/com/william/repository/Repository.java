package com.william.repository;

import java.util.List;

public interface Repository<T> {

    void add(List<T> elements);

    void add(T element);

    boolean exists(T element);
}
