package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;

public class ParallelSearchTest {

    @Test
    public void search() {
        int size = 1000;
        Random random = new Random();
        Integer[] list = new Integer[size];
        Integer obj = 463;
        int index = 856;
        for (int i = 0; i < size; i++) {
            list[i] = (random.nextInt(100));
            if (i == index) {
                list[i] = obj;
            }
        }
        Integer search = ParallelSearch.search(list, obj);
        Assert.assertThat(search, is(index));
    }
}