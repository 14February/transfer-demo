package com.learn.servlet;

import com.learn.factory.BeanFactory;
import com.learn.factory.ProxyFactory;
import com.learn.pojo.Result;
import com.learn.service.TransferService;
import com.learn.service.impl.TransferServiceImpl;
import com.learn.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "transferServlet", value = "/transferServlet")
public class TransferServlet extends HttpServlet {

//    private TransferService transferService = new TransferServiceImpl();
//    private static TransferService transferService = (TransferServiceImpl) BeanFactory.getBean("transferService");

    public static final ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");

    public static final TransferService transferService = (TransferService) proxyFactory.getProxy(BeanFactory.getBean("transferService"));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");

        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);

        Result result = new Result();

        try {

            // 2. 调用service层方法
            transferService.transfer(fromCardNo,toCardNo,money);
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
            result.setMessage(e.toString());
        }

        // 响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }

}