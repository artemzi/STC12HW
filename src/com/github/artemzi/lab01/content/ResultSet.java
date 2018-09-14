package com.github.artemzi.lab01.content;

<<<<<<< Updated upstream
import com.github.artemzi.lab01.exceptions.CannotAddContentException;
import com.github.artemzi.lab01.utils.WaitGroup;

=======
>>>>>>> Stashed changes
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ResultSet {
    private Set<String> data;
    private ResultSet() {
        this.data = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
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

    public void addData(String data) throws CannotAddContentException {
        boolean added = this.data.add(data);
        if (!added) {
            throw new CannotAddContentException("Cannot add sentence to collection");
        }
    }
}
