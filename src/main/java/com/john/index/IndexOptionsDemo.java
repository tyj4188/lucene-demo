/*
 *
 * 项目名：	com.john.index
 * 文件名：	IndexOptionsDemo
 * 模块说明：
 * 修改历史：
 * 2018/6/21 - tongyongjian - 创建。
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
 * @author tongyongjian
 * @date 2018/6/21
 */
public class IndexOptionsDemo {
    public static void main(String[] args) {
        Analyzer analyzer = new IKAnalyzer4Lucene7(true);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try(
                Directory dir = FSDirectory.open(new File("/Users/tongyongjian/Documents/ProgramFiles/LuceneDatabase").toPath());

                IndexWriter writer = new IndexWriter(dir, config);) {

            Document doc = new Document();
            String name = "content";
            String value = "张三说的确实在理";
            FieldType type = new FieldType();
            type.setStored(false); // 存储
            type.setTokenized(true); // 分词
            type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS); // 设置该字段的索引选项
            type.freeze(); // 不可更改

            Field field = new Field(name, value, type);
            doc.add(field);
            writer.addDocument(doc);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
