package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        var temp  = model.getId();
        return memory.computeIfPresent(temp, (key, value) -> {
            Base stored = memory.get(temp);
            if (stored.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base newBase = new Base(model.getId(), model.getVersion() + 1);
            newBase.setName(model.getName());
            return newBase;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(int key) {
        return memory.get(key);
    }


}