/*
 * <p>
 * Copyright: Copyright (c) 2018-08-14 15:40
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.exercise;

import com.john.common.Common;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * 如果用户输入了“联想笔记本电脑”来搜索商品名称或商品简介中包含这个短语的商品，这个查询我们该如何构建？
 *
 * @author tongyongjian
 * @date 2018/8/14
 */
public class ProductQueryExercise {

    public static void main(String[] args) throws IOException {
        Directory directory = FSDirectory.open(new File(Common.LUCENE_DB_DIR).toPath());
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        Query nameQuery = new PhraseQuery("name", "联想", "笔记本电脑");
        Query introQuery = new PhraseQuery("simpleIntro", "联想笔记本电脑");
        BooleanQuery booleanQuery = new BooleanQuery.Builder()
                .add(nameQuery, BooleanClause.Occur.SHOULD)
                .add(introQuery, BooleanClause.Occur.SHOULD).build();

        Common.doQuery(booleanQuery, searcher);
    }

}
