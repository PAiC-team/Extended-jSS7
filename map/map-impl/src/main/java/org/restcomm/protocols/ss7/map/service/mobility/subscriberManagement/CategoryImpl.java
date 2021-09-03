
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Category;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CategoryValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author daniel bichara
 * @author sergey vetyutnev
 *
 */
public class CategoryImpl extends OctetStringLength1Base implements Category {

    public CategoryImpl() {
        super("Category");
    }

    public CategoryImpl(int data) {
        super("Category", data);
    }

    public CategoryImpl(CategoryValue value) {
        super("Category", (value != null ? value.getCode() : 0));
    }

    public int getData() {
        return data;
    }

    @Override
    public CategoryValue getCategoryValue() {
        return CategoryValue.getInstance(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        CategoryValue val = this.getCategoryValue();
        if (val != null)
            sb.append(val);
        else
            sb.append(data);

        sb.append("]");

        return sb.toString();
    }
}
