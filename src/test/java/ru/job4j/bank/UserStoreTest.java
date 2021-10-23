package ru.job4j.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void add() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 150);
        Assert.assertTrue(userStore.add(user1));
        Assert.assertTrue(userStore.add(user2));
        Assert.assertFalse(userStore.add(user1));
    }

    @Test
    public void update() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(1, 150);
        userStore.add(user1);
        Assert.assertTrue(userStore.update(user2));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 150);
        userStore.add(user1);
        userStore.add(user2);
        Assert.assertTrue(userStore.delete(user1));
        Assert.assertTrue(userStore.delete(user2));
        Assert.assertFalse(userStore.delete(user1));
    }

    @Test
    public void transferTrue() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 150);
        userStore.add(user1);
        userStore.add(user2);
        Assert.assertTrue(userStore.transfer(1, 2, 100));
    }

    @Test
    public void transferNotEnoughMonye() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 150);
        userStore.add(user1);
        userStore.add(user2);
        Assert.assertFalse(userStore.transfer(1, 2, 200));
    }
}