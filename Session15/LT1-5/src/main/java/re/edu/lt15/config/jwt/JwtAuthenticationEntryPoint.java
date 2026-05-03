package re.edu.lt15.config.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import re.edu.lt15.dto.response.ErrorResponse;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final JsonMapper jsonMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String exceptionDetail = (String) request.getAttribute("exception");
        ErrorResponse<?> errorResponse;

        // Mã code HTTP luôn là 401, Status luôn là "FAIL"
        int code = HttpStatus.UNAUTHORIZED.value();
        String status = "FAIL";

        if ("EXPIRED_TOKEN".equals(exceptionDetail)) {
            errorResponse = new ErrorResponse<>(status, code,
                    "Lỗi xác thực Token: Token đã hết hạn", null, LocalDateTime.now());
        } else if ("MALFORMED_TOKEN".equals(exceptionDetail) || "UNSUPPORTED_TOKEN".equals(exceptionDetail)) {
            errorResponse = new ErrorResponse<>(status, code,
                    "Lỗi xác thực Token: Định dạng không hợp lệ hoặc chữ ký sai", null, LocalDateTime.now());
        } else if ("ILLEGAL_ARGUMENT_TOKEN".equals(exceptionDetail)) {
            errorResponse = new ErrorResponse<>(status, code,
                    "Lỗi xác thực Token: Chuỗi Token trống", null, LocalDateTime.now());
        } else if ("MISSING_TOKEN".equals(exceptionDetail)) {
            errorResponse = new ErrorResponse<>(status, code,
                    "Lỗi xác thực Token: Token bị thiếu hoặc không được cung cấp", null, LocalDateTime.now());
        } else {

            // Lỗi xác thực chung
            errorResponse = new ErrorResponse<>(status, code, "Lỗi xác thực Token: " + authException.getMessage(), null, LocalDateTime.now());
        }

        jsonMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
