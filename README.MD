### RabbitMQ with Spring API

This demo will show the basic usage of the messaging protocol via the RabbitMQ.

In order to run the demo, you need to have a rabbitMQ server running on your local.

Follow the steps below to install and configure it on your machine:

 - Download and install Erlang: https://www.erlang.org/downloads
 - Download and install the rabbitMQ: https://www.rabbitmq.com/install-windows.html
 - After both of the installations are complete, run the following command on the 'RabbitMQ Command Prompt' to activate its admin panel: `rabbitmq-plugins enable rabbitmq_management`

After you have completed steps above, you would be ready to run the demo.

Run each of the projects via
 `mvn spring-boot:run`

Provider API will be running on the `http://localhost:8081`
Consumer API will be running on the `http://localhost:8082`
