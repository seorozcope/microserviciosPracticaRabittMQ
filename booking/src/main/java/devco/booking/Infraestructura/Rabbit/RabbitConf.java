package devco.booking.Infraestructura.Rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        //una aplicación escucha una cola
        container.setQueueNames("booking.viajes.viajegenerado");
        container.setMessageListener(new Consumidor());
        //no se debe asumir el error, el mensaje debe conservarse o qué manejo debe realizarse
        // si hay un error en la capa de dominio en la capa de mensaje debe lanzarlo a otra cola de manejo de errores
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }
}
