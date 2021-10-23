package ru.job4j.Bank;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.List;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final List<User> userList;

    public UserStore(List<User> userList) {
        this.userList = userList;
    }

    public synchronized boolean add(User user) {
        return userList.add(user);
    }

    public synchronized boolean update(User user) {
        User userToUpdate = userList.get(userList.indexOf(user));
        if (userToUpdate == null) {
            return false;
        }
        userToUpdate.setAmount(user.getAmount());
        return true;
    }

    public synchronized boolean delete(User user) {
        return userList.remove(user);
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
