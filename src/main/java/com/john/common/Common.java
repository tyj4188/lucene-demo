/*
 * <p>
 * Copyright: Copyright (c) 2018-08-08 16:37
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.common;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

/**
 * 标题、简要说明.
 * 类详细说明.
 *
 * @author tongyongjian
 * @date 2018/8/8
 */
public class Common {
    public static final String LUCENE_DB_DIR = "/Users/tongyongjian/Documents/ProgramFiles/LuceneDatabase";

    public static void doQuery(Query query, IndexSearcher searcher) throws IOException {
        System.out.println("**** 查询 : " + query.toString());
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
            System.out.println(" - type = " + hitDoc.get("type"));

            System.out.println();
        }
    }
}
