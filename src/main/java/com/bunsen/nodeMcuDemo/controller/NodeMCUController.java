/**
 * @author: Willy
 *
 */
package com.bunsen.nodeMcuDemo.controller;

import com.bunsen.nodeMcuDemo.dao.NodeMCUDao;
import com.bunsen.nodeMcuDemo.model.NodeMCU;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sensor-data")
public class NodeMCUController extends HttpServlet {
    private NodeMCUDao nodeMCUDao = new NodeMCUDao();
    private NodeMCU nodeMCU=new NodeMCU();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temperature = request.getParameter("temperature");
        String humidity = request.getParameter("humidity");

        if (temperature != null && humidity!=null) {
            nodeMCU.setTemperature(Float.parseFloat(temperature));
            nodeMCU.setHumidity(Float.parseFloat(humidity));
            nodeMCUDao.saveData(nodeMCU);
            response.getWriter().write("Data received and saved.");
        } else {
            response.getWriter().write("Error: No data received.");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
