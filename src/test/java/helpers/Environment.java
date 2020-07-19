package helpers;

public class Environment {
    public static final String
        baseSearchUrl = System.getProperty("base_search_url","https://amwine.ru/search/?q="),
        searchString = System.getProperty("search_string", "Magners Pear"),
        remoteDriverUrl = System.getProperty("remote_driver_url"),
        videoStorageUrl = System.getProperty("video_storage_url");
    public static boolean
        isRemoteDriver = remoteDriverUrl != null,
        isVideoOn = videoStorageUrl != null;
}

