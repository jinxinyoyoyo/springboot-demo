package com.jjkj.core.util;

import java.util.List;

/**
 * Created by Adminis on 2018/4/24.
 */
public interface TreeObject {
    Object getId();

    void setId(Object id);

    Object getParentId();

    void setParentId(Object parentId);

    String getName();

    void setName(String name);

    List getChildren();

    void setChildren(List children);

}
