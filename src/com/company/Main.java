package com.company;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length, workerCount = 0;
        boolean[] bikeUsed = new boolean[m], hasBike = new boolean[n];
        int[] res = new int[n];

        // [dis, workderIdx, bikeIdx]
        PriorityQueue<int[]> pq = new PriorityQueue<>( (o1, o2) -> {
            if (o1[0] != o2[0])
                return o1[0] - o2[0];

            if (o1[1] != o2[1])
                return o1[1] - o2[1];

            return o1[2] - o2[2];
        });

        for (int i = 0; i < n; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < m; j++) {
                int[] bike = bikes[j];
                pq.offer(new int[]{Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]), i, j});
            }
        }

        while (workerCount < n) {
            int[] candidate = pq.poll();
            if (bikeUsed[candidate[2]] || hasBike[candidate[1]])
                continue;
            res[candidate[1]] = candidate[2];
            bikeUsed[candidate[2]] = true;
            hasBike[candidate[1]] = true;
            workerCount++;
        }
        return res;
    }
}