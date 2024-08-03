package com.sohliou.ia.demo.config;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class RagDataLoader {
    
    @Value("classpath:/store/bloc.pdf")
    private Resource pdfRssource;

   

    @Value("store-data-v2.json")
    private String storefile;
    
    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
SimpleVectorStore vectorStore = new SimpleVectorStore(embeddingModel);
String fileStore = Path.of("src","main", "resources","store").toAbsolutePath()+"/"+storefile;
File file =new File(fileStore);
if(!file.exists()){
    PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(pdfRssource);
    List<Document> documents = pagePdfDocumentReader.get();
    TextSplitter textSplitter = new TokenTextSplitter();
    List<Document> chunks = textSplitter.split(documents);
    vectorStore.accept(chunks);
    vectorStore.save(file);
}else{
    vectorStore.load(file);
}
return vectorStore;
}
}
