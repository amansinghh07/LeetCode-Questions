import java.util.*;

class Solution {

    static class Ride {
        int start;
        int duration;

        Ride(int start, int duration) {
            this.start = start;
            this.duration = duration;
        }
    }

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int landFirst = solve(
            landStartTime, landDuration,
            waterStartTime, waterDuration
        );

        int waterFirst = solve(
            waterStartTime, waterDuration,
            landStartTime, landDuration
        );

        return Math.min(landFirst, waterFirst);
    }

    private int solve(int[] firstStart, int[] firstDuration,
                      int[] secondStart, int[] secondDuration) {

        int n = secondStart.length;
        Ride[] rides = new Ride[n];

        for (int i = 0; i < n; i++) {
            rides[i] = new Ride(secondStart[i], secondDuration[i]);
        }

        Arrays.sort(rides, (a, b) -> Integer.compare(a.start, b.start));

        // Prefix minimum duration
        int[] prefixMinDuration = new int[n];
        prefixMinDuration[0] = rides[0].duration;

        for (int i = 1; i < n; i++) {
            prefixMinDuration[i] = Math.min(
                prefixMinDuration[i - 1],
                rides[i].duration
            );
        }

        // Suffix minimum finish time: start + duration
        int[] suffixMinFinish = new int[n];
        suffixMinFinish[n - 1] =
            rides[n - 1].start + rides[n - 1].duration;

        for (int i = n - 2; i >= 0; i--) {
            suffixMinFinish[i] = Math.min(
                suffixMinFinish[i + 1],
                rides[i].start + rides[i].duration
            );
        }

        int ans = Integer.MAX_VALUE;

        // Try every ride as the first ride
        for (int i = 0; i < firstStart.length; i++) {
            int firstFinish = firstStart[i] + firstDuration[i];

            // Find first ride whose start time > firstFinish
            int left = 0, right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (rides[mid].start <= firstFinish) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            int idx = left;

            // Case 1: Second ride is already open
            if (idx > 0) {
                ans = Math.min(
                    ans,
                    firstFinish + prefixMinDuration[idx - 1]
                );
            }

            // Case 2: Second ride opens after firstFinish
            if (idx < n) {
                ans = Math.min(ans, suffixMinFinish[idx]);
            }
        }

        return ans;
    }
}