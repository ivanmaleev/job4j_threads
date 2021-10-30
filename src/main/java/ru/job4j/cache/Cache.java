package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() != memory.get(k).getVersion()) {
                throw new OptimisticException("Not updated");
            }
            Base newModel = new Base(model.getId(), model.getVersion() + 1);
            newModel.setName(model.getName());
            return newModel;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}