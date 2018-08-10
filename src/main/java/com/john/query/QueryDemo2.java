/*
 * <p>
 * Copyright: Copyright (c) 2018-08-09 16:49
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.query;

import com.john.analyzer.ik.IKAnalyzer4Lucene7;
import com.john.common.Common;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * 基本查询与组合查询
 *
 * @author tongyongjian
 * @date 2018/8/9
 */
public class QueryDemo2 {

    public static void main(String[] args) throws IOException, ParseException {

        Analyzer analyzer = new IKAnalyzer4Lucene7(true);
        Directory directory = FSDirectory.open(new File(Common.LUCENE_DB_DIR).toPath());
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        //String fieldName = "name";
        //QueryParser parser = new QueryParser(fieldName, analyzer);

        System.out.println("****************** 词项查询 ******************");
        Query query1 = new TermQuery(new Term("name", "thinkpad"));
        doQuery(query1, searcher);

        System.out.println("****************** 布尔查询 ******************");
        Query query2 = new TermQuery(new Term("simpleIntro", "英特尔"));
        BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
        booleanQueryBuilder.add(query1, BooleanClause.Occur.SHOULD); // 或
        booleanQueryBuilder.add(query2, BooleanClause.Occur.MUST); // 且
        BooleanQuery booleanQuery = booleanQueryBuilder.build();
        doQuery(booleanQuery, searcher);

        System.out.println("****************** 短语查询 ******************");
        PhraseQuery phraseQuery1 = new PhraseQuery("name", "thinkpad", "carbon");
        doQuery(phraseQuery1, searcher);

        System.out.println("****************** 短语查询 ******************");
        PhraseQuery phraseQuery2 = new PhraseQuery(1, "name"
                , "thinkpad", "carbon");
        doQuery(phraseQuery2, searcher);

    }


    public static void doQuery(Query query, IndexSearcher searcher) throws IOException {
        System.out.println(" ***** 查询语句 : " + query.toString());

        TopDocs docs = searcher.search(query, 10);
        System.out.println("查询结果 ************** ");
        System.out.println(" **** 命中数 : " + docs.totalHits);

        for(ScoreDoc tmp : docs.scoreDocs) {
            Document hitDoc = searcher.doc(tmp.doc);
            System.out.println(" -------- DocId = " + tmp.doc + ", Score = " + tmp.score);

            System.out.println(" - prodId = " + hitDoc.get("prodId"));
            System.out.println(" - name = " + hitDoc.get("name"));
            System.out.println(" - simpleIntro = " + hitDoc.get("simpleIntro"));
            System.out.println(" - price = " + hitDoc.get("price"));

            System.out.println();
        }
    }
}
