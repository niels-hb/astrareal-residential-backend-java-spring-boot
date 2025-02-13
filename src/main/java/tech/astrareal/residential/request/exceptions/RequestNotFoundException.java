package tech.astrareal.residential.request.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Request not found.")
public class RequestNotFoundException extends Exception {
}
