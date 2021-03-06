// 1 - Pacote
package petstore;

// 2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;


// 3 - Classe
public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
@Test(priority = 0)  // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado - Parte do Rest Assured
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
        .when()  // Quando
                .post(uri)
        .then()  // Então
                .log().all()
                .statusCode(200)
                .body("category.name", is("Dog"))
                .body("name", is("Iron"))
                .body("status", is("Atendido"))
                .body("tags.name", contains("Yorkshire"))

        ;

    }
@Test(priority = 1)
    public void consultarPet() {
        String petId = "15488965";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is("Dog"))
                .body("name", is("Iron"))
                .body("status", is("Atendido"))
                .body("tags.name", contains("Yorkshire"))
        ;


    }

@Test
    public void alterarPet(){

}


}