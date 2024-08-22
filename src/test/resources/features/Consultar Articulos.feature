Feature: Validar Creación y obtencion de Order

  @prueba
  Scenario Outline: Generar una orden
    Given que tengo una orden con los siguientes datos
      | id   | petId | quantity | shipDate                    | status | complete |
      | <id> | <petId> | <quantity> | <shipDate>                | <status> | <complete> |
    When envio una petición POST al endpoint
    Then obtengo el código "<codigoEstatus>"

    Examples:
      | codigoEstatus | id | petId | quantity | shipDate                    | status | complete |
      | 200           | 2  | 1     | 1        | 2024-08-22T22:05:54.170Z    | placed | true     |
      | 400           | 0  | 0     | 0        |                            | placed | false    |


  @prueba
  Scenario Outline: Consultar una orden
    Given que tengo una orden con el "<id>"
    When envio una petición GET al endpoint
    Then valido el "<codigoEstatus>"

    Examples:
      | codigoEstatus | id|
      | 200           |"1"  |
      | 400           |"2"  |
