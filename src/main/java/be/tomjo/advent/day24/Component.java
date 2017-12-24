package be.tomjo.advent.day24;

import static java.lang.Integer.parseInt;

class Component {
    private final int port1;
    private final int port2;

    public Component(String serialized) {
        String[] parts = serialized.split("/");
        this.port1 = parseInt(parts[0]);
        this.port2 = parseInt(parts[1]);
    }

    public int getPort1() {
        return port1;
    }

    public int getPort2() {
        return port2;
    }

    public int getStrength(){
        return port1 + port2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (port1 != component.port1) return false;
        return port2 == component.port2;
    }

    @Override
    public int hashCode() {
        int result = port1;
        result = 31 * result + port2;
        return result;
    }

    public int getOtherPort(int port) {
        return port1 == port ? port2 : port1;
    }
}
