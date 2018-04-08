/*
 * Copyright (c) 2017 - 2018 - Frank Hossfeld
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package de.gishmo.gwt.sema4g.example.servlet;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import de.gishmo.gwt.sema4g.example.shared.dto.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class JsonMessageServlet
  extends HttpServlet {

  private MyBeanFactory myBeanFactory = AutoBeanFactorySource.create(MyBeanFactory.class);

  interface MyBeanFactory
    extends AutoBeanFactory {

    AutoBean<Message> message();

  }

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response)
    throws ServletException, IOException {
    String wait = request.getParameter("wait");
    if (wait != null) {
      try {
        Thread.sleep(Integer.parseInt(wait));

        Message message = this.createMessage();
        message.setMessage("Service (Servlet) Fourteen returned after " + wait + " ms");

        PrintWriter out = response.getWriter();
        out.print(this.serializeMessageToJSON(message));
        out.flush();

        return;
      } catch (InterruptedException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    } else {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
    throws ServletException, IOException {
    super.doPost(request,
                 response);
    this.doGet(request,
               response);
  }

  private Message createMessage() {
    AutoBean<Message> message = myBeanFactory.message();
    return message.as();
  }

  private String serializeMessageToJSON(Message message) {
    AutoBean<Message> bean = AutoBeanUtils.getAutoBean(message);
    return AutoBeanCodex.encode(bean)
                        .getPayload();
  }
}