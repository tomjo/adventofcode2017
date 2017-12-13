package be.tomjo.advent.day6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;

public class ReallocationRoutine {

    public static void main(String[] args) {
        String input = readInput("6.txt");
        int[] memoryBanks = toMemoryBanks(input);
        ReallocationRoutine reallocationRoutine = new ReallocationRoutine();
        System.out.println("Solution 6.1: "+reallocationRoutine.reallocateUntilCycleFound(memoryBanks));
    }

    public int reallocateUntilCycleFound(int[] memoryBanks){
        Set<MemoryBanks> configurations = new HashSet<>(5000);
        while(configurations.add(new MemoryBanks(memoryBanks))){
            reallocate(memoryBanks);
        }
        return configurations.size();
    }

    public void reallocate(int[] memoryBanks){
        int highest = -1;
        int currentMemoryBank = getHighestMemoryBank(memoryBanks, highest);
        int redistributionValue = memoryBanks[currentMemoryBank];
        memoryBanks[currentMemoryBank] = 0;
        while(redistributionValue > 0){
            currentMemoryBank = (currentMemoryBank + 1) % memoryBanks.length;
            memoryBanks[currentMemoryBank]++;
            redistributionValue--;
        }
    }

    private int getHighestMemoryBank(int[] memoryBanks, int highest) {
        int highestMemoryBank = -1;
        for (int i = 0; i < memoryBanks.length; i++) {
            if(memoryBanks[i] > highest){
                highest = memoryBanks[i];
                highestMemoryBank = i;
            }
        }
        return highestMemoryBank;
    }

    private static int[] toMemoryBanks(String input) {
        return stream(input.replace("\r\n", "").split("\t")).mapToInt(Integer::parseInt).toArray();
    }

    static class MemoryBanks {
        private final int[] memoryBanks;

        public MemoryBanks(int[] memoryBanks) {
            this.memoryBanks = Arrays.copyOf(memoryBanks, memoryBanks.length);
        }

        public int[] getMemoryBanks() {
            return memoryBanks;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MemoryBanks that = (MemoryBanks) o;

            return Arrays.equals(memoryBanks, that.memoryBanks);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(memoryBanks);
        }
    }

}
