package com.nttdata.glue;

import com.nttdata.steps.OrderStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

public class OrderStepDef {

    @Steps
    OrderStep orderSteps;

    private static int ID = 0;


    @Given("que tengo una orden con los siguientes datos   {int} , {int} , {int} , {string} , {string} ,  true")
    public void queTengoUnaOrdenConLosSiguientesDatosTrue(int id, int pedId, int quantity, String shipDate, String status) {
        orderSteps.prepararOrden(id, pedId, quantity, shipDate, status, true);
    }

    @Given("que tengo una orden con los siguientes datos")
    public void queTengoUnaOrdenConLosSiguientesDatos(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        if (data.isEmpty()) {
            throw new IllegalArgumentException("La tabla de datos está vacía.");
        }

        Map<String, String> record = data.get(0);

        try {
            int id = Integer.parseInt(record.get("id"));
            int petId = Integer.parseInt(record.get("petId"));
            int quantity = Integer.parseInt(record.get("quantity"));
            String shipDate = record.get("shipDate");
            String status = record.get("status");
            boolean complete = Boolean.parseBoolean(record.get("complete"));

            orderSteps.prepararOrden(id, petId, quantity, shipDate, status, complete);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error en el formato de los datos numéricos.", e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Uno o más datos están faltando en la tabla.", e);
        }
    }

    @When("envio una petición POST al endpoint")
    public void envioUnaPeticiónPOSTAlEndpoint() {
        orderSteps.CrearOrden();
    }

    @Then("obtengo el código {string}")
    public void obtengoElCódigo(int arg0) {
        orderSteps.validarResponse(arg0);
    }


    @Given("que tengo una orden con el {string}")
    public void queTengoUnaOrdenConEl(String arg0) {
        int orderId = Integer.parseInt(arg0);
        ID = orderId;
        orderSteps.imprimirID(orderId);

    }

    @When("envio una petición GET al endpoint")
    public void envioUnaPeticiónGETAlEndpoint() {

        orderSteps.obtenerOrdenPorId(ID);
    }

    @Then("valido el {string}")
    public void validoElEImprimoLaOrden(String arg0) {
        int codigo = Integer.parseInt(arg0);
        orderSteps.validarResponse(codigo);

    }

    @Given("que tengo una orden con los siguientes datos {int}, {int}, {string}, {string} ,{string} ,<complete>")
    public void queTengoUnaOrdenConLosSiguientesDatosComplete(String arg0, String arg1, String arg2, String arg3, String arg4) {

    }

}
