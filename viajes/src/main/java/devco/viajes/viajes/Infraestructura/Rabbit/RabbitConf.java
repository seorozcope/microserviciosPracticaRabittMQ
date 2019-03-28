package devco.viajes.viajes.Infraestructura.Rabbit;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {

    @Bean
    public ConnectionFactory connectionFactory (){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("usrViajes");
        cachingConnectionFactory.setPassword("usrViajes");
        cachingConnectionFactory.setConnectionTimeout(10_000);
        cachingConnectionFactory.setRequestedHeartBeat(3_000);

        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate template (ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }
}
