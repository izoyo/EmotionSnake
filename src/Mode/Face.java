package Mode;

import java.util.*;

public enum Face {
    sad, happy, angry, normal, none;

    private static final List<Face> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size() - 1;
    private static final Random RANDOM = new Random();

    public static Face randomFace() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}