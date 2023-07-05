# SubAPI

Die Inizialisierung sollte beim Anfang des Plugin's erfolgen, da dies am meisten Leistung braucht:

```java
SubAPI subAPI = new SubAPI(plugin);
```

Die Instanz kann man nach der Inizialisierung folgend bekommen::
```java
SubAPI.getInstance();
```
Um nur die Connections zwischen Minecraft, Discord und Twitch zu bekommen:
```java
 subAPI.getDefaultUser("value", By.MINECRAFTUUID);
 subAPI.getDefaultUser("value", By.TWITCHID);
 subAPI.getDefaultUser("value", By.DISCORDID);
```
Um den kompletten SyncUser zu bekommen kann man folgenden Code verwenden:
```java
SyncUser user = subAPI.getUser("value", By.MINECRAFTUUID);

//Twitch-Informationen bekommen
if(!user.getTwitch().isLoaded()) {
            user.getTwitch().load(new AsynchroneCallback() {
                @Override
                public void onComplete(Response response) {
                    user.getTwitch().getUserId();
                    user.getTwitch().getTier();
                    user.getTwitch().getDisplayName();
                }
            });
        } else {
            user.getTwitch().getUserId();
            user.getTwitch().getTier();
            user.getTwitch().getDisplayName();
        }
```

