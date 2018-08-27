/*
 * <p>
 * Copyright: Copyright (c) 2018-08-27 11:07
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.analyzer.ik;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.util.Map;

/**
 * solr 集成 IK 分词器工厂类
 *
 * @author tongyongjian
 * @date 2018/8/27
 */
public class IKTokenizer4Lucene7Factory extends TokenizerFactory {

    private boolean useSmart = false;

    public IKTokenizer4Lucene7Factory(Map<String, String> args) {
        super(args);
        String useSmartParam = (String) args.get("useSmart");
        if("true".equals(useSmartParam)) {
            this.useSmart = true;
        }
    }

    @Override
    public Tokenizer create(AttributeFactory attributeFactory) {
        return new IKTokenizer4Lucene7(this.useSmart);
    }
}
