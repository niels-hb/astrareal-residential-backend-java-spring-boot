package tech.astrareal.residential.building.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Building not found")
public class BuildingNotFoundException extends Exception {
}
