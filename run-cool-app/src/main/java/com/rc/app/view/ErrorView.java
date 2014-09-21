package com.rc.app.view;

import com.rc.app.constants.ProtocolConstants;
import com.rc.app.response.ErrorResponse;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * 回复通用异常回复报文
 */
public class ErrorView implements View {

    @Override
    public void render(Map<String, ?> modelView, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ErrorResponse errorResponse = new ErrorResponse(ProtocolConstants.PROTOCOL_V1_0, null);
        byte[] data = errorResponse.convert2ByteResult();
        response.setContentLength(data.length);
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
        out.close();
    }

    @Override
    public String getContentType() {
        return "application/json";
    }
}
