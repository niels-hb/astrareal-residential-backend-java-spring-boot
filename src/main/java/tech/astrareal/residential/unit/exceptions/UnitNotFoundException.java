package tech.astrareal.residential.unit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Unit not found")
public class UnitNotFoundException extends Exception {
}
