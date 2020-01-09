package com.dagm.devtool.cache;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class StoreKey implements Serializable {

    /**
     * Item category
     */
    private String category;

    /**
     * Parameters
     */
    private Object[] params;

    public static StoreKey valueOf(String category, Object... params) {
        return new StoreKey(category, params);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
            append(category).append(params).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
            append(category).append(params).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StoreKey) {
            StoreKey sk = (StoreKey) obj;
            return new EqualsBuilder().
                append(category, sk.category).
                append(params, sk.params).
                isEquals();
        }
        return false;
    }
}
