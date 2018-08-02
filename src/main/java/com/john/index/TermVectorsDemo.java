/*
 * <p>
 * Copyright: Copyright (c) 2018-06-27 16:58
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john.index;

import com.john.analyzer.ik.IKAnalyzer4Lucene7;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * 标题、简要说明.
 * 类详细说明.
 *
 * @author tongyongjian
 * @date 2018/6/27
 */
public class TermVectorsDemo {
    public static void main(String[] args) {
        Analyzer analyzer = new IKAnalyzer4Lucene7(true);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try(
                Directory dir = FSDirectory.open(new File("/Users/tongyongjian/Documents/ProgramFiles/LuceneDatabase").toPath());

                IndexWriter writer = new IndexWriter(dir, config);
                ) {
            Document doc = new Document();
            String name = "content";
            String value = "联想高性能电脑，Mac系统4G内存1T硬盘";
            FieldType type = new FieldType();
            type.setStored(true);
            type.setTokenized(true);
            type.setIndexOptions(IndexOptions.DOCS);

            type.setStoreTermVectors(true);
            type.setStoreTermVectorOffsets(true);
            type.setStoreTermVectorPayloads(true);
            type.setStoreTermVectorPositions(true);

            type.freeze();

            Field field = new Field(name, value, type);
            doc.add(field);
            writer.addDocument(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
