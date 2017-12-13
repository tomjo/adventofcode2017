package be.tomjo.advent.day6;

import java.util.*;

import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;

public class ReallocationRoutine {

    private List<MemoryBankConfiguration> configurations;
    private MemoryBankConfiguration lastAttemptedConfiguration;

    public static void main(String[] args) {
        String input = readInput("6.txt");
        int[] memoryBanks = toMemoryBanks(input);
        ReallocationRoutine reallocationRoutine = new ReallocationRoutine();
        System.out.println("Solution 6.1: "+ reallocationRoutine.reallocateUntilCycleFound(memoryBanks));
        System.out.println("Solution 6.2: "+ reallocationRoutine.getCycleCount());
    }

    public int reallocateUntilCycleFound(int[] memoryBanks){
        configurations = new ArrayList<>(5000);
        MemoryBankConfiguration configuration = new MemoryBankConfiguration(memoryBanks);
        while(!configurations.contains(configuration)){
            configurations.add(configuration);
            reallocate(memoryBanks);
            configuration = lastAttemptedConfiguration = new MemoryBankConfiguration(memoryBanks);
        }
        return configurations.size();
    }

    public int getCycleCount(){
        return configurations.size() - configurations.indexOf(lastAttemptedConfiguration);
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

    static class MemoryBankConfiguration {
        private final int[] memoryBanks;

        public MemoryBankConfiguration(int[] memoryBanks) {
            this.memoryBanks = Arrays.copyOf(memoryBanks, memoryBanks.length);
        }

        public int[] getMemoryBanks() {
            return memoryBanks;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MemoryBankConfiguration that = (MemoryBankConfiguration) o;

            return Arrays.equals(memoryBanks, that.memoryBanks);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(memoryBanks);
        }
    }

}
