package com.volunteer.commonweal.models.implementModels.homePageModels;


import com.volunteer.commonweal.constants.DataBaseConst;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DataBaseConst.UPLOADTOKEN_DATABASE_NAME)
public class UploadToken {
    @Indexed(unique = true)
    String userId;
    String token;
    Long timeStamp;
}
