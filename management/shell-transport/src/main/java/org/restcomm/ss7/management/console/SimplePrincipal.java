
package org.restcomm.ss7.management.console;

import java.security.Principal;

/**
 *
 * @author amit bhayani
 *
 */
public class SimplePrincipal implements Principal {
    private final String name;

    /**
     *
     */
    public SimplePrincipal(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.security.Principal#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimplePrincipal other = (SimplePrincipal) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
