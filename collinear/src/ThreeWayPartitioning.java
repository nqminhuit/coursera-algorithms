import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class ThreeWayPartitioning {

    private int[] nums;

    private int n;

    public ThreeWayPartitioning(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
    }

    public void partition() {
        int v = nums[0];
        int i = 0;
        int lt = 0;
        int gt = n - 1;

        while (i <= gt) {
            if (nums[i] < v) {
                exchange(nums, lt++, i++);
            } else if (nums[i] > v) {
                exchange(nums, gt--, i);
            } else {
                i++;
            }
        }

    }

    private void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int n = 10;

        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = StdRandom.uniform(n / 2);
        }
        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();

        ThreeWayPartitioning twp = new ThreeWayPartitioning(nums);
        twp.partition();

        Arrays.stream(nums).forEach(System.out::print);

    }

}
