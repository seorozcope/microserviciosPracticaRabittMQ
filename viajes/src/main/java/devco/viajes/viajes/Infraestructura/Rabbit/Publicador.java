package devco.viajes.viajes.Infraestructura.Rabbit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Publicador {

    ApplicationContext contexto= new AnnotationConfigApplicationContext(RabbitConf.class);
    RabbitTemplate template= contexto.getBean(RabbitTemplate.class);

    public void publicarMensaje(String exchange, String routingKey, String mensaje){
        template.convertAndSend(exchange,routingKey, mensaje);
    }
}
