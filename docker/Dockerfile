# Use the official image as a parent image.
FROM rabbitmq:3.8.3-management

# Enable MQTT plugin
RUN rabbitmq-plugins enable rabbitmq_mqtt

#Expose UI management port
EXPOSE 15672 15672