package io.github.zanhorvat.aoc21.day02.model;

public class SubmarinePosition {
    private long depth;
    private long x;
    private long aim;

    public SubmarinePosition() {
        depth = 0;
        x = 0;
        aim = 0;
    }

    public long getDepth() {
        return depth;
    }

    public long getX() {
        return x;
    }

    public void changeSimple(String direction, int value) {
        if ("forward".equals(direction)) {
            this.x += value;
        } else if ("up".equals(direction)) {
            this.depth -= value;
        } else if ("down".equals(direction)) {
            this.depth += value;
        }
    }

    public void changeWithAim(String direction, int value) {
        if ("forward".equals(direction)) {
            this.x += value;
            this.depth += (value * this.aim);
        } else if ("up".equals(direction)) {
            this.aim -= value;
        } else if ("down".equals(direction)) {
            this.aim += value;
        }
    }
}
