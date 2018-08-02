/*
 * <p>
 * Copyright: Copyright (c) 2018-06-27 16:23
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.exercise;

import com.john.analyzer.ik.IKAnalyzer4Lucene7;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * 商品索引练习.
 * 对商品各项属性进行不同的索引.
 *
 * @author tongyongjian
 * @date 2018/6/27
 */
public class ProductIndexExercise {
    public static void main(String[] args) {
        // 不索引，存储
        String prodId = "p0001";

        // 分词索引(存储词频、位置、偏移量)、存储
        String name = "ThinkPad X1 Carbon 20KH0009CD/25CD 超极本轻薄笔记本电脑";

        // 仅存储
        String imgUrl = "http://www.dongnao.com/aaa";

        // 分词索引(不需要支持短语、临近查询)、存储，结果中支持高亮显示
        String simpleIntro = "集成显卡 英特尔 酷睿 i5-8250U 14英寸";

        // 不分词索引，但存储
        String brand = "ThinkPad";

        Analyzer analyzer = new IKAnalyzer4Lucene7(true);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try(
                Directory dir = FSDirectory.open(new File("/Users/tongyongjian/Documents/ProgramFiles/LuceneDatabase").toPath());
                IndexWriter writer = new IndexWriter(dir, config);
                ) {
            Document doc = new Document();
            // 仅存储
            doc.add(new StoredField("prodId", prodId));
            FieldType type = new FieldType();
            type.setStored(true);
            type.setTokenized(true);
            type.setIndexOptions(IndexOptions
                    .DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            // 存储、索引、词项向量
            doc.add(new Field("name", name, type));
            // 仅存储
            doc.add(new StoredField("imgUrl", imgUrl));
            type.setIndexOptions(IndexOptions.DOCS_AND_FREQS);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}