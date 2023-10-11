package pl.javastart.equipy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//dzięki adnotacji @ResponseStatus jeśli ten wyjątek dotrze do kontrolera, to Spring
//(konkretnie ResponseStatusExceptionResolver) zadba o jego obsługę i zwróci kod 409 dołaczając do odpowiedzi
//obiekt z polem message ustawionym na komunikat pobrany z atrybutu reason wyjątku
@ResponseStatus(value = HttpStatus.CONFLICT, reason ="Użytkownik z takim peselem już istnieje")
class DuplicatePeselException extends RuntimeException {
}
