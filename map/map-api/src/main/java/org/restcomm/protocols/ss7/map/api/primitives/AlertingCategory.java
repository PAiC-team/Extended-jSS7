
package org.restcomm.protocols.ss7.map.api.primitives;

/**
 * @author amit bhayani
 *
 */
public enum AlertingCategory {

    Category1(4), Category2(5), Category3(6), Category4(7), Category5(8);

    private final int category;

    private AlertingCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return this.category;
    }

    public static AlertingCategory getInstance(int data) {
        switch (data) {
            case 4:
                return Category1;
            case 5:
                return Category2;
            case 6:
                return Category3;
            case 7:
                return Category4;
            case 8:
                return Category5;
        }

        return null;
    }
}
