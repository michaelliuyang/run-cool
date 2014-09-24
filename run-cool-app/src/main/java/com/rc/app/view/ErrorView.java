package com.rc.app.view;

import com.rc.app.response.ErrorResponse;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * 通用异常回复View
 * Created by michael 2014/9/18.
 */
public class ErrorView implements View {

    @Override
    public void render(Map<String, ?> modelView, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object protocolObject = request.getAttribute("requestProtocol");
        Object userIdObject = request.getAttribute("requestUserId");
        String protocol = protocolObject == null ? "" : protocolObject.toString();
        String userId = userIdObject == null ? "" : userIdObject.toString();
        ErrorResponse errorResponse = new ErrorResponse(protocol, userId);
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
