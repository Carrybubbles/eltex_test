package ru.fedin.visitor.models;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.vaadin.ui.CustomComponent;
import org.apache.log4j.Logger;
import org.bson.Document;
import ru.fedin.net.HTTPResponser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mongodb.client.model.Filters.eq;

public class VisitorCounter {
    private static final Logger log = Logger.getLogger(VisitorCounter.class);

    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    public VisitorCounter(){
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(inputStream);
            mongoClient = new MongoClient(prop.getProperty("db.host"), Integer.valueOf(prop.getProperty("db.port")));
            MongoDatabase db = mongoClient.getDatabase(prop.getProperty("db.name"));
            collection = db.getCollection(prop.getProperty("db.tablename"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Integer incrementCounter(){
        if(collection != null) {
            Document myDoc = collection.find(eq("title", "visitors_counter")).first();
            if (myDoc != null) {
                BasicDBObject query = new BasicDBObject();
                query.put("title", "visitors_counter");
                Integer counter = myDoc.getInteger("counter");
                BasicDBObject incValue = new BasicDBObject("counter", 1);
                BasicDBObject intModifier = new BasicDBObject("$inc", incValue);
                collection.updateOne(query, intModifier);
                return counter + 1;
            }
            log.error("There is not table in DB with visitors information. Check your DB.");
        }
        log.error("Can't connect to DB check configuration");
        return null;
    }


}
