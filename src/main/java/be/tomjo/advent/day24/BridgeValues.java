package be.tomjo.advent.day24;

class BridgeValues {
    private final int strength;
    private final int length;

    public BridgeValues(int strength, int length) {
        this.strength = strength;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getStrength() {
        return strength;
    }

    public BridgeValues add(int strength, int length) {
        return new BridgeValues(this.strength + strength, this.length + length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BridgeValues that = (BridgeValues) o;

        if (strength != that.strength) return false;
        return length == that.length;
    }

    @Override
    public int hashCode() {
        int result = strength;
        result = 31 * result + length;
        return result;
    }
}
