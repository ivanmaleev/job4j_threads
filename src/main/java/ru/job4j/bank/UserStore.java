package ru.job4j.bank;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final Map<Integer, User> userList = new HashMap<>();

    public synchronized boolean add(User user) {
        return userList.put(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        User userToUpdate = userList.get(user.getId());
        if (userToUpdate == null) {
            return false;
        }
        userToUpdate.setAmount(user.getAmount());
        return true;
    }

    public synchronized boolean delete(User user) {
        return userList.remove(user.getId()) != null;
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
