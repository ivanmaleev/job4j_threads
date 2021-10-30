package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;

public class RolColSumTest {

    @Test
    public void sum() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        RolColSum.Sums[] exp = new RolColSum.Sums[]{new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15), new RolColSum.Sums(24, 18)};

        Assert.assertThat(sums, is(exp));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        RolColSum.Sums[] exp = new RolColSum.Sums[]{new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15), new RolColSum.Sums(24, 18)};

        Assert.assertThat(sums, is(exp));
    }
}