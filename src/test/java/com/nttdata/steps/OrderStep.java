package com.nttdata.steps;

import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class OrderStep {


    private static String CREATE_ORDER = "https://petstore.swagger.io/v2/store/order";
    private static String GET_ORDER = "https://petstore.swagger.io/v2/store/order/";
    private static int ID= 0;
    private Response response;
    private String orderPayload;

    public void prepararOrden(int id, int petId, int quantity, LocalDateTime shipDate, String status, boolean complete) {
        // Crear el JSON como string
        orderPayload = "{" +
                "\"id\": " + id + "," +
                "\"petId\": " + petId + "," +
                "\"quantity\": " + quantity + "," +
                "\"shipDate\": \"" + shipDate.toString() + "\"," +  // Convertir a String ISO 8601
                "\"status\": \"" + status + "\"," +
                "\"complete\": " + complete +
                "}";

        // Imprimir los datos enviados
        System.out.println("Datos enviados en la orden: " + orderPayload);
    }

    public void CrearOrden() {
        // Usar el payload preparado previamente para enviar la solicitud POST
        response = given().baseUri(CREATE_ORDER)
                .header("Content-Type", "application/json")
                .body(orderPayload)
                .when()
                .post()
                .then()
                .extract().response();
    }

    public void validarResponse(int statusCode) {
        response.then().assertThat().statusCode(equalTo(statusCode));
    }
    public void imprimirID(int orderId){
        System.out.println("El order ID utilizado es : " + orderId);
        ID = orderId;
    }

    public void obtenerOrdenPorId(int orderId) {
        response = given()
                .baseUri("https://petstore.swagger.io/v2/store/order")
                .header("Content-Type", "application/json")
                .pathParam("orderId", orderId)
                .when()
                .get("/{orderId}")
                .then()
                .extract()
                .response();
    }

    // Método para obtener la respuesta
    public Response obtenerRespuesta() {
        return response;
    }
}
