package com.volunteer.commonweal.services.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
//读取配置文件中的hive下的配置
//正式版把test改为hive
//测试版使用test前缀的配置文件
@Component
@ConfigurationProperties(prefix = "hive")
public class Config {
    private boolean dev;
    private Config.Mongodb mongodb;
    private Map<Integer,String> exceptionsMap;

    public boolean getdev(){
        return this.dev;
    }
    public void setdev(boolean dev){
        this.dev = dev;
    }
    public Mongodb getMongodb(){
        return this.mongodb;
    }
    public void setMongodb(Mongodb mongodb){
        this.mongodb = mongodb;
    }
    public Map<Integer,String> getExceptionsMap(){
        return this.exceptionsMap;
    }
    public void setExceptionsMap(Map<Integer,String> exceptionsMap){
        this.exceptionsMap = exceptionsMap;
    }

    public static class Mongodb{
        private String host;
        private int port;
        private String database;
        public String getHost(){
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getDatabase() {
            return database;
        }
        public void setDatabase(String database){
            this.database = database;
        }
    }
}
