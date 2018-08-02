/*
 *
 * 项目名：	com.john.analyzer
 * 文件名：	IKAnalyzerDemo
 * 模块说明：
 * 修改历史：
 * 2018/6/13 - tongyongjian - 创建。
 */

package com.john.analyzer;

import com.john.analyzer.ik.IKAnalyzer4Lucene7;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * @author tongyongjian
 * @date 2018/6/13
 */
public class IKAnalyzerDemo {

    public static void doToken(TokenStream ts) throws IOException {
        ts.reset();
        CharTermAttribute attr = ts.getAttribute(CharTermAttribute.class);
        while(ts.incrementToken()) {
            System.out.printf(attr.toString() + "|");
        }
        System.out.println("\n");
        ts.end(); // 关闭资源
        ts.close();
    }


    public static void main(String[] args) {
        String enText = "Analysis is one of the main causes of slow indexing. Simply put, ";
        String cnText = "厉害了我的国一经播出，受到各方好评，强烈激发了国人的爱国之情、自豪感！";

        // 细颗粒度切分
        try(Analyzer analyzer = new IKAnalyzer4Lucene7()) {
            TokenStream ts = analyzer.tokenStream("", enText);
            System.out.println("IKAnalyzer中文分词器 细颗粒度 英文分词效果 : ");
            doToken(ts);

            ts = analyzer.tokenStream("", cnText);
            System.out.println("IKAnalyzer中文分词器 细颗粒度 中文分词效果 : ");
            doToken(ts);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 智能切分
        try(Analyzer analyzer = new IKAnalyzer4Lucene7(true)) {
            TokenStream ts = analyzer.tokenStream("", enText);
            System.out.println("IKAnalyzer中文分词器 智能切分 英文分词效果 : ");
            doToken(ts);

            ts = analyzer.tokenStream("", cnText);
            System.out.println("IKAnalyzer中文分词器 智能切分 中文分词效果 : ");
            doToken(ts);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
