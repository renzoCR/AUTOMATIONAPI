package com.nttdata.steps;

import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderStep {


    private static String CREATE_ORDER = "https://petstore.swagger.io/v2/store/order";
    private static String GET_ORDER = "https://petstore.swagger.io/v2/store/order/";
    private static int ID= 0;
    private Response response;


    public void prepararOrden(int id, int pedId, int quantity, String shipDate, String status, boolean complete){
        response = given().baseUri(CREATE_ORDER)
                .header("Content-Type", "application/json")
                .body("{" +"\"id\": " + id + "," +
                        "\"petId\": " + pedId + "," +
                        "\"quantity\": " + quantity + "," +
                        "\"shipDate\": \"" + shipDate + "\"," +
                        "\"status\": \"" + status + "\"," +
                        "\"complete\": " + complete +
                        "}"

                ).when()
                .post()
                .then()
                .extract()
                .response();
    }
    public void CrearOrden(){
        response = given().baseUri(CREATE_ORDER)
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then()
                .extract().response();
    }
    public void validarResponse(int statusCode){
        response.then().statusCode(statusCode);
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
