package devco.viajes.viajes.Aplicacion;

import devco.viajes.viajes.Dominio.Entidades.Viaje;
import devco.viajes.viajes.Infraestructura.Rabbit.Publicador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="viaje")
public class ViajeController {

    // El proceso debería pasar por reglas de negocio, creación de elementos en la base de datos y no debería ser directo desde el controlador
    Publicador publicador = new Publicador();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Viaje> crearViaje(@RequestBody Viaje viaje){
        publicador.publicarMensaje("devco.viajes.viajecomprado", viaje.getTipo(),viaje.getIdViaje()+viaje.getDestino());
        return new ResponseEntity<Viaje>(viaje, null , HttpStatus.OK);
    }



}
