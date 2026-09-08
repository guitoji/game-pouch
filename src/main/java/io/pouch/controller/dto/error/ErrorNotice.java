package io.pouch.controller.dto.error;

import java.util.List;

public record ErrorNotice(Integer status, String message, List<ErrorField> errorFields) {
}
