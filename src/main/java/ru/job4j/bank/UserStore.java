package ru.job4j.bank;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final Map<Integer, User> userList = new ConcurrentHashMap<>();

    public synchronized boolean add(User user) {
        return userList.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return userList.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return userList.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = userList.get(fromId);
        User userTo = userList.get(toId);
        if (userFrom == null || userTo == null) {
            return false;
        }
        int remains = userFrom.getAmount() - amount;
        if (remains < 0) {
            System.out.println("Not enough money");
            return false;
        }
        userFrom.setAmount(remains);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}
