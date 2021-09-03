
package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * @author amit bhayani
 *
 */
public enum AlertingLevel {

    Level0(0), Level1(1), Level2(2);

    private final int level;

    private AlertingLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public static AlertingLevel getInstance(int data) {
        switch (data) {
            case 0:
                return Level0;
            case 1:
                return Level1;
            case 2:
                return Level2;
        }

        return null;
    }
}
