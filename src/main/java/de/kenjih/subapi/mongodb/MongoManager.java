package de.kenjih.subapi.mongodb;

import com.mongodb.client.model.Updates;
import de.kenjih.subapi.SubAPIMain;
import de.kenjih.subapi.objects.DefaultSyncUser;
import de.kenjih.subapi.objects.Token;
import de.kenjih.subapi.objects.enums.By;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MongoManager {

    //TWITCHID, DISCORDID, MINECRAFTUUID

    private static MongoDB mongoDB = SubAPIMain.getMongoDB();

    public void set(DefaultSyncUser user){
        if(getUser(user.getTwitchId(), By.TWITCHID) == null){
            Document document = new Document();
            document.append("twitchid", user.getTwitchId());
            document.append("discordid", user.getDiscordId());
            document.append("minecraftuuid", user.getUuid().toString());
            mongoDB.getCollection("data").set(document);
            return;
        }
        Bson updates = Updates.combine(
                Updates.set("twitchid", user.getTwitchId()),
                Updates.set("discordid", user.getDiscordId()),
                Updates.set("minecraftuuid", user.getUuid().toString())
        );
        Document search = mongoDB.getCollection("data").find("minecraftuuid", user.getUuid().toString());
        mongoDB.getCollection("data").update(search, updates);
    }

    public List<Token> getAllTokens(){
        List<Token> list = new ArrayList<>();
        if(mongoDB.getCollectionDocument("tokens").find().first() == null) return list;
        for(Document doc : mongoDB.getCollectionDocument("tokens").find()){
            list.add(new Token(doc.getString("id"), doc.getString("accesstoken"), doc.getString("refreshtoken")));
        }
        return list;
    }

    public void unlink(DefaultSyncUser user){
        if(getUser(user.getTwitchId(), By.TWITCHID) == null) return;
        Document doc = new Document("twitchid", user.getTwitchId());
        mongoDB.getCollection("data").getDocument().deleteOne(doc);
    }

    public DefaultSyncUser getUser(String value, By by){
        Document doc = mongoDB.getCollection("data").find(by.toString().toLowerCase(), value);
        if(doc == null) return null;
        return new DefaultSyncUser(doc.getString("twitchid"), doc.getString("discordid"), UUID.fromString(doc.getString("minecraftuuid")));
    }

    public Token getToken(String id){
        Document doc = mongoDB.getCollection("tokens").find("id", id);
        if(doc == null) return null;
        return new Token(doc.getString("id"), doc.getString("accesstoken"), doc.getString("refreshtoken"));
    }

    public void setAccessToken(Token setting){
        if(getToken(setting.getId()) == null){
            Document document = new Document();
            document.append("id", setting.getId());
            document.append("accesstoken", setting.getAccessToken());
            document.append("refreshtoken", setting.getRefreshToken());
            mongoDB.getCollection("tokens").set(document);
            return;
        }
        Bson updates = Updates.combine(
                Updates.set("id", setting.getId()),
                Updates.set("accesstoken", setting.getAccessToken()),
                Updates.set("refreshtoken", setting.getRefreshToken())
        );
        Document search = mongoDB.getCollection("tokens").find("id", setting.getId());
        mongoDB.getCollection("tokens").update(search, updates);
    }

}
