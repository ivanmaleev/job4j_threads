package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

public class CacheTest {

    @Test
    public void whenAdd() {
        Base base1 = new Base(1, 1);
        base1.setName("Base 1");
        Base base2 = new Base(2, 1);
        base2.setName("Base 2");
        Cache cache = new Cache();
        Assert.assertTrue(cache.add(base1));
        Assert.assertTrue(cache.add(base2));
        Assert.assertFalse(cache.add(base2));
    }

    @Test
    public void whenUpdate() {
        Base base1 = new Base(1, 1);
        base1.setName("Base 1");
        Cache cache = new Cache();
        cache.add(base1);
        base1.setName("Base 111");
        Assert.assertTrue(cache.update(base1));
        base1 = new Base(1, 2);
        base1.setName("Base 1.2");
        Assert.assertTrue(cache.update(base1));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateFalse() {
        Base base1 = new Base(1, 1);
        base1.setName("Base 1");
        Cache cache = new Cache();
        cache.add(base1);
        base1 = new Base(1, 2);
        base1.setName("Base 1");
        cache.update(base1);
    }

    @Test
    public void whenDelete() {
        Base base1 = new Base(1, 1);
        base1.setName("Base 1");
        Cache cache = new Cache();
        Assert.assertTrue(cache.add(base1));
        cache.delete(base1);
        Assert.assertTrue(cache.add(base1));
    }
}