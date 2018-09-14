package com.github.artemzi.lab01.content;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ResultSet {
    private Set<String> data;
    private ResultSet() {
        this.data = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    private static class ResultSetHolder {
        private static final ResultSet INSTANCE = new ResultSet();
    }

    public static ResultSet getInstance() {
        return ResultSetHolder.INSTANCE;
    }

    public Set<String> getData() {
        return data;
    }

    public void addData(String data) {
        this.data.add(data);
    }
}
