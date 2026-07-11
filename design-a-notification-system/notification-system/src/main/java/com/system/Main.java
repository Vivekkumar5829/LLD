package com.system;




import com.system.builder.NotificationBuilder;
import com.system.controller.NotificationController;
import com.system.enums.ChannelType;
import com.system.enums.Priority;
import com.system.models.*;
import java.util.*;


public class Main {


    public static void main(String[] args){


        NotificationPreference preference =
                new NotificationPreference();


        User user =
                new User(
                        "Vivek",
                        preference
                );


        NotificationTemplate template =
                new NotificationTemplate(
                        "ORDER_SUCCESS",
                        "Your order is ready"
                );



        Notification notification =
                new NotificationBuilder()
                        .setUser(user)
                        .setTemplate(template)
                        .setChannelType(ChannelType.EMAIL)
                        .setPriority(Priority.CRITICAL)
                        .build();



        NotificationController controller =
                new NotificationController();



        controller.send(notification);



        System.out.println(notification);

    }
}
