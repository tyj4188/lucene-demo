/*
 * <p>
 * Copyright: Copyright (c) 2018-08-17 11:30
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.queryparser;

import com.john.analyzer.ik.IKAnalyzer4Lucene7;
import com.john.common.Common;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * 新解析框架的标准解析器
 *
 * @author tongyongjian
 * @date 2018/8/17
 */
public class StandardQueryParserDemo {
    public static void main(String[] args) throws IOException, QueryNodeException {
        Analyzer analyzer = new IKAnalyzer4Lucene7(true);

        Directory directory = FSDirectory.open(new File(Common.LUCENE_DB_DIR).toPath());
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        StandardQueryParser parser = new StandardQueryParser(analyzer);
        String lql = "(\"联想笔记本电脑\" OR simpleIntro:英特尔) AND type:电脑";
        Query query = parser.parse(lql, "name");
        Common.doQuery(query, searcher);
    }
}
