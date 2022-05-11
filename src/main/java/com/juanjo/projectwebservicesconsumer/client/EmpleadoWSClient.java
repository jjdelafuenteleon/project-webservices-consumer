/**
 * 
 */
package com.juanjo.projectwebservicesconsumer.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.juanjo.projectwebservicesconsumer.dto.EmpleadoDTO;

/**
 * @author Juanjo
 *
 */
public class EmpleadoWSClient {
	
	public static void main(String[] args) {
		//::::::::::::::::::::GET::::::::::::::::::::::::::
//		Client client = ClientBuilder.newClient();
//		WebTarget webTarget = client.target("http://localhost:8080/project-webservices/juanjo/empleadosWS/").path("consultarEmpleadosPorNumeroEmpleado").path("123456");
//		
//		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//		Response response = invocationBuilder.get();
//		
//		if (response.getStatus() == 204) {
//			System.out.println("EmpleadoWSClient.main(No se encontro el empleado...)");
//		}
//		
//		if (response.getStatus() == 200) {
//			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
//			
//			System.out.println("EmpleadoWSClient.main(Nombre) = "+empleadoDTO.getNombre());
//			System.out.println("EmpleadoWSClient.main(Fecha Creacion) = "+empleadoDTO.getFechaCreacion());
//		}
		
		//::::::::::::::::::::::::::POST::::::::::::::::::::::::
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/project-webservices/juanjo/empleadosWS/").path("guardarEmpleado");
		
		EmpleadoDTO emp = new EmpleadoDTO();
		emp.setNumeroEmpleado("654321");
		emp.setNombre("Luis Alejandro");
		emp.setPrimerApellido("Lopez");
		emp.setSegundoApellido("Ramore");
		emp.setEdad(25);
		emp.setFechaCreacion(LocalDateTime.now());
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_JSON));	
		
		if (response.getStatus() == 400) {
			String error = response.readEntity(String.class);			
			System.out.println("EmpleadoWSClient.main(error) = "+error);		
		}
		
		if (response.getStatus() == 200) {
			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);			
			System.out.println("EmpleadoWSClient.main(Nombre) = "+empleadoDTO.getNombre());
			System.out.println("EmpleadoWSClient.main(Fecha Creacion) = "+empleadoDTO.getFechaCreacion());		
		}		
	}
	

}
