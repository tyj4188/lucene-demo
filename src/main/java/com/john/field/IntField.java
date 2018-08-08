/*
 * <p>
 * Copyright: Copyright (c) 2018-08-07 17:14
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.field;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.NumericUtils;

/**
 * 自定义整型字段
 *
 * @author tongyongjian
 * @date 2018/8/7
 */
public class IntField extends Field {

    public IntField(String fieldName, int value, FieldType type) {
        super(fieldName, type);
        this.fieldsData = Integer.valueOf(value);
    }

    @Override
    public BytesRef binaryValue() {
        byte[] bytes = new byte[Integer.BYTES];
        NumericUtils.intToSortableBytes((Integer) this.fieldsData, bytes, 0);
        return new BytesRef(bytes);
    }
}
