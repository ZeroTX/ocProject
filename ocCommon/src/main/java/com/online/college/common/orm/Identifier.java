package com.online.college.common.orm;

import java.io.Serializable;

/**
 * Created by tx on 2018/7/4.
 */
public interface Identifier<KEY extends Serializable> {
    KEY getId();
}
