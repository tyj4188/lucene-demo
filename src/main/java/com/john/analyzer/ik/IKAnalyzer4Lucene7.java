/*
 *
 * 项目名：	com.john.analyzer.ik
 * 文件名：	IKAnalyzer4Lucene7
 * 模块说明：
 * 修改历史：
 * 2018/6/12 - tongyongjian - 创建。
 */

package com.john.analyzer.ik;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * @author tongyongjian
 * @date 2018/6/12
 */
public class IKAnalyzer4Lucene7 extends Analyzer {
    private boolean useSmart;

    public boolean useSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    /**
     * IK分词器Lucene  Analyzer接口实现类
     *
     * 默认细粒度切分算法
     */
    public IKAnalyzer4Lucene7() {
        this(false);
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param useSmart 当为true时，分词器进行智能切分
     */
    public IKAnalyzer4Lucene7(boolean useSmart) {
        super();
        this.useSmart = useSmart;
    }

    /**
     * 重载Analyzer接口，构造分词组件
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer _IKTokenizer = new IKTokenizer4Lucene7(this.useSmart());
        return new TokenStreamComponents(_IKTokenizer);
    }
}
