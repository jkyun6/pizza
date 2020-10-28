package pizza;

//application.properties에서 설정한 MongoDB에 정보를 읽어서 Bean을 생성하는 페이지

//스프링 부트에서는 servlet-context, root-context가 없기 때문에

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration //Configuration 어노테이션은 root-context안에 들어갈 코드를 어노테이션으로 만든것이라고 생각하면 된다.

public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.username}")     //application.properties에서 정의한 MongoDB에 계정 아이디
    private String userName;


    @Value("${spring.data.mongodb.password}")    //application.properties에서 정의한 MongoDB에 계정 비밀번호
    private String password;


    @Value("${spring.data.mongodb.database}") //application.properties에서 정의한 MongoDB에있는 데이터베이스
    private String database;


    @Override
    protected String getDatabaseName() {
        return database;
    }

    //레거시 프로젝트에서 xml파일 안에 <bean>태그 안에 있던 코드를 적어놓은것.
    //mongotemplate을 만들어서 리턴해준다.
    public @Bean MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate((MongoClient) mongoClient(), database);
    }

    @Override
    public MongoClient mongoClient() {
        MongoCredential credential =  //인증 정보
                MongoCredential.createCredential(
                        userName, database, password.toCharArray()); //아이디, 데이터베이스, 비밀번호
        return new MongoClient(new ServerAddress("localhost", 27017),
                Arrays.asList(credential));
    }
}
